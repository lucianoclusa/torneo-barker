package ar.com.torneobarker

class TarjetaAmarilla {
	def Jugador jugador
	def Equipo equipo
	def Torneo torneo
	def Partido partido
	static belongsTo = [partido: Partido]

    static constraints = {
    }
}
