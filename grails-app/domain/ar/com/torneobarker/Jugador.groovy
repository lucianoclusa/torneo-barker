package ar.com.torneobarker

class Jugador {
	String nombre
	String apellido
	Date fechaNacimiento
	Integer dni
	static mapping = {
		sort dni:"asc"
	}
    static constraints = {
		nombre blank: false, nullable: true
		dni unique: true, nullable: false, blank:false
    }
	
	public String toString(){
		return this.dni + " - " + this.apellido + ", " + this.nombre
	}
}
