package ar.com.torneobarker

class Equipo {

	String nombre
	Date fechaCreacion
	Colegio colegio
	Categoria categoria
	static hasMany = [jugadores: Jugador]
	String descripcion
    
	static mapping = {
		sort nombre:"asc"
	}
	
	static constraints = {
		nombre blank: false, unique: true, nullable: true
		colegio blank: false, nullable: true
		
    }
	
	public String toString(){
		return this.nombre + " - " + this.categoria
	}
}
