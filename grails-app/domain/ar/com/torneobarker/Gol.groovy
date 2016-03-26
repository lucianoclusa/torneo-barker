package ar.com.torneobarker

class Gol {
	def Jugador jugador
	def boolean enContra = false;
	def Torneo torneo
	def Partido partido
	static belongsTo = [partido: Partido, jugador: Jugador]

    static constraints = {
    }
}
