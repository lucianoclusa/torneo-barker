package ar.com.torneobarker

class Fecha {
	static hasMany = [partidos: Partido]
    
	int numeroFecha
	
	Equipo equipoLibre
	
	String descripcion
	
	def Torneo torneo
	
	static belongsTo = [torneo: Torneo]
	
	
	static mapping = {
		sort "numeroFecha"
		partidos cascade: 'all'
	}
	
	static constraints = {
		equipoLibre blank: false, nullable: true
    }
	public String toString(){
		return descripcion
	}
}
