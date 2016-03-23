package ar.com.torneobarker

class Anio {
	
	def int anio
	def String descripcion
	
	static mapping = {
		sort anio:"asc"
	}
    static constraints = {
		anio min: 1900, max:2999, blank:false, 	nullable: false
    }
}
