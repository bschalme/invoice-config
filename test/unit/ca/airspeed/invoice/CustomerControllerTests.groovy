package ca.airspeed.invoice



import org.junit.*
import grails.test.mixin.*

@TestFor(CustomerController)
@Mock([Customer, Company])
class CustomerControllerTests {


    def populateValidParams(params) {
      assert params != null
	  params["customerRefListId"] = '334rddd2e234e'
	  params["fullName"] = 'MegaCorp'
	  params["defaultDeliveryMethod"] = 'Email'
	  def airspeed = new Company(name:'Airspeed', 
		  address1:'25 Somewhere Ave.', 
		  city:'Winnipeg', 
		  province:'MB', 
		  postalCode:'R2M 0Y6', 
		  phone:'+1 (123) 555-1212', 
		  url:'http://www.airspeed.ca',
		  invoiceFirstName:'Brian', 
		  invoiceLastName:'Schalme', 
		  invoiceEmail:'bschalme@airspeed.ca').save(failOnError: true)
	  params["company.id"] = Company.findByName('Airspeed').id
    }

    void testIndex() {
        controller.index()
        assert "/customer/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.customerInstanceList.size() == 0
        assert model.customerInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.customerInstance != null
    }

    void testSave() {
        controller.save()

        assert model.customerInstance != null
        assert view == '/customer/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/customer/show/1'
        assert controller.flash.message != null
        assert Customer.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/customer/list'


        populateValidParams(params)
        def customer = new Customer(params)

        assert customer.save() != null

        params.id = customer.id

        def model = controller.show()

        assert model.customerInstance == customer
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/customer/list'


        populateValidParams(params)
        def customer = new Customer(params)

        assert customer.save() != null

        params.id = customer.id

        def model = controller.edit()

        assert model.customerInstance == customer
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/customer/list'

        response.reset()


        populateValidParams(params)
        def customer = new Customer(params)

        assert customer.save() != null

        // test invalid parameters in update
        params.id = customer.id
        params.fullName = ''

        controller.update()

        assert view == "/customer/edit"
        assert model.customerInstance != null

        customer.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/customer/show/$customer.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        customer.clearErrors()

        populateValidParams(params)
        params.id = customer.id
        params.version = -1
        controller.update()

        assert view == "/customer/edit"
        assert model.customerInstance != null
        assert model.customerInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/customer/list'

        response.reset()

        populateValidParams(params)
        def customer = new Customer(params)

        assert customer.save() != null
        assert Customer.count() == 1

        params.id = customer.id

        controller.delete()

        assert Customer.count() == 0
        assert Customer.get(customer.id) == null
        assert response.redirectedUrl == '/customer/list'
    }
}
