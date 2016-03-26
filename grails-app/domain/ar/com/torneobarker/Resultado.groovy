package ar.com.torneobarker

class Resultado {

	static hasMany = [golesLocal: Gol, golesVisitante:Gol, tarjetasAmarillas: TarjetaAmarilla,tarjetasRojas:TarjetaRoja, suspensiones: Suspension]
	def boolean empate
	def Partido partido
	static belongsTo = [partido: Partido]
	
	
	static mapping = {
		golesLocal cascade: 'all'
		golesVisitante cascade: 'all'
		tarjetasAmarillas cascade: 'all'
		tarjetasRojas cascade: 'all'
		suspensiones cascade: 'all'
	}

	
    static constraints = {
		golesLocal nullable: true
		golesVisitante nullable: true
		tarjetasRojas nullable: true
		tarjetasAmarillas nullable: true
		suspensiones nullable: true
		
    }
}
