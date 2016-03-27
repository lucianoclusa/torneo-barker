package ar.com.torneobarker



import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

import org.springframework.security.access.annotation.Secured

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class SuspensionEstadoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SuspensionEstado.list(params), model:[suspensionEstadoInstanceCount: SuspensionEstado.count()]
    }

    def show(SuspensionEstado suspensionEstadoInstance) {
        respond suspensionEstadoInstance
    }

    def create() {
        respond new SuspensionEstado(params)
    }

    @Transactional
    def save(SuspensionEstado suspensionEstadoInstance) {
        if (suspensionEstadoInstance == null) {
            notFound()
            return
        }

        if (suspensionEstadoInstance.hasErrors()) {
            respond suspensionEstadoInstance.errors, view:'create'
            return
        }

        suspensionEstadoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'suspensionEstado.label', default: 'SuspensionEstado'), suspensionEstadoInstance.id])
                redirect suspensionEstadoInstance
            }
            '*' { respond suspensionEstadoInstance, [status: CREATED] }
        }
    }

    def edit(SuspensionEstado suspensionEstadoInstance) {
        respond suspensionEstadoInstance
    }

    @Transactional
    def update(SuspensionEstado suspensionEstadoInstance) {
        if (suspensionEstadoInstance == null) {
            notFound()
            return
        }

        if (suspensionEstadoInstance.hasErrors()) {
            respond suspensionEstadoInstance.errors, view:'edit'
            return
        }

        suspensionEstadoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'SuspensionEstado.label', default: 'SuspensionEstado'), suspensionEstadoInstance.id])
                redirect suspensionEstadoInstance
            }
            '*'{ respond suspensionEstadoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(SuspensionEstado suspensionEstadoInstance) {

        if (suspensionEstadoInstance == null) {
            notFound()
            return
        }

        suspensionEstadoInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'SuspensionEstado.label', default: 'SuspensionEstado'), suspensionEstadoInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'suspensionEstado.label', default: 'SuspensionEstado'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
