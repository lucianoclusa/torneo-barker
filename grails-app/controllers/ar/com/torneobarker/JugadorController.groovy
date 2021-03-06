package ar.com.torneobarker



import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class JugadorController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Jugador.list(params), model:[jugadorInstanceCount: Jugador.count()]
    }

    def show(Jugador jugadorInstance) {
        respond jugadorInstance
    }

    def create() {
        respond new Jugador(params)
    }

    @Transactional
    def save(Jugador jugadorInstance) {
        if (jugadorInstance == null) {
            notFound()
            return
        }

        if (jugadorInstance.hasErrors()) {
            respond jugadorInstance.errors, view:'create'
            return
        }

        jugadorInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'jugador.label', default: 'Jugador'), jugadorInstance.id])
                redirect jugadorInstance
            }
            '*' { respond jugadorInstance, [status: CREATED] }
        }
    }

    def edit(Jugador jugadorInstance) {
        respond jugadorInstance
    }

    @Transactional
    def update(Jugador jugadorInstance) {
        if (jugadorInstance == null) {
            notFound()
            return
        }

        if (jugadorInstance.hasErrors()) {
            respond jugadorInstance.errors, view:'edit'
            return
        }

        jugadorInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Jugador.label', default: 'Jugador'), jugadorInstance.id])
                redirect jugadorInstance
            }
            '*'{ respond jugadorInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Jugador jugadorInstance) {

        if (jugadorInstance == null) {
            notFound()
            return
        }

        jugadorInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Jugador.label', default: 'Jugador'), jugadorInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'jugador.label', default: 'Jugador'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
	
	def searchJugador(){
		List<Suspension> jugadores
		if(params?.jugador) {
			if(isNumeric(params.jugador)){
				def dni = params.jugador
				jugadores = Jugador.findAll{ dni == dni };
			}else{
				def likeJugador = "%" + params.jugador + "%"
				jugadores = Jugador.findAll{ nombre =~ likeJugador || apellido =~ likeJugador };
			}
		}else{
			jugadores = Jugador.findAll();
		}

		render(view:'index', model:['jugadorInstanceList':jugadores,
			'jugador':params?.jugador])

	}
	
	private static boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		}
		catch(NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
