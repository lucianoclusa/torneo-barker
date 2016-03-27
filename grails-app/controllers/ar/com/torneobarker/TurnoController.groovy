package ar.com.torneobarker



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TurnoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Turno.list(params), model:[turnoInstanceCount: Turno.count()]
    }

    def show(Turno turnoInstance) {
        respond turnoInstance
    }

    def create() {
        respond new Turno(params)
    }

    @Transactional
    def save(Turno turnoInstance) {
        if (turnoInstance == null) {
            notFound()
            return
        }

        if (turnoInstance.hasErrors()) {
            respond turnoInstance.errors, view:'create'
            return
        }

        turnoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'turno.label', default: 'Turno'), turnoInstance.id])
                redirect turnoInstance
            }
            '*' { respond turnoInstance, [status: CREATED] }
        }
    }

    def edit(Turno turnoInstance) {
        respond turnoInstance
    }

    @Transactional
    def update(Turno turnoInstance) {
        if (turnoInstance == null) {
            notFound()
            return
        }

        if (turnoInstance.hasErrors()) {
            respond turnoInstance.errors, view:'edit'
            return
        }

        turnoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Turno.label', default: 'Turno'), turnoInstance.id])
                redirect turnoInstance
            }
            '*'{ respond turnoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Turno turnoInstance) {

        if (turnoInstance == null) {
            notFound()
            return
        }

        turnoInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Turno.label', default: 'Turno'), turnoInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'turno.label', default: 'Turno'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
