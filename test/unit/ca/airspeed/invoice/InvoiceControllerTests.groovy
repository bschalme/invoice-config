/*
    Copyright 2012 Airspeed Consulting

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */
package ca.airspeed.invoice



import org.junit.*

import ca.airspeed.common.EmailerService;
import ca.airspeed.invoice.Invoice;
import ca.airspeed.invoice.InvoiceController;
import ca.airspeed.invoice.Job;
import grails.test.mixin.*

@TestFor(InvoiceController)
@Mock([Invoice, Job, , InvoiceRecipient, Customer, Company, Tenant])
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
		def airspeed = new Company(tenant:tenant, name:'TEST Airspeed Consulting', address1:'25 Somewhere Ave.', city:'Winnipeg', province:'MB', postalCode:'R2M 0Y6', phone:'+1 (123) 555-1212', url:'http://www.airspeed.ca', invoiceFirstName:'Brian', invoiceLastName:'Schalme', invoiceEmail:'bschalme@airspeed.ca').save(failOnError: true)
		def megaCorp = new Customer(company:airspeed, customerRefListId:'334rddd2e234e', fullName:'MegaCorp', defaultDeliveryMethod:'Email').save(failOnError: true)
		def sonicEsb = new Job(customer:megaCorp, name:'Sonic ESB Integration', emailTemplatePlain:'/templates/MegaCorp/MegaCorpPlain', emailTemplateHtml:'/templates/MegaCorp/MegaCorpHtml').save(failOnError: true)
		def toRecipient = new InvoiceRecipient(job: sonicEsb, type: 'To', firstName: 'Joe', lastName: 'Director', email: 'jdirector@megacorp.com').save(failOnError: true)
		def ccRecipient = new InvoiceRecipient(job: sonicEsb, type: 'Cc', firstName: 'Jane', lastName: 'Vendor', email: 'jvendor@megacorp.com').save(failOnError: true)
		
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
		populateValidParams(params)
		def invoice = new Invoice(params)

		assert invoice.save() != null
		assert Invoice.count() == 1

		params.id = invoice.id
		
		def emailControl = mockFor(EmailerService)
		emailControl.demand.emailInvoice {Map mail ->
			assert mail.to[0].toString() == 'Joe Director <jdirector@megacorp.com>'
			assert mail.from == 'Brian Schalme <bschalme@airspeed.ca>'
			assert mail.cc[0].toString() == 'Jane Vendor <jvendor@megacorp.com>'
			assert mail.subject == "Invoice #${invoice.invoiceNumber} from ${invoice.job.customer.company.name}"
			assert mail.attachments[0].name == 'grails_logo.jpg'
			return
		}
		controller.emailerService = emailControl.createMock()
		
		controller.email()

		assert invoice.deliveryStatus == 'Delivered'
	}
}
