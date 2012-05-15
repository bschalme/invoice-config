package ca.airspeed.invoice



import org.junit.*

import ca.airspeed.invoice.Invoice;
import ca.airspeed.invoice.InvoiceController;
import ca.airspeed.invoice.Job;
import grails.test.mixin.*

@TestFor(InvoiceController)
@Mock([Invoice, Job, Customer, Company, Tenant])
class InvoiceControllerTests {


	def populateValidParams(params) {
		assert params != null
		params["invoiceNumber"] = '362'
		params["deliveryStatus"] = 'ToBeDelivered'
		params["deliveryMethod"] = 'Email'
		params["emailTemplateHtml"] = ' '
		params["emailTemplatePlain"] = '/tmp/blart'
		if (Job.list().size() == 0) {
			seedDb()
		}
		params["job.id"] = Job.get(1).id
	}

	void seedDb() {
		def tenant = new Tenant(name:'4020774 Manitoba Ltd.').save(failOnError: true)
		def airspeed = new Company(tenant:tenant, name:'Airspeed Consulting', invoiceFirstName:'Brian', invoiceLastName:'Schalme', invoiceEmail:'bschalme@airspeed.ca').save(failOnError: true)
		def megaCorp = new Customer(company:airspeed, customerRefListId:'334rddd2e234e', fullName:'MegaCorp', defaultDeliveryMethod:'Email').save(failOnError: true)
		def sonicEsb = new Job(customer:megaCorp, name:'Sonic ESB Integration').save(flush: true, failOnError: true)
	}

	void testIndex() {
		controller.index()
		assert "/invoice/list" == response.redirectedUrl
	}

	void testList() {

		def model = controller.list()

		assert model.invoiceInstanceList.size() == 0
		assert model.invoiceInstanceTotal == 0
	}

	void testCreate() {
		def model = controller.create()

		assert model.invoiceInstance != null
	}

	void testSave() {
		controller.save()

		assert model.invoiceInstance != null
		assert view == '/invoice/create'

		response.reset()

		populateValidParams(params)
		controller.save()

		assert response.redirectedUrl == '/invoice/show/1'
		assert controller.flash.message != null
		assert Invoice.count() == 1
	}

	void testShow() {
		controller.show()

		assert flash.message != null
		assert response.redirectedUrl == '/invoice/list'


		populateValidParams(params)
		def invoice = new Invoice(params)

		assert invoice.save() != null

		params.id = invoice.id

		def model = controller.show()

		assert model.invoiceInstance == invoice
	}

	void testEdit() {
		controller.edit()

		assert flash.message != null
		assert response.redirectedUrl == '/invoice/list'


		populateValidParams(params)
		def invoice = new Invoice(params)

		assert invoice.save() != null

		params.id = invoice.id

		def model = controller.edit()

		assert model.invoiceInstance == invoice
	}

	void testUpdate() {
		controller.update()

		assert flash.message != null
		assert response.redirectedUrl == '/invoice/list'

		response.reset()


		populateValidParams(params)
		def invoice = new Invoice(params)

		assert invoice.save() != null

		// test invalid parameters in update
		params.id = invoice.id
		params.deliveryMethod = 'NoSuchDeliveryMethod'

		controller.update()

		assert view == "/invoice/edit"
		assert model.invoiceInstance != null

		invoice.clearErrors()

		populateValidParams(params)
		controller.update()

		assert response.redirectedUrl == "/invoice/show/$invoice.id"
		assert flash.message != null

		//test outdated version number
		response.reset()
		invoice.clearErrors()

		populateValidParams(params)
		params.id = invoice.id
		params.version = -1
		controller.update()

		assert view == "/invoice/edit"
		assert model.invoiceInstance != null
		assert model.invoiceInstance.errors.getFieldError('version')
		assert flash.message != null
	}

	void testDelete() {
		controller.delete()
		assert flash.message != null
		assert response.redirectedUrl == '/invoice/list'

		response.reset()

		populateValidParams(params)
		def invoice = new Invoice(params)

		assert invoice.save() != null
		assert Invoice.count() == 1

		params.id = invoice.id

		controller.delete()

		assert Invoice.count() == 0
		assert Invoice.get(invoice.id) == null
		assert response.redirectedUrl == '/invoice/list'
	}
	
	void testEmail() {
		assert controller.email() != null
	}
}
