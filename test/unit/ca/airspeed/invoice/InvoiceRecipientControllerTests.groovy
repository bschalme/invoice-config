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
import grails.test.mixin.*

@TestFor(InvoiceRecipientController)
@Mock([InvoiceRecipient, Job, Customer, Company])
class InvoiceRecipientControllerTests {


	def populateValidParams(params) {
		assert params != null
		params["type"] = 'To'
		params["firstName"] = 'Some'
		params["lastName"] = 'Body'
		params["email"] = 'some.body@domain.com'
		def airspeed = new Company(name:'TEST Airspeed Consulting',
				address1:'25 Somewhere Ave.', city:'Winnipeg', province:'MB',
				postalCode:'R2M 0Y6', phone:'+1 (123) 555-1212', url:'http://www.airspeed.ca',
				invoiceFirstName:'Brian', invoiceLastName:'Schalme',
				invoiceEmail:'bschalme@airspeed.ca').save(failOnError: true)
		def megaCorp = new Customer(company:airspeed, customerRefListId:'334rddd2e234e',
				fullName:'MegaCorp', defaultDeliveryMethod:'Email').save(failOnError: true)
		def sonicEsb = new Job(customer:megaCorp, name:'Sonic ESB Integration',
				emailTemplatePlain:'/templates/MegaCorp/MegaCorpPlain',
				emailTemplateHtml:'/templates/MegaCorp/MegaCorpHtml').save(failOnError: true)
		params["job.id"] = Job.findByName('Sonic ESB Integration').id
	}

	void testIndex() {
		controller.index()
		assert "/invoiceRecipient/list" == response.redirectedUrl
	}

	void testList() {

		def model = controller.list()

		assert model.invoiceRecipientInstanceList.size() == 0
		assert model.invoiceRecipientInstanceTotal == 0
	}

	void testCreate() {
		def model = controller.create()

		assert model.invoiceRecipientInstance != null
	}

	void testSave() {
		controller.save()

		assert model.invoiceRecipientInstance != null
		assert view == '/invoiceRecipient/create'

		response.reset()

		populateValidParams(params)
		controller.save()

		assert response.redirectedUrl == '/invoiceRecipient/show/1'
		assert controller.flash.message != null
		assert InvoiceRecipient.count() == 1
	}

	void testShow() {
		controller.show()

		assert flash.message != null
		assert response.redirectedUrl == '/invoiceRecipient/list'


		populateValidParams(params)
		def invoiceRecipient = new InvoiceRecipient(params)

		assert invoiceRecipient.save() != null

		params.id = invoiceRecipient.id

		def model = controller.show()

		assert model.invoiceRecipientInstance == invoiceRecipient
	}

	void testEdit() {
		controller.edit()

		assert flash.message != null
		assert response.redirectedUrl == '/invoiceRecipient/list'


		populateValidParams(params)
		def invoiceRecipient = new InvoiceRecipient(params)

		assert invoiceRecipient.save() != null

		params.id = invoiceRecipient.id

		def model = controller.edit()

		assert model.invoiceRecipientInstance == invoiceRecipient
	}

	void testUpdate() {
		controller.update()

		assert flash.message != null
		assert response.redirectedUrl == '/invoiceRecipient/list'

		response.reset()


		populateValidParams(params)
		def invoiceRecipient = new InvoiceRecipient(params)

		assert invoiceRecipient.save() != null

		// test invalid parameters in update
		params.id = invoiceRecipient.id
		params.type = 'XX'

		controller.update()

		assert view == "/invoiceRecipient/edit"
		assert model.invoiceRecipientInstance != null

		invoiceRecipient.clearErrors()

		populateValidParams(params)
		controller.update()

		assert response.redirectedUrl == "/invoiceRecipient/show/$invoiceRecipient.id"
		assert flash.message != null

		//test outdated version number
		response.reset()
		invoiceRecipient.clearErrors()

		populateValidParams(params)
		params.id = invoiceRecipient.id
		params.version = -1
		controller.update()

		assert view == "/invoiceRecipient/edit"
		assert model.invoiceRecipientInstance != null
		assert model.invoiceRecipientInstance.errors.getFieldError('version')
		assert flash.message != null
	}

	void testDelete() {
		controller.delete()
		assert flash.message != null
		assert response.redirectedUrl == '/invoiceRecipient/list'

		response.reset()

		populateValidParams(params)
		def invoiceRecipient = new InvoiceRecipient(params)

		assert invoiceRecipient.save() != null
		assert InvoiceRecipient.count() == 1

		params.id = invoiceRecipient.id

		controller.delete()

		assert InvoiceRecipient.count() == 0
		assert InvoiceRecipient.get(invoiceRecipient.id) == null
		assert response.redirectedUrl == '/invoiceRecipient/list'
	}
}
