package ar.com.torneobarker

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import ar.com.torneobarker.command.EstadisticaJugador
import ar.com.torneobarker.utils.GoleadoresComparator

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class EstadisticaJugadorController {

    def index() { }
	
	def showGoleadores(Torneo torneo) {

		List<EstadisticaJugador> goleadoresList = getGoleadoresList(torneo)
		

		render view: '/home/goleadores.gsp', model: [goleadoresList: goleadoresList, torneoInstance: torneo]

	}

	public static List<EstadisticaJugador> getGoleadoresList(Torneo torneo) {
		List<Gol> goles = Gol.findAll("from Gol g where g.torneo.id = :torneoId and g.enContra = false", [torneoId : torneo.id])
		List<EstadisticaJugador> goleadoresList;
		def HashMap<Jugador,EstadisticaJugador> goleadores =  new HashMap();


		for(Gol gol: goles){
			//			Jugador jugador = Jugador.get(gol.jugador)

			if(goleadores.get(gol.jugador.getId())==null){
				Equipo equipo;
				for(Jugador jugador: gol.getPartido().getLocal().jugadores){
					if(jugador.getId().equals(gol.jugador.getId())){
						equipo = gol.partido.local
						break
					}
				}
				if(equipo==null)
					equipo = gol.partido.visitante


				EstadisticaJugador goleador = new EstadisticaJugador(jugador: gol.jugador, golesFavor: 1, equipo:equipo)
				goleadores.put(gol.jugador.getId(), goleador)
			}else{
				EstadisticaJugador goleador = goleadores.get(gol.jugador.getId())
				goleador.golesFavor = goleador.golesFavor+1
				goleadores.put(gol.jugador.getId(), goleador)
			}
		}
		goleadoresList = new ArrayList(goleadores.values());

		Collections.sort(goleadoresList, new GoleadoresComparator());

		for(int i= 0; i<goleadoresList.size();i++){
			((EstadisticaJugador)goleadoresList.get(i)).setPosicion(i+1);
		}
		return goleadoresList
	}
	
}
