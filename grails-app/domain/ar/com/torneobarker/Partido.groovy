package ar.com.torneobarker

class Partido {

	def static final int CANT_PUNTOS_VICTORIA = 3;
	def static final int CANT_PUNTOS_EMPATE = 1;
	def static final int CANT_PUNTOS_DERROTA = 0;
	def Equipo local
	def Equipo visitante
	def Integer golesVisitante
	def Integer golesLocal
	def Resultado resultado
	def Fecha fecha
	def nro
	def Turno turno
	def Cancha cancha
//	def boolean tieneRestriccionEquipoLocal
//	def String descripcionRestriccionEquipoLocal
//	def boolean tieneRestriccionEquipoVisitante
//	def String descripcionRestriccionEquipoVisitante

	static belongsTo = [fecha: Fecha]

    static constraints = {
		resultado unique: true, nullable: true
		cancha nullable:true
		turno nullable:true
//		descripcionRestriccionEquipoLocal nullable:true
//		descripcionRestriccionEquipoVisitante nullable:true
    }
	static mapping = {
		sort id:"asc"
	}

	static transients = ['golesVisitante', 'golesLocal']
	
	public String toString(){
		return this.local.nombre + " VS " + this.visitante.nombre + " - " + this.fecha.torneo.toString()
	}
	
}
