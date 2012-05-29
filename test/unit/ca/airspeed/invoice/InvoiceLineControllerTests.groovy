package ca.airspeed.invoice



import org.junit.*
import grails.test.mixin.*

@TestFor(InvoiceLineController)
@Mock(InvoiceLine)
class InvoiceLineControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
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
        //TODO: add invalid values to params object

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
