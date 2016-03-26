package ar.com.torneobarker

class Categoria {
	
//	def int id
	
	def String descripcion
	
	static mapping = {
		sort descripcion:"asc"
	}
    static constraints = {
    }
	public String toString(){
		return this.descripcion
	}
}
