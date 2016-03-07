package ar.com.torneobarker



import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class GolController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Gol.list(params), model:[golInstanceCount: Gol.count()]
    }

    def show(Gol golInstance) {
        respond golInstance
    }

    def create() {
        respond new Gol(params)
    }

    @Transactional
    def save(Gol golInstance) {
        if (golInstance == null) {
            notFound()
            return
        }

        if (golInstance.hasErrors()) {
            respond golInstance.errors, view:'create'
            return
        }

        golInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'gol.label', default: 'Gol'), golInstance.id])
                redirect golInstance
            }
            '*' { respond golInstance, [status: CREATED] }
        }
    }

    def edit(Gol golInstance) {
        respond golInstance
    }

    @Transactional
    def update(Gol golInstance) {
        if (golInstance == null) {
            notFound()
            return
        }

        if (golInstance.hasErrors()) {
            respond golInstance.errors, view:'edit'
            return
        }

        golInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Gol.label', default: 'Gol'), golInstance.id])
                redirect golInstance
            }
            '*'{ respond golInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Gol golInstance) {

        if (golInstance == null) {
            notFound()
            return
        }

        golInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Gol.label', default: 'Gol'), golInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'gol.label', default: 'Gol'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
