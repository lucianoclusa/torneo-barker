package ar.com.torneobarker

class TarjetaRoja {
	def Jugador jugador
	def Equipo equipo
	def Torneo torneo
	def Partido partido
	def String descripcion
	static belongsTo = [partido: Partido]

    static constraints = {
		descripcion nullable: true
		
    }
}
