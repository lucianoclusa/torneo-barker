package ar.com.torneobarker

class Suspension {
	
	def static final int CANT_PUNTOS_AMARILLA= 1;
	def static final int CANT_PUNTOS_ROJA = 3;
	def static final int CANT_PUNTOS_MAS_TRES_FECHAS = 6;

	static hasMany = [partidosRestados: Partido]
	
	static searchable = true  
	def Date fechaSancion
	def Jugador jugador
	def Torneo torneo
	def Equipo equipo
	def Partido partido
	def Fecha fecha
	def Integer cantFechas
	def Integer cantPartidosRestantes
	def boolean esIndefinido
	def String descripcion
	def boolean estaActiva = true
	def boolean esProvisoria
	
	static mapping = {
		sort fechaSancion:"desc"
	}

	static constraints = {
		partido nullable: true
		equipo nullable: true
		cantFechas nullable:true
		cantPartidosRestantes nullable:true
		descripcion nullable:true
		torneo nullable:true
		fecha nullable:true
    }
}
