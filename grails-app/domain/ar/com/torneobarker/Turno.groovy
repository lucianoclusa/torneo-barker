package ar.com.torneobarker

class Turno {
	
	int nroTurno
	String descripcion

    static constraints = {
 		nroTurno unique:true, nullable: false, blank:false
		descripcion blank: false, unique: true, nullable: true
   }
	public String toString(){
		return this.descripcion
	}
}
