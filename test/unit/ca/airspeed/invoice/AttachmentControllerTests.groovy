package ca.airspeed.invoice



import org.junit.*
import grails.test.mixin.*

@TestFor(AttachmentController)
@Mock([Attachment,Invoice, Job, InvoiceRecipient, Customer, Company])
class AttachmentControllerTests {


    def populateValidParams(params) {
      assert params != null
      params["fileName"] = 'silly-file.pdf'
	  params["mimeType"] = 'application/pdf'
	  params["content"] = "Hello World".bytes
	  if (Invoice.list().size() == 0) {
		  Seed.seedDb()
		  def inv362 = new Invoice(job:Job.get(1), invoiceNumber:'362', deliveryStatus:'ToBeDelivered', deliveryMethod:'Email', emailTemplateHtml:'', emailTemplatePlain:'').save(failOnError: true)
	  }
	  params["invoice.id"] = Invoice.findByInvoiceNumber('362').id
    }

    void testIndex() {
        controller.index()
        assert "/attachment/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.attachmentInstanceList.size() == 0
        assert model.attachmentInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.attachmentInstance != null
    }

    void testSave() {
        controller.save()

        assert model.attachmentInstance != null
        assert view == '/attachment/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/attachment/show/1'
        assert controller.flash.message != null
        assert Attachment.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/attachment/list'


        populateValidParams(params)
        def attachment = new Attachment(params)

        assert attachment.save() != null

        params.id = attachment.id

        def model = controller.show()

        assert model.attachmentInstance == attachment
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/attachment/list'


        populateValidParams(params)
        def attachment = new Attachment(params)

        assert attachment.save() != null

        params.id = attachment.id

        def model = controller.edit()

        assert model.attachmentInstance == attachment
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/attachment/list'

        response.reset()


        populateValidParams(params)
        def attachment = new Attachment(params)

        assert attachment.save() != null

        // test invalid parameters in update
        params.id = attachment.id
        params.fileName = ''

        controller.update()

        assert view == "/attachment/edit"
        assert model.attachmentInstance != null

        attachment.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/attachment/show/$attachment.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        attachment.clearErrors()

        populateValidParams(params)
        params.id = attachment.id
        params.version = -1
        controller.update()

        assert view == "/attachment/edit"
        assert model.attachmentInstance != null
        assert model.attachmentInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/attachment/list'

        response.reset()

        populateValidParams(params)
        def attachment = new Attachment(params)

        assert attachment.save() != null
        assert Attachment.count() == 1

        params.id = attachment.id

        controller.delete()

        assert Attachment.count() == 0
        assert Attachment.get(attachment.id) == null
        assert response.redirectedUrl == '/attachment/list'
    }
}
