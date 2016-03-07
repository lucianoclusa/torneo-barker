package ar.com.torneobarker

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import ar.com.torneobarker.command.EstadisticaFairPlay
import ar.com.torneobarker.utils.FairPlayComparator

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class EstadisticaFairPlayController {

	def index() { }

	def showFairPlay(Torneo torneo) {

		List<EstadisticaFairPlay> fairPlayList = getFairPlayList(torneo)

		render view: '/home/fairPlay.gsp', model: [fairPlayList: fairPlayList, torneoInstance: torneo]

	}

	public static List<EstadisticaFairPlay> getFairPlayList(Torneo torneo) {
		List<Suspension> suspensiones = Suspension.findAll("from Suspension s where s.torneo.id = :torneo or s.esIndefinido = 'true'", [torneo : torneo.id])

		List<EstadisticaFairPlay> fairPlayList;
		def HashMap<String,EstadisticaFairPlay> tablaFairPlay =  new HashMap();


		for(Equipo equipo:torneo.equipos){
			EstadisticaFairPlay estadistica = new EstadisticaFairPlay(equipo:equipo, puntos:0, cantSansionesGraves: 0, tarjetasAmarillas:0, tarjetasRojas:0)
			tablaFairPlay.put(equipo.id, estadistica)
		}

		List<Suspension> suspensionesGraves = new ArrayList<Suspension>();
		for(Suspension suspension: suspensiones){
			EstadisticaFairPlay estadistica = tablaFairPlay.get(suspension.getEquipo().id)
			if(suspension.cantFechas>3 || suspension.isEsIndefinido()){
				estadistica.puntos = estadistica.puntos + Suspension.CANT_PUNTOS_MAS_TRES_FECHAS
				estadistica.cantSansionesGraves =  estadistica.cantSansionesGraves + 1
				suspensionesGraves.add(suspension);

			}
		}
		fairPlayList = new ArrayList(tablaFairPlay.values());

		for(int i= 0; i<fairPlayList.size();i++){
			List<TarjetaAmarilla> amarillas = TarjetaAmarilla.findAllByEquipoAndTorneo(((EstadisticaFairPlay)fairPlayList.get(i)).getEquipo(), torneo)
			List<TarjetaRoja> rojas = TarjetaRoja.findAllByEquipoAndTorneo(((EstadisticaFairPlay)fairPlayList.get(i)).getEquipo(), torneo)

			//Si tiene suspension grave, no cuenta la amarilla
			int cantAmarillas = 0;
			for(int j=0; j<amarillas.size(); j++){
				TarjetaAmarilla amarilla = amarillas.get(j)
				boolean valeAmarilla = true;
				for(Suspension suspension: suspensionesGraves){
					if(amarilla.getJugador().id == suspension.getJugador().id && amarilla.getPartido().id == suspension.getPartido().id){
						valeAmarilla = false;
						break;
					}
				}

				if(valeAmarilla)
					cantAmarillas++;
			}

			//Si tiene suspension grave, no cuenta la roja
			int cantRojas = 0;
			for(int j=0; j<rojas.size(); j++){
				TarjetaRoja roja = rojas.get(j)
				boolean valeRoja = true;
				for(Suspension suspension: suspensionesGraves){
					if(roja.getJugador().id == suspension.getJugador().id && roja.getPartido().id == suspension.getPartido().id){
						valeRoja = false;
						break;
					}
				}

				if(valeRoja)
					cantRojas++;
			}

			((EstadisticaFairPlay)fairPlayList.get(i)).tarjetasAmarillas = amarillas.size()
			((EstadisticaFairPlay)fairPlayList.get(i)).puntos = ((EstadisticaFairPlay)fairPlayList.get(i)).puntos + (cantAmarillas * Suspension.CANT_PUNTOS_AMARILLA)
			((EstadisticaFairPlay)fairPlayList.get(i)).tarjetasRojas = rojas.size()
			((EstadisticaFairPlay)fairPlayList.get(i)).puntos = ((EstadisticaFairPlay)fairPlayList.get(i)).puntos + (cantRojas * Suspension.CANT_PUNTOS_ROJA)
		}

		Collections.sort(fairPlayList, new FairPlayComparator());


		for(int i= 0; i<fairPlayList.size();i++){
			((EstadisticaFairPlay)fairPlayList.get(i)).setPosicion(i+1);
		}
		return fairPlayList
	}

}
