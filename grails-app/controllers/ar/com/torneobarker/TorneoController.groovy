package ar.com.torneobarker



import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

import java.awt.image.BufferedImage

import javax.imageio.ImageIO

import ar.com.torneobarker.command.EstadisticaEquipo;
import jxl.Workbook
import jxl.format.Alignment
import jxl.format.Border
import jxl.format.BorderLineStyle
import jxl.format.Colour
import jxl.format.VerticalAlignment
import jxl.write.Label
import jxl.write.WritableCellFormat
import jxl.write.WritableFont
import jxl.write.WritableImage
import jxl.write.WritableSheet
import jxl.write.WritableWorkbook


@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class TorneoController {
	def grailsApplication
	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Torneo.list(params), model:[torneoInstanceCount: Torneo.count()]
	}

	def show(Torneo torneoInstance) {
		Set<Fecha> fechas = torneoInstance.fechas;
		boolean torneoFinalizado = true;
		for(Fecha fecha: fechas){
			for(Partido partido: fecha.partidos){
				if(partido.resultado!=null){
					int golesLocal = 0;
					int golesVisitante = 0;
					for(Gol gol : partido.resultado.golesLocal){
						if(!gol.enContra){
							golesLocal++
						}else{
							golesVisitante++
						}
					}

					for(Gol gol : partido.resultado.golesVisitante){
						if(!gol.enContra){
							golesVisitante++
						}else{
							golesLocal++
						}
					}

					partido.setGolesLocal(golesLocal);
					partido.setGolesVisitante(golesVisitante);
				}

				if(torneoFinalizado == true && partido.getResultado()==null){
					torneoFinalizado = false;
				}

			}

		}

		if(torneoFinalizado){
			List<EstadisticaEquipo> primeros = this.checkDesempate(torneoInstance);
			if(primeros!=null && primeros.size()>1)
				this.crearFechaDesempate(torneoInstance, primeros);
		}

		respond torneoInstance
	}

	private void crearFechaDesempate(Torneo torneo, List<EstadisticaEquipo> primeros){
		Fecha fechaDesempate = new Fecha();
		fechaDesempate.setDescripcion("Fecha desempate");
		fechaDesempate.numeroFecha = torneo.fechas.size();

		List<Partido>partidos= new ArrayList<Partido>()
		Partido partido = new Partido();
		partido.local = primeros.get(0).equipo
		partido.visitante = primeros.get(1).equipo
		partido.fecha = fechaDesempate
		partidos.add(partido)

		fechaDesempate.partidos = partidos
		torneo.addToFechas(fechaDesempate)
		torneo.save(flush:true)
	}

	private List<EstadisticaEquipo> checkDesempate(Torneo torneo){
		List<EstadisticaEquipo> posiciones = EstadisticaEquipoController.getPosicionesList(torneo);
		List<EstadisticaEquipo> primeros = this.getPrimeros(posiciones);
		return primeros;
	}

	private List<EstadisticaEquipo> getPrimeros(List<EstadisticaEquipo> posiciones){
		List<EstadisticaEquipo> primeros = new ArrayList<EstadisticaEquipo>();
		primeros.add(posiciones.get(0));
		for(int i=1; i<posiciones.size();i++){
			if(posiciones.get(i).puntos == primeros.get(0).puntos && posiciones.get(i).diferenciaGol == primeros.get(0).diferenciaGol){
				primeros.add(posiciones.get(i));
			}else{
				break;
			}
		}
		return primeros;

	}

	def create() {
		respond new Torneo(params)
	}

	@Transactional
	def save(Torneo torneoInstance) {
		if (torneoInstance == null) {
			notFound()
			return
		}

		if (torneoInstance.hasErrors()) {
			respond torneoInstance.errors, view:'create'
			return
		}

		String[] equipos = params.get("equipos")

		for(String equipoId: equipos){
			Equipo equipo = Equipo.get(equipoId)
			torneoInstance.addToEquipos(equipo)
		}

		String[] equiposCompletos;
		boolean impar=(equipos.size()%2!=0);
		if(impar){
			equiposCompletos = new String[equipos.size() + 1]
			for(int i=0; i <equiposCompletos.size(); i++){
				if(i==equiposCompletos.size()-1){
					equiposCompletos[i] = null
				}else{
					equiposCompletos[i] = equipos[i]
				}
			}
		}else{
			equiposCompletos = new String[equipos.size()]
			for(int i=0; i <equiposCompletos.size(); i++){
				equiposCompletos[i] = equipos[i]
			}
		}

		genFechas(equiposCompletos, torneoInstance)

		torneoInstance.save flush:true

		if(Anio.findByDescripcion(torneoInstance.anio)==null){
			new Anio(descripcion:torneoInstance.anio, anio:torneoInstance.anio).save()
		}

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'torneo.label', default: 'Torneo'), torneoInstance.id])
				redirect torneoInstance
			}
			'*' { respond torneoInstance, [status: CREATED] }
		}
	}

	private void genFechas(String[] equipos, Torneo torneoInstance){
		int auxT=equipos.size();

		int cantFechas;
		int cantPartidosPorFecha;
		boolean impar=(auxT%2!=0);

		if(impar){
			cantFechas = equipos.size()
			cantPartidosPorFecha = (equipos.size()+1)/2
		}else{
			cantFechas = equipos.size()-1
			cantPartidosPorFecha = equipos.size()/2
		}

		Fecha[] fechas = new Fecha[cantFechas];
		Partido[] partidos = new Partido[cantPartidosPorFecha];

		if(impar){
			++auxT;
		}
		int totalP=(auxT*(auxT-1))/2;//total de partidos de una ronda
		String [] local=new String [totalP];
		String [] visita=new String [totalP];
		int modIF=(auxT/2);//para hacer mod cada inicio de fecha
		int indiceInverso=auxT-2;
		for(int i=0;i<totalP;i++){
			if (i%modIF==0){//seria el partido inicial de cada fecha
				//si es impar el numero de clubes la primera fecha se borra poniendo null
				if (impar){
					local[i]=null;
					visita[i]=null;
				}
				else{
					//se pone uno local otro  visita al ultimo equipo
					if(i%2==0){
						local[i]=equipos[i%(auxT-1)];
						visita[i]=equipos[auxT-1];
					}
					else{
						local[i]=equipos[auxT-1];
						visita[i]=equipos[i%(auxT-1)];
					}
				}
			}
			else{
				local[i]=equipos[i%(auxT-1)];
				visita[i]=equipos[indiceInverso];
				--indiceInverso;
				if (indiceInverso<0){
					indiceInverso=auxT-2;
				}
			}
		}


		int k = 0;

		for(int i=0; i<cantFechas; i++){
			fechas[i] = new ar.com.torneobarker.Fecha();
			fechas[i].numeroFecha = i+1;
			fechas[i].descripcion = "Fecha " + (i+1);
			fechas[i].torneo = torneoInstance
			int nroPartido = 1;
			for(int j=0; j<cantPartidosPorFecha; j++){
				String equipoUnoId = local[k];
				String equipoDosId = visita[k];
				if(equipoUnoId==null || equipoDosId==null){
					fechas[i].equipoLibre = Equipo.get(equipoUnoId!=null?equipoUnoId:equipoDosId);
				}else{
					Partido partido = new Partido();
					Equipo equipoLocal = Equipo.get(equipoUnoId);
					partido.setLocal(equipoLocal);
					Equipo equipoVisitante = Equipo.get(equipoDosId);
					partido.setVisitante(equipoVisitante);
					partido.fecha = fechas[i]
					partido.nro = nroPartido;
					nroPartido++;
					partido.turno = getTurnoForTorneo(torneoInstance)
					partido.cancha = getCanchaForPartido(partido);
					fechas[i].addToPartidos(partido);
				}
				k++
			}
			torneoInstance.addToFechas(fechas[i])
		}


	}

	private Turno getTurnoForTorneo(Torneo torneo){
		switch (torneo.getCategoria().id){
			case 1:
				return Turno.findByNroTurno(1);
			case 2:
				return Turno.findByNroTurno(2);
			case 3:
				return Turno.findByNroTurno(3);
			default:
				return Turno.findByNroTurno(1);

		}
	}

	private Cancha getCanchaForPartido(Partido partido){
		switch (partido.nro){
			case 1:
				return Cancha.findByNroCancha(1);
			case 2:
				return Cancha.findByNroCancha(2);
			case 3:
				return Cancha.findByNroCancha(3);
			case 4:
				return Cancha.findByNroCancha(4);
			case 5:
				return Cancha.findByNroCancha(5);
			case 6:
				return Cancha.findByNroCancha(6);
			default:
				return Cancha.findByNroCancha(1);
		}
	}

	def edit(Torneo torneoInstance) {
		Set<Fecha> fechas = torneoInstance.getFechas();

		respond torneoInstance , [fechas: fechas]
	}

	@Transactional
	def update(Torneo torneoInstance) {


		if (torneoInstance == null) {
			notFound()
			return
		}

		if (torneoInstance.hasErrors()) {
			respond torneoInstance.errors, view:'edit'
			return
		}

		torneoInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'Torneo.label', default: 'Torneo'), torneoInstance.id])
				redirect torneoInstance
			}
			'*'{ respond torneoInstance, [status: OK] }
		}
	}

	@Transactional
	def delete(Torneo torneoInstance) {

		if (torneoInstance == null) {
			notFound()
			return
		}

		torneoInstance.delete flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'Torneo.label', default: 'Torneo'), torneoInstance.id])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'torneo.label', default: 'Torneo'), params.id])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
	
	@Transactional
	def editFecha(){
		boolean error = false;
		Fecha fechaActual = Fecha.get(params.get("fechaActualId"));
		int fechaSelected = Integer.parseInt(params.get("fechaSelected"));
		if(fechaActual.numeroFecha!=fechaSelected){
			error = !switchFechas(fechaActual, fechaSelected);
		}
		if(!error){
			for(Partido partido : fechaActual.partidos){
				boolean partidoModificado = false;
				Long turnoSelectedId = Long.parseLong(params.get("turnoSelected" + partido.getId()));
				
				if(!partido.getTurno().getId().equals(turnoSelectedId)){
					partido.setTurno(Turno.get(turnoSelectedId));
					partidoModificado = true;
				}
				
				Long canchaSelectedId = Long.parseLong(params.get("canchaSelected" + partido.getId()));
				if(!partido.getCancha().getId().equals(canchaSelectedId)){
					partido.setCancha(Cancha.get(canchaSelectedId));
					partidoModificado = true;
				}
				
				if(partidoModificado){
					partido.save(flush:true);
					error = partido.dirty;  
				}
			}

			
			if(!error)			
				request.message = "Fecha modificada."
			else
				request.error = "Error modificando la fecha."
		}else
			request.error = "Error modificando la fecha."
		
		redirect(action:"show", id:fechaActual.getTorneo().getId())
	}

	private boolean switchFechas(Fecha fechaActual, int nroFechaActualizada){
		boolean ok= false;
		
		Fecha fechaASwitchear = Fecha.findByTorneoAndNumeroFecha(fechaActual.getTorneo(), nroFechaActualizada);
		fechaASwitchear.setNumeroFecha(fechaActual.getNumeroFecha());
		fechaASwitchear.setDescripcion("Fecha "+fechaASwitchear.numeroFecha)
		fechaActual.setNumeroFecha(nroFechaActualizada);
		fechaActual.setDescripcion("Fecha "+fechaActual.numeroFecha)
		
		fechaASwitchear.save(flush:true)
		fechaActual.save(flush:true)
		
		ok = !fechaActual.dirty && !fechaASwitchear.dirty
		
		return ok
	}
	
}
