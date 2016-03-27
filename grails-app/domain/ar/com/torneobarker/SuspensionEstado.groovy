package ar.com.torneobarker

class SuspensionEstado {

	int id
	String descripcion
	
    static constraints = {
    }
	
	public String toString(){
		return this.descripcion;
	}
}
