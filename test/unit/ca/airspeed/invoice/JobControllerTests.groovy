package ca.airspeed.invoice



import org.junit.*
import grails.test.mixin.*

@TestFor(JobController)
@Mock(Job)
class JobControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
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
        //TODO: add invalid values to params object

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
