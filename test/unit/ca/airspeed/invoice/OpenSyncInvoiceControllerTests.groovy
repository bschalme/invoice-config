package ca.airspeed.invoice



import org.junit.*
import grails.test.mixin.*

@TestFor(OpenSyncInvoiceController)
@Mock(OpenSyncInvoice)
class OpenSyncInvoiceControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/openSyncInvoice/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.openSyncInvoiceInstanceList.size() == 0
        assert model.openSyncInvoiceInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.openSyncInvoiceInstance != null
    }

    void testSave() {
        controller.save()

        assert model.openSyncInvoiceInstance != null
        assert view == '/openSyncInvoice/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/openSyncInvoice/show/1'
        assert controller.flash.message != null
        assert OpenSyncInvoice.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/openSyncInvoice/list'


        populateValidParams(params)
        def openSyncInvoice = new OpenSyncInvoice(params)

        assert openSyncInvoice.save() != null

        params.id = openSyncInvoice.id

        def model = controller.show()

        assert model.openSyncInvoiceInstance == openSyncInvoice
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/openSyncInvoice/list'


        populateValidParams(params)
        def openSyncInvoice = new OpenSyncInvoice(params)

        assert openSyncInvoice.save() != null

        params.id = openSyncInvoice.id

        def model = controller.edit()

        assert model.openSyncInvoiceInstance == openSyncInvoice
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/openSyncInvoice/list'

        response.reset()


        populateValidParams(params)
        def openSyncInvoice = new OpenSyncInvoice(params)

        assert openSyncInvoice.save() != null

        // test invalid parameters in update
        params.id = openSyncInvoice.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/openSyncInvoice/edit"
        assert model.openSyncInvoiceInstance != null

        openSyncInvoice.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/openSyncInvoice/show/$openSyncInvoice.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        openSyncInvoice.clearErrors()

        populateValidParams(params)
        params.id = openSyncInvoice.id
        params.version = -1
        controller.update()

        assert view == "/openSyncInvoice/edit"
        assert model.openSyncInvoiceInstance != null
        assert model.openSyncInvoiceInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/openSyncInvoice/list'

        response.reset()

        populateValidParams(params)
        def openSyncInvoice = new OpenSyncInvoice(params)

        assert openSyncInvoice.save() != null
        assert OpenSyncInvoice.count() == 1

        params.id = openSyncInvoice.id

        controller.delete()

        assert OpenSyncInvoice.count() == 0
        assert OpenSyncInvoice.get(openSyncInvoice.id) == null
        assert response.redirectedUrl == '/openSyncInvoice/list'
    }
}
