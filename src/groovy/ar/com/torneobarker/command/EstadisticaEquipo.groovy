package ar.com.torneobarker.command

import ar.com.torneobarker.Equipo
import ar.com.torneobarker.Partido

class EstadisticaEquipo {

	public EstadisticaEquipo(Equipo equipo, int partidosGanados, int partidosPerdidos, int partidosEmpatados, int partidosJugados, int puntos,  int golesFavor, int golesContra) {
		super();
		this.equipo = equipo;
		this.puntos = puntos;
		this.partidosJugados = partidosJugados;
		this.partidosGanados = partidosGanados;
		this.partidosPerdidos = partidosPerdidos;
		this.partidosEmpatados = partidosEmpatados;
		this.golesFavor = golesFavor;
		this.golesContra = golesContra;
	}
	def Equipo equipo
	def int posicion
	def int puntos
	def int puntosFairPlay
	def int partidosJugados	
	def int partidosGanados	
	def int partidosPerdidos
	def int partidosEmpatados
	def int golesFavor
	def int golesContra
	def int diferenciaGol
}
