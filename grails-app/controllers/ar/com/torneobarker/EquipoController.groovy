package ar.com.torneobarker



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.web.multipart.MultipartRequest
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class EquipoController {
	def final MAX_JUGADORES = 37;

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Equipo.list(params), model:[equipoInstanceCount: Equipo.count()]
	}

	def show(Equipo equipoInstance) {
		respond equipoInstance
	}

	def create() {
		respond new Equipo(params)
	}

	@Transactional
	def save(Equipo equipoInstance) {
		if (equipoInstance == null) {
			notFound()
			return
		}

		if (equipoInstance.hasErrors()) {
			respond equipoInstance.errors, view:'create'
			return
		}

		equipoInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'equipo.label', default: 'Equipo'), equipoInstance.id])
				redirect equipoInstance
			}
			'*' { respond equipoInstance, [status: CREATED] }
		}
	}

	def categoriaChanged(long categoriaId) {
		Categoria categoria = Categoria.get(categoriaId)
		def equipos = []
		if ( categoria != null ) {
			equipos = Equipo.findAllByCategoria(categoria)
		}
		render g.select(id:'fechas', name:'equipo.id', multiple:'true',
		from:equipos, optionKey:'id', style:"height:300px; width:300px", required:"", value:"id", class:"many-to-many"
		)
	}

	def edit(Equipo equipoInstance) {
		respond equipoInstance
	}

	@Transactional
	def update(Equipo equipoInstance) {
		if (equipoInstance == null) {
			notFound()
			return
		}

		if (equipoInstance.hasErrors()) {
			respond equipoInstance.errors, view:'edit'
			return
		}

		equipoInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'Equipo.label', default: 'Equipo'), equipoInstance.id])
				redirect equipoInstance
			}
			'*'{ respond equipoInstance, [status: OK] }
		}
	}

	@Transactional
	def delete(Equipo equipoInstance) {

		if (equipoInstance == null) {
			notFound()
			return
		}

		equipoInstance.delete flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'Equipo.label', default: 'Equipo'), equipoInstance.id])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'equipo.label', default: 'Equipo'), params.id])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
	def showLoadFromExcel(){
		render(view:"loadFromExcel");
	}

	@Secured(['ROLE_USER','ROLE_ADMIN', 'IS_AUTHENTICATED_FULLY'])
	@Transactional
	def loadFromExcel(){
		MultipartRequest multipartRequest =  request as MultipartRequest
		if(multipartRequest){

			def f = multipartRequest.getFile('cfile')
			if (f.empty) {
				flash.message = 'file cannot be empty'
				render(view: 'uploadForm')
				return
			}
			File tempFile = File.createTempFile(f.name, ".tmp")
			f.transferTo(tempFile)
			this.loadEquiposFromExcelFunc(tempFile)
			redirect(controller:"equipo", action:"index");
		}

	}

	def loadEquiposFromExcelFunc(File inputFile){
		FileInputStream file = new FileInputStream(inputFile);

		//Create Workbook instance holding reference to .xlsx file
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet

		for(int i=0; i< workbook.getNumberOfSheets(); i++){
			sheet = workbook.getSheetAt(i);

			Colegio colegio = new Colegio( nombre: workbook.getSheetName(i)).save();


			Row rowEquipo = sheet.getRow(7);
			Cell nombreEquipoCell = rowEquipo.getCell(1);

			Row rowCategoria = sheet.getRow(2);
			Cell categoriaCell = rowCategoria.getCell(8);

			Categoria categoria = this.getCategoriaByDescripcion(categoriaCell.stringCellValue);


			String cellString = nombreEquipoCell.stringCellValue;
			String  nombreEquipo = cellString.substring(9, cellString.length());
			String descripcionEquipo = nombreEquipo + " " + categoria.descripcion;

			Equipo equipo = new Equipo(nombre: nombreEquipo, colegio: colegio, fechaCreacion: new Date(), categoria: categoria, descripcion : descripcionEquipo )

			for(int j=12; j<MAX_JUGADORES; j++){
				Row rowJugador = sheet.getRow(j);

				String  dni
				Integer numDNI
				Jugador jugador = null;
				if(rowJugador!=null && rowJugador.getCell(2)!=null){
					if(rowJugador.getCell(2).getCellType() == Cell.CELL_TYPE_STRING){
						dni = rowJugador.getCell(2).getStringCellValue();
						numDNI = formatDNI(dni);
					}else if(rowJugador.getCell(2).getCellType() == Cell.CELL_TYPE_NUMERIC)
						numDNI = rowJugador.getCell(2).getNumericCellValue();
					if(numDNI!=null){
						jugador = Jugador.findByDni(numDNI)
						if(jugador == null){
							String  nombreApellido = rowJugador.getCell(1).getStringCellValue();
							if(nombreApellido!=null && nombreApellido.compareTo("")){
								Map nombreApellidoMap = this.formatNombre(nombreApellido);
								jugador = new Jugador(nombre: nombreApellidoMap.get("nombre"), apellido: nombreApellidoMap.get("apellido"), fechaNacimiento: new Date(), dni: numDNI)
								jugador.save flush:true
							}
						}
						if(jugador!=null)
							equipo.addToJugadores(jugador)
					}
				}
			}
			equipo.save flush:true
		}
	}

	def formatNombre(String nombreApellido){
		def Map<String, String> mapaNombreApellido = new HashMap();
		if(nombreApellido.indexOf(" ")==0){
			nombreApellido = nombreApellido.replaceFirst(' ','');
		}

		if(!nombreApellido.contains(','))
			nombreApellido = nombreApellido.replaceFirst(' ',',');
		String[] array = nombreApellido.split(',')
		mapaNombreApellido.put("nombre", array[1].replace(',', ''));
		mapaNombreApellido.put("apellido", array[0].replace(',', ''));
		return mapaNombreApellido
	}

	def formatDNI(String dni){
		dni = dni.replace(" ", "");
		dni = dni.replace(",", "");
		dni = dni.replace(".","");
		Integer numDNI
		try{
			numDNI = Integer.parseInt(dni)
		}catch(NumberFormatException e){
			return null;
		}
		return numDNI;
	}

	def getCategoriaByDescripcion(String categoriaNombre){
		if(categoriaNombre.equals("CAT 35")){
			return Categoria.get(1);
		}
		if(categoriaNombre.equals("CAT 40")){
			return Categoria.get(2);
		}
		if(categoriaNombre.equals("CAT 45")){
			return Categoria.get(3);
		}else{
			return Categoria.findByDescripcion(categoriaNombre)
		}
	}

}
