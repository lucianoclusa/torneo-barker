package ar.com.torneobarker



import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class AnioController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Anio.list(params), model:[anioInstanceCount: Anio.count()]
    }

    def show(Anio anioInstance) {
        respond anioInstance
    }

    def create() {
        respond new Anio(params)
    }

    @Transactional
    def save(Anio anioInstance) {
        if (anioInstance == null) {
            notFound()
            return
        }

        if (anioInstance.hasErrors()) {
            respond anioInstance.errors, view:'create'
            return
        }

        anioInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'anio.label', default: 'Anio'), anioInstance.id])
                redirect anioInstance
            }
            '*' { respond anioInstance, [status: CREATED] }
        }
    }

    def edit(Anio anioInstance) {
        respond anioInstance
    }

    @Transactional
    def update(Anio anioInstance) {
        if (anioInstance == null) {
            notFound()
            return
        }

        if (anioInstance.hasErrors()) {
            respond anioInstance.errors, view:'edit'
            return
        }

        anioInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Anio.label', default: 'Anio'), anioInstance.id])
                redirect anioInstance
            }
            '*'{ respond anioInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Anio anioInstance) {

        if (anioInstance == null) {
            notFound()
            return
        }

        anioInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Anio.label', default: 'Anio'), anioInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'anio.label', default: 'Anio'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
