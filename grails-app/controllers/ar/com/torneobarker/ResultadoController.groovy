package ar.com.torneobarker



import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import ar.com.torneobarker.command.EstadisticaJugador
import ar.com.torneobarker.utils.JugadorComparator

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class ResultadoController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Resultado.list(params), model:[resultadoInstanceCount: Resultado.count()]
	}

	def show(Resultado resultadoInstance) {
		Long partidoId = Long.valueOf(params.get("partidoId"))
		Partido partido = Partido.get(partidoId)
		Torneo torneoInstance = partido.getFecha().getTorneo()
		Resultado resultado = partido.getResultado();

		List<Suspension> suspensionesList = Suspension.findAll("from Suspension s where s.partido.id = :torneo and s.estado.id = 1", [torneo : partido.id])
		Set<Suspension> suspensiones = new HashSet<Suspension>(suspensionesList);

		Set<EstadisticaJugador> jugadoresLocal = populateJugadoresEstadistica(partido, partido.getLocal(), resultado, resultado.golesLocal, suspensiones, false)
		Set<EstadisticaJugador> jugadoresVisitante = populateJugadoresEstadistica(partido, partido.getVisitante(), resultado, resultado.golesVisitante, suspensiones, false)



		render view: 'show.gsp', model: [jugadoresLocal: jugadoresLocal, jugadoresVisitante: jugadoresVisitante, torneoInstance: torneoInstance, local : partido.local, visitante : partido.visitante, resultadoInstance:resultado]
	}

	def showGoleadores(Torneo torneo){
		HashMap<Integer, EstadisticaJugador> mapJugadores = new HashMap<String, Jugador>();

		List goles = Gol.findByTorneo(torneo, reload:true);

		for(Gol gol:goles){
			if(mapJugadores.get(gol.jugador.dni)!=null){
				mapJugadores.get(gol.jugador.dni).golesFavor++
			}else{
				//				EstadisticaEquipo jugador = new EstadisticaEquipo(Equipo.find(), partidosGanados, partidosPerdidos, partidosEmpatados, partidosJugados, puntos, golesFavor, golesContra)
			}
		}

	}

	private Set<EstadisticaJugador> populateJugadoresEstadistica(Partido partido, Equipo equipo, Resultado resultado, Set goles, Set suspensiones, boolean jugadoresVacios) {
		List<EstadisticaJugador> jugadores = new ArrayList<EstadisticaJugador>();


		for(Jugador jugador : equipo.jugadores){
			EstadisticaJugador jugadorEstadistica;
			jugadorEstadistica = new EstadisticaJugador();
			jugadorEstadistica.setJugador(jugador);
			jugadorEstadistica.setGolesContra(0);
			jugadorEstadistica.setGolesFavor(0);
			jugadorEstadistica.setTarjetasAmarillas(0);
			jugadorEstadistica.setTarjetasRojas(0);
			jugadorEstadistica.setComentario("");


			for(Gol gol : goles){
				if(jugador.id.equals(gol.jugador.id)){
					if(!gol.enContra){
						jugadorEstadistica.golesFavor = jugadorEstadistica.golesFavor + 1
					}else{
						jugadorEstadistica.golesContra = jugadorEstadistica.golesContra + 1
					}
				}
			}

			if(resultado != null){
				for(TarjetaAmarilla tarjeta : resultado.tarjetasAmarillas){
					if(jugador.id.equals(tarjeta.jugador.id)){
						jugadorEstadistica.tarjetasAmarillas = jugadorEstadistica.tarjetasAmarillas + 1
					}
				}
				for(TarjetaRoja tarjeta : resultado.tarjetasRojas){
					if(jugador.id.equals(tarjeta.jugador.id)){
						jugadorEstadistica.tarjetasRojas = jugadorEstadistica.tarjetasRojas + 1
						jugadorEstadistica.comentario = tarjeta.descripcion
					}
				}
			}
			if(jugadoresVacios){
				jugadores.add(jugadorEstadistica);
			}else{
				if(jugadorEstadistica.golesFavor>0||jugadorEstadistica.golesContra>0||jugadorEstadistica.tarjetasAmarillas>0||jugadorEstadistica.tarjetasRojas>0){
					jugadores.add(jugadorEstadistica);
				}
			}

			for(Suspension suspension: suspensiones){
				if(suspension.jugador.id==jugadorEstadistica.jugador.id){
					jugadorEstadistica.cantSusp = jugadorEstadistica.cantSusp + suspension.cantFechas
				}
			}

		}

		Collections.sort(jugadores, new JugadorComparator())
		return jugadores;
	}

	def create() {
		respond new Resultado(params)
	}

	def cargarResultado() {
		Long partidoId = Long.valueOf(params.get("partidoId"))
		Partido partido = Partido.get(partidoId)

		Set<EstadisticaJugador> jugadoresLocal = populateJugadoresEstadistica(partido, partido.getLocal(), null, null, null, true)
		Set<EstadisticaJugador> jugadoresVisitante = populateJugadoresEstadistica(partido, partido.getVisitante(), null, null, null, true)


		render view: 'create.gsp', model: [partido: partido, jugadoresLocal: jugadoresLocal, jugadoresVisitante: jugadoresVisitante]
	}

	@Transactional
	def save(Resultado resultadoInstance) {
		if (resultadoInstance == null) {
			notFound()
			return
		}

		if(resultadoInstance.id!=null){
			//			Partido partido = resultadoInstance.partido
			//			partido.resultado = null
			//			partido.save flush:true
			//			for(Gol gol: resultadoInstance.golesLocal){
			//				gol.removeFromPartido()
			//				gol.removeFromJugador()
			//				gol.partido=null
			//				gol.torneo=null
			//				gol.delete flush:true
			//			}
			//			for(Gol gol: resultadoInstance.golesVisitante){
			//				gol.removeFromPartido()
			//				gol.removeFromJugador()
			//				gol.partido=null
			//				gol.torneo=null
			//				gol.delete flush:true
			//			}
			//
			Partido partido = resultadoInstance.partido
			partido.resultado = null
			partido.save flush:true

			resultadoInstance.delete flush:true

		}

		resultadoInstance = new Resultado();

		HashSet<String> keySet = params.keySet()

		Long partidoId = Long.valueOf(params.get("partidoId"));
		Partido partido = Partido.get(partidoId);
		Fecha fecha = Fecha.get(partido.fecha.id);
		Torneo torneo = fecha.getTorneo();
		torneo.ultFechaCargada = fecha
		torneo.save flush:true

		restarPartidosRestantes(partido)

		for (String key : keySet) {
			def jugadorId

			if(key.split("_").size()>1){
				jugadorId = key.split("_")[1]
			}

			if(key.startsWith("golFavor")){
				if(params.get(key)!=null && !params.get(key).isEmpty() && new Integer(params.get(key))>0){
					for(int i =0 ; i<new Integer(params.get(key));i++){
						if(partido.local.jugadores.contains(Jugador.get(jugadorId))){
							resultadoInstance.addToGolesLocal( new Gol(jugador: Jugador.get(jugadorId), torneo:fecha.torneo, resultado:resultadoInstance, enContra: false, partido:partido))
						}else{
							resultadoInstance.addToGolesVisitante( new Gol(jugador: Jugador.get(jugadorId), torneo:fecha.torneo, resultado:resultadoInstance, enContra: false, partido:partido))
						}
					}
				}
			}

			if(key.startsWith("golContra")){
				if(params.get(key)!=null && !params.get(key).isEmpty() && new Integer(params.get(key))>0){
					for(int i =0 ; i<new Integer(params.get(key));i++){
						if(partido.local.jugadores.contains(Jugador.get(jugadorId))){
							resultadoInstance.addToGolesLocal(new Gol(jugador: Jugador.get(jugadorId), torneo:fecha.torneo, resultado:resultadoInstance, enContra: true, partido:partido))
						}else{
							resultadoInstance.addToGolesVisitante(new Gol(jugador: Jugador.get(jugadorId), torneo:fecha.torneo, resultado:resultadoInstance, enContra: true, partido:partido))
						}
					}
				}
			}
			if(key.startsWith("tarjetaAmarilla")){
				if(params.get(key)!=null && !params.get(key).isEmpty() && new Integer(params.get(key))>0){
					for(int i =0 ; i<new Integer(params.get(key));i++){
						if(partido.local.jugadores.contains(Jugador.get(jugadorId))){
							resultadoInstance.addToTarjetasAmarillas(new TarjetaAmarilla(jugador: Jugador.get(jugadorId), torneo:fecha.torneo, partido:partido, equipo:partido.local))
						}else{
							resultadoInstance.addToTarjetasAmarillas(new TarjetaAmarilla(jugador: Jugador.get(jugadorId), torneo:fecha.torneo, partido:partido, equipo:partido.visitante))
						}
					}
				}
			}
			if(key.startsWith("tarjetaRoja")){
				if(params.get(key)!=null && !params.get(key).isEmpty() && new Integer(params.get(key))>0){
					for(int i =0 ; i<new Integer(params.get(key));i++){
						if(partido.local.jugadores.contains(Jugador.get(jugadorId))){
							resultadoInstance.addToTarjetasRojas(new TarjetaRoja(jugador: Jugador.get(jugadorId), torneo:fecha.torneo, partido:partido, descripcion:params.get("descripcion_" + jugadorId), equipo:partido.local))
						}else{
							resultadoInstance.addToTarjetasRojas(new TarjetaRoja(jugador: Jugador.get(jugadorId), torneo:fecha.torneo, partido:partido, descripcion:params.get("descripcion_" + jugadorId), equipo:partido.visitante))
						}
					}
				}
			}
			if(key.startsWith("cantSuspension")){
				if(params.get(key)!=null && !params.get(key).isEmpty() && new Integer(params.get(key))>0){
					Jugador jugador = Jugador.get(jugadorId);
					int cantFechasSuspension = new Integer(params.get(key));
					if(partido.local.jugadores.contains(Jugador.get(jugadorId))){
						Suspension suspension = new Suspension(estado: SuspensionEstado.get(1), fechaSancion: new Date(), cantPartidosRestantes:cantFechasSuspension, cantFechas:cantFechasSuspension ,jugador:jugador, torneo:fecha.torneo, fecha: partido.fecha, partido:partido, descripcion:params.get("descripcion_" + jugadorId), esIndefinido:false, equipo: partido.local);
						resultadoInstance.addToSuspensiones(suspension)
					}else{
						Suspension suspension = new Suspension(estado: SuspensionEstado.get(1), fechaSancion: new Date(),cantPartidosRestantes:cantFechasSuspension, cantFechas:cantFechasSuspension ,jugador:jugador, torneo:fecha.torneo, fecha: partido.fecha, partido:partido, descripcion:params.get("descripcion_" + jugadorId), esIndefinido:false, equipo: partido.visitante);
						resultadoInstance.addToSuspensiones(suspension)
					}

				}
			}
		}

		partido.setResultado(resultadoInstance);

		resultadoInstance.setPartido(partido)

		resultadoInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'resultado.label', default: 'Resultado'), torneo.id])
				redirect torneo
			}
			'*' { respond torneo, [status: CREATED] }
		}
	}

	def restarPartidosRestantes(Partido partido){

		List<Suspension> suspensionesLocal = Suspension.findAll("from Suspension s where s.equipo = :equipo and s.estado.id = 1 and s.esIndefinido = false", [equipo : partido.local])
		List<Suspension> suspensionesVisitante = Suspension.findAll("from Suspension s where s.equipo = :equipo and s.estado.id = 1 and s.esIndefinido = false", [equipo : partido.visitante])

		if(suspensionesLocal!=null){
			for(Suspension suspension : suspensionesLocal){
				if(suspension.cantPartidosRestantes!=null){
					if(!suspension.partidosRestados.contains(partido)){
						if(suspension.fecha!=null && !suspension.fecha.equals(partido.getFecha())){
							suspension.cantPartidosRestantes--
							suspension.partidosRestados.add(partido)
						}
						if(suspension.cantPartidosRestantes == 0)
							suspension.estado=SuspensionEstado.get(2)
						suspension.save flush:true
					}
				}
			}
		}
		if(suspensionesLocal!=null){
			for(Suspension suspension : suspensionesVisitante){
				if(suspension.cantPartidosRestantes!=null){
					if(!suspension.partidosRestados.contains(partido)){
						if(suspension.fecha!=null && !suspension.fecha.equals(partido.getFecha())){
							suspension.cantPartidosRestantes--
							suspension.partidosRestados.add(partido)
						}
						if(suspension.cantPartidosRestantes == 0)
							suspension.estado=SuspensionEstado.get(2)
						suspension.save flush:true
					}
				}
			}
		}
	}

	def edit(Resultado resultadoInstance) {
		List<Suspension> suspensionesList = Suspension.findAll("from Suspension s where s.partido.id = :torneo", [torneo : resultadoInstance.getPartido().id])
		Set<Suspension> suspensiones = new HashSet<Suspension>(suspensionesList);

		Set<EstadisticaJugador> jugadoresLocal = populateJugadoresEstadistica(resultadoInstance.partido, resultadoInstance.partido.getLocal(), resultadoInstance, resultadoInstance.golesLocal, suspensiones, true)
		Set<EstadisticaJugador> jugadoresVisitante = populateJugadoresEstadistica(resultadoInstance.partido, resultadoInstance.partido.getVisitante(), resultadoInstance, resultadoInstance.golesVisitante, suspensiones, true)

		render view: 'create.gsp', model: [partido: resultadoInstance.getPartido(), jugadoresLocal: jugadoresLocal, jugadoresVisitante: jugadoresVisitante, resultadoInstance: resultadoInstance]
	}

	@Transactional
	def update(Resultado resultadoInstance) {
		if (resultadoInstance == null) {
			notFound()
			return
		}

		if (resultadoInstance.hasErrors()) {
			respond resultadoInstance.errors, view:'edit'
			return
		}

		resultadoInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'Resultado.label', default: 'Resultado'), resultadoInstance.id])
				redirect resultadoInstance
			}
			'*'{ respond resultadoInstance, [status: OK] }
		}
	}

	@Transactional
	def delete(Resultado resultadoInstance) {

		if (resultadoInstance == null) {
			notFound()
			return
		}

		resultadoInstance.delete flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'Resultado.label', default: 'Resultado'), resultadoInstance.id])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	@Transactional
	def eliminarResultado() {

		Long resultadoId = Long.valueOf(params.get("resultadoId"))

		Resultado resultadoInstance = Resultado.get(resultadoId)

		Partido partido = resultadoInstance.partido
		partido.resultado = null
		partido.save flush:true



		resultadoInstance.delete flush:true

		redirect(controller:"torneo", action:"show", id:resultadoInstance.partido.fecha.torneo.id);
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'resultado.label', default: 'Resultado'), params.id])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}

}
