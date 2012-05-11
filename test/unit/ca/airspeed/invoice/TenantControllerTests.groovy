package ca.airspeed.invoice




import org.junit.*

import ca.airspeed.invoice.Tenant;
import ca.airspeed.invoice.TenantController;
import grails.test.mixin.*

@TestFor(TenantController)
@Mock(Tenant)
class TenantControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/tenant/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.tenantInstanceList.size() == 0
        assert model.tenantInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.tenantInstance != null
    }

    void testSave() {
        controller.save()

        assert model.tenantInstance != null
        assert view == '/tenant/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/tenant/show/1'
        assert controller.flash.message != null
        assert Tenant.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/tenant/list'


        populateValidParams(params)
        def tenant = new Tenant(params)

        assert tenant.save() != null

        params.id = tenant.id

        def model = controller.show()

        assert model.tenantInstance == tenant
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/tenant/list'


        populateValidParams(params)
        def tenant = new Tenant(params)

        assert tenant.save() != null

        params.id = tenant.id

        def model = controller.edit()

        assert model.tenantInstance == tenant
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/tenant/list'

        response.reset()


        populateValidParams(params)
        def tenant = new Tenant(params)

        assert tenant.save() != null

        // test invalid parameters in update
        params.id = tenant.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/tenant/edit"
        assert model.tenantInstance != null

        tenant.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/tenant/show/$tenant.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        tenant.clearErrors()

        populateValidParams(params)
        params.id = tenant.id
        params.version = -1
        controller.update()

        assert view == "/tenant/edit"
        assert model.tenantInstance != null
        assert model.tenantInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/tenant/list'

        response.reset()

        populateValidParams(params)
        def tenant = new Tenant(params)

        assert tenant.save() != null
        assert Tenant.count() == 1

        params.id = tenant.id

        controller.delete()

        assert Tenant.count() == 0
        assert Tenant.get(tenant.id) == null
        assert response.redirectedUrl == '/tenant/list'
    }
}
