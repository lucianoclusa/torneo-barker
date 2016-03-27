package ar.com.torneobarker



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CanchaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Cancha.list(params), model:[canchaInstanceCount: Cancha.count()]
    }

    def show(Cancha canchaInstance) {
        respond canchaInstance
    }

    def create() {
        respond new Cancha(params)
    }

    @Transactional
    def save(Cancha canchaInstance) {
        if (canchaInstance == null) {
            notFound()
            return
        }

        if (canchaInstance.hasErrors()) {
            respond canchaInstance.errors, view:'create'
            return
        }

        canchaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'cancha.label', default: 'Cancha'), canchaInstance.id])
                redirect canchaInstance
            }
            '*' { respond canchaInstance, [status: CREATED] }
        }
    }

    def edit(Cancha canchaInstance) {
        respond canchaInstance
    }

    @Transactional
    def update(Cancha canchaInstance) {
        if (canchaInstance == null) {
            notFound()
            return
        }

        if (canchaInstance.hasErrors()) {
            respond canchaInstance.errors, view:'edit'
            return
        }

        canchaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Cancha.label', default: 'Cancha'), canchaInstance.id])
                redirect canchaInstance
            }
            '*'{ respond canchaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Cancha canchaInstance) {

        if (canchaInstance == null) {
            notFound()
            return
        }

        canchaInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Cancha.label', default: 'Cancha'), canchaInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cancha.label', default: 'Cancha'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
