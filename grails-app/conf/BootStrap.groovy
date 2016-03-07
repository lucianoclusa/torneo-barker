import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import ar.com.torneobarker.*
import ar.com.torneobarker.users.Role
import ar.com.torneobarker.users.User
import ar.com.torneobarker.users.UserRole


class BootStrap {

	def final MAX_JUGADORES = 25;

	def init = { servletContext ->

		DatosServidor server = DatosServidor.get(0)
		if(server==null){
			server = new DatosServidor(cantBooteos:1)
		}else{
			server.cantBooteos++
		}
		server.save()


		//		assert User.count() == 1
		//		assert Role.count() == 2
		//		assert UserRole.count() == 1

		if(server.cantBooteos<=1){
			loadTablasFijas()
			//			loadEquiposFromExcel();
		}


	}

	def destroy = {

	}

	def loadTablasFijas = {

		if(Temporada.get(1)==null)new Temporada(id:1, descripcion:"Clausura").save()
		if(Temporada.get(2)==null)new Temporada(id:2, descripcion:"Apertura").save()

		if(Categoria.get(1)==null)new Categoria(id:1, descripcion:"Mas 35").save()
		if(Categoria.get(2)==null)new Categoria(id:2, descripcion:"Mas 40").save()
		if(Categoria.get(3)==null)new Categoria(id:3, descripcion:"Mas 45").save()
		def adminRole
		if(Role.get(1)==null)adminRole = new Role(id:1, authority: 'ROLE_ADMIN').save(flush: true)
		def userRole
		if(Role.get(2)==null)userRole = new Role(id:2, authority: 'ROLE_USER').save(flush: true)
		def admin
		if(User.get(1)==null)admin = new User(id:1, username: 'sRojas', password: 'santiagoRojas').save(flush: true)

		UserRole.create admin, adminRole, true


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
