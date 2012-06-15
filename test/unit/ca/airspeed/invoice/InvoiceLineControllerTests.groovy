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

@TestFor(InvoiceLineController)
@Mock([InvoiceLine, Invoice, Job, InvoiceRecipient, Customer, Company])
class InvoiceLineControllerTests {


    def populateValidParams(params) {
      assert params != null
      params["quantity"] ='4.0'
	  params["itemName"] = 'PS2'
	  params["description"] = 'Professional Services'
	  params["rate"] = '100.0'
	  params["amount"] = '400.0'
	  if (Invoice.list().size() == 0) {
		  Seed.seedDb()
		  def inv362 = new Invoice(job:Job.get(1), invoiceNumber:'362', deliveryStatus:'ToBeDelivered', deliveryMethod:'Email', emailTemplateHtml:'', emailTemplatePlain:'').save(failOnError: true)
	  }
	  params["invoice.id"] = Invoice.findByInvoiceNumber('362').id
    }

    void testIndex() {
        controller.index()
        assert "/invoiceLine/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.invoiceLineInstanceList.size() == 0
        assert model.invoiceLineInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.invoiceLineInstance != null
    }

    void testSave() {
        controller.save()

        assert model.invoiceLineInstance != null
        assert view == '/invoiceLine/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/invoiceLine/show/1'
        assert controller.flash.message != null
        assert InvoiceLine.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/invoiceLine/list'


        populateValidParams(params)
        def invoiceLine = new InvoiceLine(params)

        assert invoiceLine.save() != null

        params.id = invoiceLine.id

        def model = controller.show()

        assert model.invoiceLineInstance == invoiceLine
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/invoiceLine/list'


        populateValidParams(params)
        def invoiceLine = new InvoiceLine(params)

        assert invoiceLine.save() != null

        params.id = invoiceLine.id

        def model = controller.edit()

        assert model.invoiceLineInstance == invoiceLine
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/invoiceLine/list'

        response.reset()


        populateValidParams(params)
        def invoiceLine = new InvoiceLine(params)

        assert invoiceLine.save() != null

        // test invalid parameters in update
        params.id = invoiceLine.id
		params.itemName = ''

        controller.update()

        assert view == "/invoiceLine/edit"
        assert model.invoiceLineInstance != null

        invoiceLine.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/invoiceLine/show/$invoiceLine.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        invoiceLine.clearErrors()

        populateValidParams(params)
        params.id = invoiceLine.id
        params.version = -1
        controller.update()

        assert view == "/invoiceLine/edit"
        assert model.invoiceLineInstance != null
        assert model.invoiceLineInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/invoiceLine/list'

        response.reset()

        populateValidParams(params)
        def invoiceLine = new InvoiceLine(params)

        assert invoiceLine.save() != null
        assert InvoiceLine.count() == 1

        params.id = invoiceLine.id

        controller.delete()

        assert InvoiceLine.count() == 0
        assert InvoiceLine.get(invoiceLine.id) == null
        assert response.redirectedUrl == '/invoiceLine/list'
    }
}
