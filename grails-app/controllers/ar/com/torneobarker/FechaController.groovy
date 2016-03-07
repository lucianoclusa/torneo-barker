package ar.com.torneobarker



import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class FechaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Fecha.list(params), model:[fechaInstanceCount: Fecha.count()]
    }

    def show(Fecha fechaInstance) {
        respond fechaInstance
    }

    def create() {
        respond new Fecha(params)
    }

    @Transactional
    def save(Fecha fechaInstance) {
        if (fechaInstance == null) {
            notFound()
            return
        }

        if (fechaInstance.hasErrors()) {
            respond fechaInstance.errors, view:'create'
            return
        }

        fechaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'fecha.label', default: 'Fecha'), fechaInstance.id])
                redirect fechaInstance
            }
            '*' { respond fechaInstance, [status: CREATED] }
        }
    }

    def edit(Fecha fechaInstance) {
        respond fechaInstance
    }

    @Transactional
    def update(Fecha fechaInstance) {
        if (fechaInstance == null) {
            notFound()
            return
        }

        if (fechaInstance.hasErrors()) {
            respond fechaInstance.errors, view:'edit'
            return
        }

        fechaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Fecha.label', default: 'Fecha'), fechaInstance.id])
                redirect fechaInstance
            }
            '*'{ respond fechaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Fecha fechaInstance) {

        if (fechaInstance == null) {
            notFound()
            return
        }

        fechaInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Fecha.label', default: 'Fecha'), fechaInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'fecha.label', default: 'Fecha'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
