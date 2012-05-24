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

@TestFor(JobController)
@Mock([Job, Customer, Company])
class JobControllerTests {


    def populateValidParams(params) {
      assert params != null
      params["name"] = 'Replace Roof Shingles'
	  params["emailTemplateHtml"] = '/templates/html.gsp'
	  params["emailTemplatePlain"] = '/templates/plain.gsp'
	  def airspeed = new Company(name:'TEST Airspeed Consulting', address1:'25 Somewhere Ave.', city:'Winnipeg', province:'MB', postalCode:'R2M 0Y6', phone:'+1 (123) 555-1212', url:'http://www.airspeed.ca', invoiceFirstName:'Brian', invoiceLastName:'Schalme', invoiceEmail:'bschalme@airspeed.ca').save(failOnError: true)
	  def megaCorp = new Customer(company:airspeed, customerRefListId:'334rddd2e234e', fullName:'MegaCorp', defaultDeliveryMethod:'Email').save(failOnError: true)
	  params["customer.id"] = Customer.findByFullName('MegaCorp').id
	  
    }

    void testIndex() {
        controller.index()
        assert "/job/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.jobInstanceList.size() == 0
        assert model.jobInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.jobInstance != null
    }

    void testSave() {
        controller.save()

        assert model.jobInstance != null
        assert view == '/job/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/job/show/1'
        assert controller.flash.message != null
        assert Job.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/job/list'


        populateValidParams(params)
        def job = new Job(params)

        assert job.save() != null

        params.id = job.id

        def model = controller.show()

        assert model.jobInstance == job
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/job/list'


        populateValidParams(params)
        def job = new Job(params)

        assert job.save() != null

        params.id = job.id

        def model = controller.edit()

        assert model.jobInstance == job
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/job/list'

        response.reset()


        populateValidParams(params)
        def job = new Job(params)

        assert job.save() != null

        // test invalid parameters in update
        params.id = job.id
        params.emailTemplateHtml = ''

        controller.update()

        assert view == "/job/edit"
        assert model.jobInstance != null

        job.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/job/show/$job.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        job.clearErrors()

        populateValidParams(params)
        params.id = job.id
        params.version = -1
        controller.update()

        assert view == "/job/edit"
        assert model.jobInstance != null
        assert model.jobInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/job/list'

        response.reset()

        populateValidParams(params)
        def job = new Job(params)

        assert job.save() != null
        assert Job.count() == 1

        params.id = job.id

        controller.delete()

        assert Job.count() == 0
        assert Job.get(job.id) == null
        assert response.redirectedUrl == '/job/list'
    }
}
