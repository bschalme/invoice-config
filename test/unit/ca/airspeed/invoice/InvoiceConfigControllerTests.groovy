package ca.airspeed.invoice



import org.junit.*
import grails.test.mixin.*

@TestFor(InvoiceConfigController)
@Mock(InvoiceConfig)
class InvoiceConfigControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/invoiceConfig/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.invoiceConfigInstanceList.size() == 0
        assert model.invoiceConfigInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.invoiceConfigInstance != null
    }

    void testSave() {
        controller.save()

        assert model.invoiceConfigInstance != null
        assert view == '/invoiceConfig/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/invoiceConfig/show/1'
        assert controller.flash.message != null
        assert InvoiceConfig.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/invoiceConfig/list'


        populateValidParams(params)
        def invoiceConfig = new InvoiceConfig(params)

        assert invoiceConfig.save() != null

        params.id = invoiceConfig.id

        def model = controller.show()

        assert model.invoiceConfigInstance == invoiceConfig
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/invoiceConfig/list'


        populateValidParams(params)
        def invoiceConfig = new InvoiceConfig(params)

        assert invoiceConfig.save() != null

        params.id = invoiceConfig.id

        def model = controller.edit()

        assert model.invoiceConfigInstance == invoiceConfig
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/invoiceConfig/list'

        response.reset()


        populateValidParams(params)
        def invoiceConfig = new InvoiceConfig(params)

        assert invoiceConfig.save() != null

        // test invalid parameters in update
        params.id = invoiceConfig.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/invoiceConfig/edit"
        assert model.invoiceConfigInstance != null

        invoiceConfig.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/invoiceConfig/show/$invoiceConfig.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        invoiceConfig.clearErrors()

        populateValidParams(params)
        params.id = invoiceConfig.id
        params.version = -1
        controller.update()

        assert view == "/invoiceConfig/edit"
        assert model.invoiceConfigInstance != null
        assert model.invoiceConfigInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/invoiceConfig/list'

        response.reset()

        populateValidParams(params)
        def invoiceConfig = new InvoiceConfig(params)

        assert invoiceConfig.save() != null
        assert InvoiceConfig.count() == 1

        params.id = invoiceConfig.id

        controller.delete()

        assert InvoiceConfig.count() == 0
        assert InvoiceConfig.get(invoiceConfig.id) == null
        assert response.redirectedUrl == '/invoiceConfig/list'
    }
}
