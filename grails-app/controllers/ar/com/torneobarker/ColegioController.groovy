package ar.com.torneobarker



import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class ColegioController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Colegio.list(params), model:[colegioInstanceCount: Colegio.count()]
    }

    def show(Colegio colegioInstance) {
        respond colegioInstance
    }

    def create() {
        respond new Colegio(params)
    }

    @Transactional
    def save(Colegio colegioInstance) {
        if (colegioInstance == null) {
            notFound()
            return
        }

        if (colegioInstance.hasErrors()) {
            respond colegioInstance.errors, view:'create'
            return
        }

        colegioInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'colegio.label', default: 'Colegio'), colegioInstance.id])
                redirect colegioInstance
            }
            '*' { respond colegioInstance, [status: CREATED] }
        }
    }

    def edit(Colegio colegioInstance) {
        respond colegioInstance
    }

    @Transactional
    def update(Colegio colegioInstance) {
        if (colegioInstance == null) {
            notFound()
            return
        }

        if (colegioInstance.hasErrors()) {
            respond colegioInstance.errors, view:'edit'
            return
        }

        colegioInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Colegio.label', default: 'Colegio'), colegioInstance.id])
                redirect colegioInstance
            }
            '*'{ respond colegioInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Colegio colegioInstance) {

        if (colegioInstance == null) {
            notFound()
            return
        }

        colegioInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Colegio.label', default: 'Colegio'), colegioInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'colegio.label', default: 'Colegio'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
