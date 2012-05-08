package ca.airspeed.invoice



import org.junit.*
import grails.test.mixin.*

@TestFor(InvoiceRecipientController)
@Mock(InvoiceRecipient)
class InvoiceRecipientControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
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
        //TODO: add invalid values to params object

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
