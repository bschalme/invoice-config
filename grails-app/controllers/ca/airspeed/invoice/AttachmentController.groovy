package ca.airspeed.invoice

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException

class AttachmentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	static final int BUFF_SIZE = 100000;
	static final byte[] buffer = new byte[BUFF_SIZE];

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [attachmentInstanceList: Attachment.list(params), attachmentInstanceTotal: Attachment.count()]
    }

    def create() {
        [attachmentInstance: new Attachment(params)]
    }

    def save() {
        def attachmentInstance = new Attachment(params)
        if (!attachmentInstance.save(flush: true)) {
            render(view: "create", model: [attachmentInstance: attachmentInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'attachment.label', default: 'Attachment'), attachmentInstance.id])
        redirect(action: "show", id: attachmentInstance.id)
    }

    def show() {
        def attachmentInstance = Attachment.get(params.id)
        if (!attachmentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'attachment.label', default: 'Attachment'), params.id])
            redirect(action: "list")
            return
        }

        [attachmentInstance: attachmentInstance]
    }

    def edit() {
        def attachmentInstance = Attachment.get(params.id)
        if (!attachmentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'attachment.label', default: 'Attachment'), params.id])
            redirect(action: "list")
            return
        }

        [attachmentInstance: attachmentInstance]
    }

    def update() {
        def attachmentInstance = Attachment.get(params.id)
        if (!attachmentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'attachment.label', default: 'Attachment'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (attachmentInstance.version > version) {
                attachmentInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'attachment.label', default: 'Attachment')] as Object[],
                          "Another user has updated this Attachment while you were editing")
                render(view: "edit", model: [attachmentInstance: attachmentInstance])
                return
            }
        }

        attachmentInstance.properties = params

        if (!attachmentInstance.save(flush: true)) {
            render(view: "edit", model: [attachmentInstance: attachmentInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'attachment.label', default: 'Attachment'), attachmentInstance.id])
        redirect(action: "show", id: attachmentInstance.id)
    }

    def delete() {
        def attachmentInstance = Attachment.get(params.id)
        if (!attachmentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'attachment.label', default: 'Attachment'), params.id])
            redirect(action: "list")
            return
        }

        try {
            attachmentInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'attachment.label', default: 'Attachment'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'attachment.label', default: 'Attachment'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
	
	def upload() {
		def contentType	= request.getHeader("Content-Type") as String
		def fileName    = request.getHeader('X-File-Name') as String
		def fileSize 	= request.getHeader('X-File-Size') as Long
		def name 		= request.getHeader('X-Uploadr-Name') as String
		def info		= session.getAttribute('uploadr')
		def savePath	= ((name && info && info.get(name) && info.get(name).path) ? info.get(name).path : "/tmp") as String
		def dir 		= new File("${System.getProperty('user.home')}/Desktop/myFirstUploadr/${savePath}")
		println "dir = ${dir}"
		def file		= new File(dir,fileName)
		int dot         = 0
		def namePart    = ""
		def extension   = ""
		def testName    = ""
		def testIterator= 1
		int status      = 0
		def statusText  = ""

		// set response content type to json
		response.contentType    = 'application/json'

		// define input and output streams
		InputStream inStream = null
		OutputStream outStream = null
		ByteArrayOutputStream attachmentOutStream = null
		
		Attachment attachment = null

		// handle file upload
		try {
			inStream = request.getInputStream()
			// outStream = new FileOutputStream(file)
			attachmentOutStream = new ByteArrayOutputStream()

			while (true) {
				synchronized (buffer) {
					int amountRead = inStream.read(buffer);
					if (amountRead == -1) {
						break
					}
					// outStream.write(buffer, 0, amountRead)
					attachmentOutStream.write(buffer, 0, amountRead)
				}
			}
			// outStream.flush()
			attachmentOutStream.flush()
			attachment = new Attachment(fileName:file.getName(), mimeType:'application/pdf', content:attachmentOutStream.toByteArray())
			def invoice = Invoice.findByInvoiceNumber(savePath)
			attachment.invoice = invoice
			attachment.save(flush:true, failOnError:true)

			status      = 200
			statusText  = "'${file.name}' upload successful!"
		} catch (Exception e) {
			// whoops, looks like something went wrong
			status      = 500
			statusText  = e.getMessage()
		} finally {
			if (inStream != null) inStream.close()
			if (outStream != null) outStream.close()
			if (attachmentOutStream != null) attachmentOutStream.close()
		}

		// make sure the file was properly written
		if (status == 200 && fileSize > attachment.content.length) {
			// whoops, looks like the transfer was aborted!
			status      = 500
			statusText  = "'${file.name}' transfer incomplete, received ${file.size()} of ${fileSize} bytes"
		}

		// got an error of some sorts?
		if (status != 200) {
			// then -try to- delete the file
			try {
				attachment.delete()
			} catch (Exception e) { }
		}

		// render json response
		response.setStatus(status, statusText)
		render([written: (status == 200), fileName: file.name, status: status, statusText: statusText] as JSON)
	}
}
