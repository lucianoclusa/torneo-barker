package ar.com.torneobarker



import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class SuspensionController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Suspension.list(params), model:[suspensionInstanceCount: Suspension.count()]
	}

	def show(Suspension suspensionInstance) {
		respond suspensionInstance
	}

	def create() {
		respond new Suspension(params)
	}

	def torneoChanged(long torneoId) {
		Torneo torneo = Torneo.get(torneoId)
		def fechas = []
		if ( torneo != null ) {
			fechas = Fecha.findAllByTorneo(torneo)
		}
		if(fechas==null || fechas.size()==0){
			render "<select><option>-Sin equipo-</option></select>"
		}else{
			render g.select(id:'fechas', name:'fecha.id',
			from:fechas, optionKey:'id')
		}
		
	}
	def jugadorChanged(long jugadorId) {
		Jugador jugador = Jugador.get(jugadorId)
		def equipos = []
		if ( jugador != null ) {
			equipos = ar.com.torneobarker.Equipo.findAll("from Equipo e where ? in elements(e.jugadores)", [jugador])
		}
		if(equipos==null || equipos.size()==0){
			render "<select><option>-Sin equipo-</option></select>"
		}else{
			render g.select(id:'equipo', name:'equipo.id',
			from:equipos, optionKey:'id', class:'many-to-one', onchange:'equipoChanged(this.value)', noSelection:"['':'-Sin equipo-']")
		}
	}
	def equipoChanged(long equipoId) {
		Equipo equipo = Equipo.get(equipoId)
		def torneos = []
		if ( equipo != null ) {
			torneos = ar.com.torneobarker.Torneo.findAll("from Torneo e where ? in elements(e.equipos)", [equipo])
		}
		if(torneos==null || torneos.size()==0){
			render "<select><option>-Sin equipo-</option></select>"
		}else{
			render g.select(id:'torneo', name:'torneo.id',
			from:torneos, optionKey:'id', class:'many-to-one', onchange:'torneoChanged(this.value)', noSelection:"['':'-Sin equipo-']")
		}
	}

	@Transactional
	def save(Suspension suspensionInstance) {
		if (suspensionInstance == null) {
			notFound()
			return
		}

		if (suspensionInstance.hasErrors()) {
			respond suspensionInstance.errors, view:'create'
			return
		}

		if(suspensionInstance.partido==null && suspensionInstance.fecha!=null){
			List<Partido> partidos = Partido.findAllByFecha(suspensionInstance.fecha)
			for(Partido partido : partidos){
				if(suspensionInstance.equipo.equals(partido.local)|| suspensionInstance.equipo.equals(partido.visitante)){
					suspensionInstance.partido = partido
				}
			}
		}
		
		suspensionInstance.cantPartidosRestantes = suspensionInstance.cantFechas
		
		suspensionInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'suspension.label', default: 'Suspension'), suspensionInstance.id])
				redirect suspensionInstance
			}
			'*' { respond suspensionInstance, [status: CREATED] }
		}
	}

	def edit(Suspension suspensionInstance) {
		respond suspensionInstance
	}

	@Transactional
	def update(Suspension suspensionInstance) {
		if (suspensionInstance == null) {
			notFound()
			return
		}


		if(suspensionInstance.partido==null && suspensionInstance.fecha!=null){
			List<Partido> partidos = Partido.findAllByFecha(suspensionInstance.fecha)
			for(Partido partido : partidos){
				if(suspensionInstance.equipo.equals(partido.local)|| suspensionInstance.equipo.equals(partido.visitante)){
					suspensionInstance.partido = partido
				}
			}
		}
		
		suspensionInstance.cantPartidosRestantes = suspensionInstance.cantFechas
	
		suspensionInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'Suspension.label', default: 'Suspension'), suspensionInstance.id])
				redirect suspensionInstance
			}
			'*'{ respond suspensionInstance, [status: OK] }
		}
	}

	@Transactional
	def delete(Suspension suspensionInstance) {

		if (suspensionInstance == null) {
			notFound()
			return
		}

		suspensionInstance.delete flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'Suspension.label', default: 'Suspension'), suspensionInstance.id])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'suspension.label', default: 'Suspension'), params.id])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}


	def searchSuspensiones(){
		List<Suspension> suspensiones
		if(params?.jugador) {
			if(isNumeric(params.jugador)){
				def dni = params.jugador
				suspensiones = Suspension.findAll{ jugador.dni == dni };
			}else{
				def likeJugador = "%" + params.jugador + "%"
				suspensiones = Suspension.findAll{ jugador.nombre =~ likeJugador || jugador.apellido =~ likeJugador };
			}
		}else{
			suspensiones = Suspension.findAll();
		}

		render(view:'index', model:['suspensionInstanceList':suspensiones,
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
