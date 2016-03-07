package ar.com.torneobarker



import grails.test.mixin.*
import spock.lang.*

@TestFor(AnioController)
@Mock(Anio)
class AnioControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.anioInstanceList
            model.anioInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.anioInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def anio = new Anio()
            anio.validate()
            controller.save(anio)

        then:"The create view is rendered again with the correct model"
            model.anioInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            anio = new Anio(params)

            controller.save(anio)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/anio/show/1'
            controller.flash.message != null
            Anio.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def anio = new Anio(params)
            controller.show(anio)

        then:"A model is populated containing the domain instance"
            model.anioInstance == anio
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def anio = new Anio(params)
            controller.edit(anio)

        then:"A model is populated containing the domain instance"
            model.anioInstance == anio
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/anio/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def anio = new Anio()
            anio.validate()
            controller.update(anio)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.anioInstance == anio

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            anio = new Anio(params).save(flush: true)
            controller.update(anio)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/anio/show/$anio.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/anio/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def anio = new Anio(params).save(flush: true)

        then:"It exists"
            Anio.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(anio)

        then:"The instance is deleted"
            Anio.count() == 0
            response.redirectedUrl == '/anio/index'
            flash.message != null
    }
}
