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

@TestFor(TenantController)
@Mock(Tenant)
class TenantControllerTests {


    def populateValidParams(params) {
      assert params != null
	  params["name"] = '4020774 Manitoba Ltd.'
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
		params.name = ''
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
