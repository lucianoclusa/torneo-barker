package ar.com.torneobarker

class Torneo {

	static hasMany = [fechas: Fecha, equipos: Equipo]
	def Categoria categoria
	def Temporada temporada
	def String anio
	def Date fechaInicio
	def Date fechaFin
	def Fecha ultFechaCargada
		
	static mapping = {
		fechas cascade: 'all'
	}

	
    static constraints = {
    	ultFechaCargada nullable: true
	}
	
	public String toString(){
		return this.anio + " " + (this.temporada==null?"":this.temporada.descripcion) + " " + (this.categoria==null?"":this.categoria.descripcion)
	}
}
