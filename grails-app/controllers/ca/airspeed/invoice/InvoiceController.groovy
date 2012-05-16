package ca.airspeed.invoice

import javax.mail.internet.InternetAddress;

import org.springframework.dao.DataIntegrityViolationException

class InvoiceController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def emailerService

	def index() {
		redirect(action: "list", params: params)
	}

	def list() {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[invoiceInstanceList: Invoice.list(params), invoiceInstanceTotal: Invoice.count()]
	}

	def create() {
		[invoiceInstance: new Invoice(params)]
	}

	def save() {
		def invoiceInstance = new Invoice(params)
		if (!invoiceInstance.save(flush: true)) {
			render(view: "create", model: [invoiceInstance: invoiceInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [
			message(code: 'invoice.label', default: 'Invoice'),
			invoiceInstance.id
		])
		redirect(action: "show", id: invoiceInstance.id)
	}

	def show() {
		def invoiceInstance = Invoice.get(params.id)
		if (!invoiceInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'invoice.label', default: 'Invoice'),
				params.id
			])
			redirect(action: "list")
			return
		}

		[invoiceInstance: invoiceInstance]
	}

	def edit() {
		def invoiceInstance = Invoice.get(params.id)
		if (!invoiceInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'invoice.label', default: 'Invoice'),
				params.id
			])
			redirect(action: "list")
			return
		}

		[invoiceInstance: invoiceInstance]
	}

	def update() {
		myUpdate('default.updated.message')
	}

	def myUpdate(successCode) {
		def invoiceInstance = Invoice.get(params.id)
		if (!invoiceInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'invoice.label', default: 'Invoice'),
				params.id
			])
			redirect(action: "list")
			return
		}

		if (params.version) {
			def version = params.version.toLong()
			if (invoiceInstance.version > version) {
				invoiceInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[
							message(code: 'invoice.label', default: 'Invoice')]
						as Object[],
						"Another user has updated this Invoice while you were editing")
				render(view: "edit", model: [invoiceInstance: invoiceInstance])
				return
			}
		}

		invoiceInstance.properties = params

		if (!invoiceInstance.save(flush: true)) {
			render(view: "edit", model: [invoiceInstance: invoiceInstance])
			return
		}

		flash.message = message(code: successCode, args: [
			message(code: 'invoice.label', default: 'Invoice'),
			invoiceInstance.id
		])
		redirect(action: "show", id: invoiceInstance.id)
	}

	def delete() {
		def invoiceInstance = Invoice.get(params.id)
		if (!invoiceInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'invoice.label', default: 'Invoice'),
				params.id
			])
			redirect(action: "list")
			return
		}

		try {
			invoiceInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [
				message(code: 'invoice.label', default: 'Invoice'),
				params.id
			])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [
				message(code: 'invoice.label', default: 'Invoice'),
				params.id
			])
			redirect(action: "show", id: params.id)
		}
	}

	def email() {
		Invoice invoiceInstance = Invoice.get(params.id)
		if (!invoiceInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'invoice.label', default: 'Invoice'),
				params.id
			])
			redirect(action: "list")
			return
		}
		Company company = invoiceInstance.job.customer.company
		def sender = new InternetAddress(company.invoiceEmail, company.invoiceFirstName + ' ' + company.invoiceLastName).toString()
		def recipients = invoiceInstance.job.invoiceRecipient.findAll {it.type == 'To'}
		def to = []
		recipients.each {
			to << new InternetAddress(it.email, it.firstName + ' ' + it.lastName).toString()
		}
		recipients = invoiceInstance.job.invoiceRecipient.findAll {it.type == 'Cc'}
		def cc = []
		recipients.each {
			cc << new InternetAddress(it.email, it.firstName + ' ' + it.lastName).toString()
		}
		recipients = invoiceInstance.job.invoiceRecipient.findAll {it.type == 'Bcc'}
		def bcc = []
		recipients.each {
			bcc << new InternetAddress(it.email, it.firstName + ' ' + it.lastName).toString()
		}

		def mail = [to: to, 
			from: sender, 
			cc: cc,
			bcc: bcc,
			subject: "Invoice #${invoiceInstance.invoiceNumber} from ${company.name}",
			headers: ['Disposition-Notification-To': sender], 
			text: "Hello ${invoiceInstance.job.customer.fullName} from the mail plugin.",
			html: "<html><head></head><body><p>Hello ${invoiceInstance.job.customer.fullName} from the mail plugin.</p></body></html>",
			attachments: [new File('./web-app/images/grails_logo.jpg')]]
		
		
		emailerService.emailInvoice(mail)
		params.deliveryStatus = 'Delivered'
		myUpdate('emailed.message')
	}
}
