package ar.com.torneobarker

class Colegio {
	String nombre
	static mapping = {
		sort nombre:"asc"
	}
    static constraints = {
		nombre blank: false, unique: true, nullable: true
    }
	public String toString(){
		return this.nombre	}
}
