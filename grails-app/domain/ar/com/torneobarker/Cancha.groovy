package ar.com.torneobarker

class Cancha {
	
	int nroCancha
	String descripcion

    static constraints = {
		nroCancha unique:true, nullable: false, blank:false
		descripcion blank: false, unique: true, nullable: true
    }
	public String toString(){
		return this.descripcion
	}
}
