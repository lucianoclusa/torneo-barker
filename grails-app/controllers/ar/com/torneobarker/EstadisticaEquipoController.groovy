package ar.com.torneobarker

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import grails.util.Environment
import jxl.Workbook
import jxl.format.Alignment
import jxl.format.Border
import jxl.format.BorderLineStyle
import jxl.format.Colour
import jxl.format.VerticalAlignment
import jxl.write.Label
import jxl.write.Number
import jxl.write.WritableCellFormat
import jxl.write.WritableFont
import jxl.write.WritableImage
import jxl.write.WritableSheet
import jxl.write.WritableWorkbook
import ar.com.torneobarker.command.EstadisticaEquipo
import ar.com.torneobarker.command.EstadisticaFairPlay
import ar.com.torneobarker.command.EstadisticaJugador
import ar.com.torneobarker.utils.FairPlayComparator
import ar.com.torneobarker.utils.GoleadoresComparator
import ar.com.torneobarker.utils.PosicionesComparator

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class EstadisticaEquipoController {

	def showPosiciones(Torneo torneo) {

		List<EstadisticaEquipo> posicionesList= getPosicionesList(torneo)

		render view: '/home/main.gsp', model: [posicionesList: posicionesList, torneoInstance: torneo]

	}

	public static List<EstadisticaEquipo> getPosicionesList(Torneo torneo) {
		List<EstadisticaEquipo> posicionesList = new ArrayList();
		if(torneo!=null){
			def HashMap<Long,EstadisticaEquipo> posiciones =  new HashMap();


			for(Fecha fecha: torneo.fechas){
				for(Partido partido: fecha.partidos){
					if(partido.resultado!=null){

						EstadisticaEquipo local = posiciones.get(partido.getLocal().id)
						if(local==null){
							local = new EstadisticaEquipo(partido.getLocal(), 0, 0, 0, 0, 0, 0, 0)
						}

						EstadisticaEquipo visitante = posiciones.get(partido.getVisitante().id)
						if(visitante==null){
							visitante = new EstadisticaEquipo(partido.getVisitante(), 0, 0, 0, 0, 0, 0, 0)
						}

						local.partidosJugados = local.partidosJugados + 1
						visitante.partidosJugados = visitante.partidosJugados + 1

						int golesLocalFavor = 0
						int golesVisitanteFavor = 0
						int golesLocalContra = 0
						int golesVisitanteContra = 0

						for(Gol gol : partido.resultado.golesLocal){
							if(!gol.enContra){
								golesLocalFavor++
								golesVisitanteContra++

							}else{
								golesVisitanteFavor++
								golesLocalContra++
							}
						}

						for(Gol gol : partido.resultado.golesVisitante){
							if(!gol.enContra){
								golesVisitanteFavor++
								golesLocalContra++
							}else{
								golesLocalFavor++
								golesVisitanteContra++
							}
						}

						local.golesFavor = local.golesFavor + golesLocalFavor
						visitante.golesFavor = visitante.golesFavor + golesVisitanteFavor

						local.golesContra = local.golesContra + golesLocalContra
						visitante.golesContra = visitante.golesContra + golesVisitanteContra

						if(golesLocalFavor>golesVisitanteFavor){
							local.partidosGanados = local.partidosGanados + 1
							local.puntos = local.puntos + Partido.CANT_PUNTOS_VICTORIA

							visitante.partidosPerdidos = visitante.partidosPerdidos + 1
							visitante.puntos = visitante.puntos + Partido.CANT_PUNTOS_DERROTA

						}
						if(golesLocalFavor<golesVisitanteFavor){
							visitante.partidosGanados = visitante.partidosGanados + 1
							visitante.puntos = visitante.puntos + Partido.CANT_PUNTOS_VICTORIA

							local.partidosPerdidos = local.partidosPerdidos + 1
							local.puntos = local.puntos + Partido.CANT_PUNTOS_DERROTA
						}
						if(golesLocalFavor==golesVisitanteFavor){
							local.partidosEmpatados = local.partidosEmpatados + 1
							local.puntos = local.puntos + Partido.CANT_PUNTOS_EMPATE
							visitante.partidosEmpatados = visitante.partidosEmpatados + 1
							visitante.puntos = visitante.puntos + Partido.CANT_PUNTOS_EMPATE
						}

						local.diferenciaGol = local.golesFavor - local.golesContra
						visitante.diferenciaGol = visitante.golesFavor - visitante.golesContra



						posiciones.put(local.equipo.id, local)
						posiciones.put(visitante.equipo.id, visitante)

					}else{
						if(posiciones.get(partido.getLocal().id)==null){
							EstadisticaEquipo local = new EstadisticaEquipo(partido.getLocal(), 0, 0, 0, 0, 0, 0, 0)
							posiciones.put(local.equipo.id, local)
						}
						if(posiciones.get(partido.getVisitante().id)==null){
							EstadisticaEquipo visitante = new EstadisticaEquipo(partido.getVisitante(), 0, 0, 0, 0, 0, 0, 0)
							posiciones.put(visitante.equipo.id, visitante)
						}

					}


				}
			}

			posicionesList = new ArrayList(posiciones.values());

			for(int i= 0; i<posicionesList.size();i++){
				List<TarjetaAmarilla> amarillas = TarjetaAmarilla.findAllByEquipoAndTorneo(((EstadisticaEquipo)posicionesList.get(i)).getEquipo(), torneo)
				List<TarjetaRoja> rojas = TarjetaRoja.findAllByEquipoAndTorneo(((EstadisticaEquipo)posicionesList.get(i)).getEquipo(), torneo)

				((EstadisticaEquipo)posicionesList.get(i)).puntosFairPlay = ((EstadisticaEquipo)posicionesList.get(i)).puntosFairPlay + (amarillas.size() * Suspension.CANT_PUNTOS_AMARILLA)
				((EstadisticaEquipo)posicionesList.get(i)).puntosFairPlay = ((EstadisticaEquipo)posicionesList.get(i)).puntosFairPlay + (rojas.size() * Suspension.CANT_PUNTOS_ROJA)
			}

			Collections.sort(posicionesList, new PosicionesComparator());

			for(int i= 0; i<posicionesList.size();i++){
				((EstadisticaEquipo)posicionesList.get(i)).setPosicion(i+1);
			}
		}
		return posicionesList
	}
	
	def descargarPlanillaFecha(){
		Fecha fecha = Fecha.get(params.get("fechaId"))

		List<Suspension> suspensionesTorneo = Suspension.findAll("from Suspension s where s.torneo.anio = :anio and s.torneo.temporada.id = :temporada and s.estado.id = 1", [anio : fecha.torneo.anio, temporada : fecha.torneo.temporada.id])

		List<Suspension> suspensionesPermanentes = Suspension.findAll("from Suspension s where s.esIndefinido = true and s.estado.id = 1");
		
		List<Suspension> suspensionesProvisorias = Suspension.findAll("from Suspension s where s.esProvisoria = true and s.torneo.anio = :anio and s.torneo.temporada.id = :temporada and s.estado.id = 1", [anio : fecha.torneo.anio, temporada : fecha.torneo.temporada.id]);

				int medioHeight = 15*20;
		int dobleHeight = 20*20;
		int masDobleHeight = 30*20;


		WritableCellFormat cabecera = new WritableCellFormat();
		cabecera.setBorder(Border.ALL, BorderLineStyle.THICK);

		WritableCellFormat valor = new WritableCellFormat();
		valor.setBorder(Border.ALL, BorderLineStyle.THIN);

		WritableCellFormat amonestadoFormat = new WritableCellFormat();
		amonestadoFormat.setBackground(Colour.GRAY_25);
		amonestadoFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

		WritableCellFormat alignDerecha = new WritableCellFormat();
		alignDerecha.setAlignment(Alignment.RIGHT);

		WritableCellFormat subrayado = new WritableCellFormat();
		subrayado.setBorder(Border.BOTTOM, BorderLineStyle.THIN);

		WritableCellFormat vAlignCenter = new WritableCellFormat();
		vAlignCenter.setVerticalAlignment(VerticalAlignment.CENTRE)

		WritableFont cellFont = new WritableFont(WritableFont.COURIER, 16);
		cellFont.setBoldStyle(WritableFont.BOLD);
		WritableCellFormat negrita = new WritableCellFormat(cellFont);

		WritableCellFormat formatoJugador = null;

		response.setContentType('application/vnd.ms-excel')
		response.setHeader('Content-Disposition', 'Attachment;Filename='  + fecha.torneo.toString() + " - " + fecha.toString() + '.xls')
		WritableWorkbook workbook = Workbook.createWorkbook(response.outputStream)
		try{
			int j=0;
			for(Partido partido:fecha.partidos){
				for(int i=0;  i<2; i++){
					int k=0;

					Equipo equipo = i==0?partido.local:partido.visitante


					WritableSheet sheet = workbook.createSheet(equipo.nombre, j)

					//				WritableImage image = new WritableImage(
					//					2, 1,   //column, row
					//					4, 6,   //width, height in terms of number of cells
					//					grailsAttributes.getApplicationContext().getResource("/image/Logo planilla.png").getFile()); //Supports only 'png' images
					//
					//				sheet.addImage(image);
					//

					sheet.addCell(new Label(0,0,"LISTADO " + fecha.torneo.toString(),negrita))
					sheet.addCell(new Label(0,7,"Colegio: " + equipo.colegio.toString(),negrita))
					sheet.addCell(new Label(4,7,"TURNO:        CANCHA:       ",negrita))


					sheet.addCell(new Label(1,9,"Apellido y Nombre", cabecera))
					sheet.addCell(new Label(2,9,"D.N.I", cabecera))
					sheet.addCell(new Label(3,9,"Firma", cabecera))
					sheet.addCell(new Label(4,9,"Num", cabecera))
					sheet.addCell(new Label(5,9,"Goles", cabecera))
					sheet.addCell(new Label(6,9,"Am.", cabecera))
					sheet.addCell(new Label(7,9,"Exp.", cabecera))
					sheet.addCell(new Label(8,9,"Observaciones", cabecera))
					//					sheet.setRowView(9, dobleHeight);

					for(Jugador jugador: equipo.jugadores.sort { it.dni }){
						def boolean amonestado = false
						def String suspensionString;
						formatoJugador = valor;
						for(Suspension suspension : suspensionesTorneo){
							if(suspension.cantFechas > 3){
								if(jugador.dni == suspension.jugador.dni){
									formatoJugador = amonestadoFormat;
									amonestado=true;
									suspensionString=  "SUSPENDIDO "+ (suspension.getCantFechas()!=null?suspension.getCantFechas()+" FECHAS":"") + (suspension.cantPartidosRestantes!=null? " (" + suspension.cantPartidosRestantes + " FECHAS RESTANTES).": "INDETERMINADAMENTE.")
								}
							}else{
								if(suspension.getTorneo().id == fecha.torneo.id && jugador.dni == suspension.jugador.dni){
									formatoJugador = amonestadoFormat;
									amonestado=true;
									suspensionString=  "SUSPENDIDO "+ (suspension.getCantFechas()!=null?suspension.getCantFechas()+" FECHAS":"") + (suspension.cantPartidosRestantes!=null? " (" + suspension.cantPartidosRestantes + " FECHAS RESTANTES).": "INDETERMINADAMENTE.")
								}
							}
						}
						for(Suspension suspensionPermanente: suspensionesPermanentes){
							if(jugador.dni == suspensionPermanente.jugador.dni){
								formatoJugador = amonestadoFormat;
								amonestado=true;
								suspensionString=  "SUSPENSION PERMANENTE";
							}
						}

						for(Suspension suspensionProvisoria: suspensionesProvisorias){
							if(suspensionProvisoria.getTorneo().id == fecha.torneo.id && jugador.dni == suspensionProvisoria.jugador.dni){
								formatoJugador = amonestadoFormat;
								amonestado=true;
								suspensionString=  "SUSPENSION PROVISORIA";
							}
						}
						
						sheet.addCell(new Label(0, 10 + k, "" + (k +1), vAlignCenter))
						sheet.addCell(new Label(1, 10 + k, jugador.apellido + ", " + jugador.nombre, formatoJugador))
						sheet.addCell(new Number(2, 10 + k, jugador.dni, formatoJugador))
						sheet.addCell(new Label(4, 10 + k, "", formatoJugador))
						sheet.addCell(new Label(5, 10 + k, "", formatoJugador))
						sheet.addCell(new Label(6, 10 + k, "", formatoJugador))
						sheet.addCell(new Label(7, 10 + k, "", formatoJugador))
						sheet.addCell(new Label(8, 10 + k, "", formatoJugador))
						sheet.setRowView(10+k, medioHeight);
						if(amonestado){
							sheet.addCell(new Label(3, 10 + k, suspensionString, formatoJugador))
							sheet.mergeCells(3, 10 + k, 8, 10 + k);
						}else{
							sheet.addCell(new Label(3, 10 + k, "", formatoJugador))
						}
						k++
					}
					j++
					sheet.addCell(new Label(1,10+k, "Arbitro Sr: ",alignDerecha))
					sheet.addCell(new Label(2,10+k, " ",subrayado))
					sheet.addCell(new Label(4,10+k, "Firma: ",alignDerecha))
					sheet.addCell(new Label(5,10+k, " ",subrayado))
					sheet.addCell(new Label(6,10+k, " ",subrayado))
					sheet.addCell(new Label(7,10+k, " ",subrayado))
					sheet.setRowView(10+k, masDobleHeight);

					sheet.addCell(new Label(1,10+k+1, "Linea 1: ",alignDerecha))
					sheet.addCell(new Label(2,10+k+1, " ",subrayado))
					sheet.addCell(new Label(4,10+k+1, "Firma: ",alignDerecha))
					sheet.addCell(new Label(5,10+k+1, " ",subrayado))
					sheet.addCell(new Label(6,10+k+1, " ",subrayado))
					sheet.addCell(new Label(7,10+k+1, " ",subrayado))
					sheet.setRowView(10+k+1, dobleHeight);

					sheet.addCell(new Label(1,10+k+2, "Linea 2: ",alignDerecha))
					sheet.addCell(new Label(2,10+k+2, " ",subrayado))
					sheet.addCell(new Label(4,10+k+2, "Firma: ",alignDerecha))
					sheet.addCell(new Label(5,10+k+2, " ",subrayado))
					sheet.addCell(new Label(6,10+k+2, " ",subrayado))
					sheet.addCell(new Label(7,10+k+2, " ",subrayado))
					sheet.setRowView(10+k+2, dobleHeight);

					sheet.addCell(new Label(0,10+k+3, "Los datos incluidos en la planilla de juego son correctos y han sido verificados"))
					sheet.setRowView(10+k+3, masDobleHeight);

					sheet.addCell(new Label(1,10+k+4, "Capitan: ",alignDerecha))
					sheet.addCell(new Label(2,10+k+4, " ",subrayado))
					sheet.addCell(new Label(3,10+k+4, " ",subrayado))
					sheet.addCell(new Label(5,10+k+4, "Firma: ",alignDerecha))
					sheet.addCell(new Label(6,10+k+4, " ",subrayado))
					sheet.addCell(new Label(7,10+k+4, " ",subrayado))
					sheet.addCell(new Label(8,10+k+4, " ",subrayado))
					sheet.setRowView(10+k+4, masDobleHeight);



					int widthInChars = 4;
					for(int y=0; y<9;y++){
						if(y==0)
							widthInChars = 4;
						else if(y==3)
							widthInChars = 15;
						else if(y==4 || y==5 || y==6 || y==7)
							widthInChars = 6;
						else if(y==8)
							widthInChars = 15;
						else if(y==2)
							widthInChars = 9
						else if(y==1)
							widthInChars = 20

						sheet.setColumnView(y, widthInChars);
					}

					File imgFile = null;
					if (Environment.current == Environment.DEVELOPMENT) {
						imgFile = grailsAttributes.getApplicationContext().getResource("logo.png").getFile()
					} else if (Environment.current == Environment.PRODUCTION) {
						def url = 'https://torneobarker-barkeropenshift.rhcloud.com/assets/logo.png'
						def file = new File('logo.png').newOutputStream()
						file << new URL(url).openStream()
						file.close()

						imgFile=new File("logo.png");
					}


					WritableImage wImage = new WritableImage(
							2.5, 1,   //column, row
							1.5, 5,   //width, height in terms of number of cells
							new File("logo.png")); //Supports only 'png' images

					sheet.addImage(wImage);

				}

			}


			workbook.write();
			workbook.close();
		}catch(Exception e){
			e.printStackTrace()
			workbook.close()
			throw e
		}
	}


	def descargarPlanillaPosiciones(Torneo torneo){
		//		Torneo torneo = Torneo.get(params.get("torneoId"))
		Fecha fecha = torneo.ultFechaCargada

		int medioHeight = 15*20;
		int dobleHeight = 20*20;
		int masDobleHeight = 30*20;


		WritableCellFormat cabecera = new WritableCellFormat();
		cabecera.setBorder(Border.ALL, BorderLineStyle.THICK);

		WritableFont valorFont = new WritableFont(WritableFont.ARIAL, 12);
		WritableCellFormat valor = new WritableCellFormat(valorFont);
		valor.setBorder(Border.ALL, BorderLineStyle.THIN);

		WritableCellFormat alignDerecha = new WritableCellFormat();
		alignDerecha.setAlignment(Alignment.RIGHT);

		WritableCellFormat subrayado = new WritableCellFormat();
		subrayado.setBorder(Border.BOTTOM, BorderLineStyle.THIN);

		WritableCellFormat vAlignCenter = new WritableCellFormat();
		vAlignCenter.setVerticalAlignment(VerticalAlignment.CENTRE)

		WritableFont cellFont = new WritableFont(WritableFont.TIMES, 18);
		cellFont.setBoldStyle(WritableFont.BOLD);
		WritableCellFormat negrita = new WritableCellFormat(cellFont);
		negrita.setAlignment(Alignment.CENTRE)

		WritableCellFormat formatoJugador = valor;

		response.setContentType('application/vnd.ms-excel')
		response.setHeader('Content-Disposition', 'Attachment;Filename=Posiciones - '  + (fecha==null?"Inicial":fecha.toString()) + " - " + torneo.toString() + '.xls')
		WritableWorkbook workbook = Workbook.createWorkbook(response.outputStream)
		WritableSheet posicionesSheet = workbook.createSheet("Posiciones", 0)
		WritableSheet goleadoresSheet = workbook.createSheet("Goleadores", 1)
		WritableSheet fairPlaySheet = workbook.createSheet("Fair Play", 2)
		try{
			escribirPosiciones(posicionesSheet, negrita, torneo, fecha, cabecera, dobleHeight, formatoJugador, grailsAttributes, workbook)
			escribirGoleadores(goleadoresSheet, negrita, torneo, fecha, cabecera, dobleHeight, formatoJugador, grailsAttributes, workbook)
			escribirFairPlay(fairPlaySheet, negrita, torneo, fecha, cabecera, dobleHeight, formatoJugador, grailsAttributes, workbook)
		}catch(Exception e){
			e.printStackTrace()
			workbook.close()
			throw e
		}
		workbook.write();
		workbook.close();
	}

	private escribirPosiciones(WritableSheet sheet, WritableCellFormat negrita, Torneo torneo, Fecha fecha, WritableCellFormat cabecera, int dobleHeight, WritableCellFormat formatoJugador, org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes grailsAttributes, WritableWorkbook workbook) {
		sheet.mergeCells(1, 9, 10, 9);
		sheet.addCell(new Label(1, 9, "BARKER COLLEGE", negrita));

		sheet.mergeCells(1, 11, 10, 11);
		sheet.addCell(new Label(1, 11, torneo.toString(), negrita));

		sheet.mergeCells(1, 13, 10, 13);
		sheet.addCell(new Label(1, 13, "TABLA DE POSICIONES " + torneo.categoria.toString(), negrita));

		sheet.mergeCells(1, 14, 10, 14);
		sheet.addCell(new Label(1, 14, (fecha==null?"Inicial":fecha.toString()), negrita));

		List<EstadisticaEquipo> posicionesList= getPosicionesList(torneo)

		sheet.addCell(new Label(0,18,"POS", cabecera))
		sheet.addCell(new Label(1,18,"COLEGIO", cabecera))
		sheet.addCell(new Label(2,18,"PJ", cabecera))
		sheet.addCell(new Label(3,18,"PG", cabecera))
		sheet.addCell(new Label(4,18,"PE", cabecera))
		sheet.addCell(new Label(5,18,"PP", cabecera))
		sheet.addCell(new Label(6,18,"GF", cabecera))
		sheet.addCell(new Label(7,18,"GC", cabecera))
		sheet.addCell(new Label(8,18,"DG", cabecera))
		sheet.addCell(new Label(9,18,"Ptos.", cabecera))
		sheet.addCell(new Label(10,18,"Ptos.FP", cabecera))
		sheet.setRowView(18, dobleHeight);

		for(int i=0;i<posicionesList.size; i++){

			sheet.addCell(new Number(0, 19 + i, posicionesList.get(i).posicion, formatoJugador))
			sheet.addCell(new Label(1, 19 + i, "" + posicionesList.get(i).getEquipo().getColegio().toString(), formatoJugador))
			sheet.addCell(new Number(2, 19 + i,  posicionesList.get(i).partidosJugados, formatoJugador))
			sheet.addCell(new Number(3, 19 + i,  posicionesList.get(i).partidosGanados, formatoJugador))
			sheet.addCell(new Number(4, 19 + i, posicionesList.get(i).partidosEmpatados, formatoJugador))
			sheet.addCell(new Number(5, 19 + i, posicionesList.get(i).partidosPerdidos, formatoJugador))
			sheet.addCell(new Number(6, 19 + i, posicionesList.get(i).golesFavor, formatoJugador))
			sheet.addCell(new Number(7, 19 + i,  posicionesList.get(i).golesContra, formatoJugador))
			sheet.addCell(new Number(8, 19 + i, (posicionesList.get(i).golesFavor - posicionesList.get(i).golesContra), formatoJugador))
			sheet.addCell(new Number(9, 19 + i, posicionesList.get(i).puntos, formatoJugador))
			sheet.addCell(new Number(10, 19 + i, posicionesList.get(i).puntosFairPlay, formatoJugador))
			sheet.setRowView(19 + i, dobleHeight);
		}

		int widthInChars = 4;
		for(int j=0; j<11;j++){
			if(j==0)
				widthInChars = 6;
			else if(j==1)
				widthInChars = 25;
			else if(j==9)
				widthInChars = 6;
			else if(j==10)
				widthInChars = 8;
			else
				widthInChars = 4;
			sheet.setColumnView(j, widthInChars);
		}

		File imgFile = null;
		if (Environment.current == Environment.DEVELOPMENT) {
			imgFile = grailsAttributes.getApplicationContext().getResource("logo.png").getFile()
		} else if (Environment.current == Environment.PRODUCTION) {
			def url = 'http://torneo-barkeropenshift.rhcloud.com/assets/logo.png'
			def file = new File('logo.png').newOutputStream()
			file << new URL(url).openStream()
			file.close()

			imgFile=new File("logo.png");
		}


		WritableImage wImage = new WritableImage(
				2, 1,   //column, row
				4, 7,   //width, height in terms of number of cells
				new File("logo.png")); //Supports only 'png' images

		sheet.addImage(wImage);


	}
	private escribirGoleadores(WritableSheet sheet, WritableCellFormat negrita, Torneo torneo, Fecha fecha, WritableCellFormat cabecera, int dobleHeight, WritableCellFormat formatoJugador, org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes grailsAttributes, WritableWorkbook workbook) {
		sheet.mergeCells(0, 9, 4, 9);
		sheet.addCell(new Label(0, 9, "BARKER COLLEGE", negrita));
		
		sheet.mergeCells(0, 11, 4, 11);
		sheet.addCell(new Label(0, 11, torneo.toString(), negrita));
		
		sheet.mergeCells(0, 13, 4, 13);
		sheet.addCell(new Label(0, 13, "TABLA DE GOLEADORES " + torneo.categoria.toString(), negrita));
		
		sheet.mergeCells(0, 14, 4, 14);
		sheet.addCell(new Label(0, 14, (fecha==null?"Inicial":fecha.toString()), negrita));
		
		List<EstadisticaJugador> goleadoresList= EstadisticaJugadorController.getGoleadoresList(torneo)
				
				sheet.addCell(new Label(0,18,"POS", cabecera))
				sheet.addCell(new Label(1,18,"CANT. GOLES", cabecera))
				sheet.addCell(new Label(2,18,"JUGADOR", cabecera))
				sheet.addCell(new Label(3,18,"EQUIPO", cabecera))
				sheet.setRowView(18, dobleHeight);
		
		for(int i=0;i<goleadoresList.size; i++){
			
			sheet.addCell(new Number(0, 19 + i, goleadoresList.get(i).posicion, formatoJugador))
			sheet.addCell(new Number(1, 19 + i, goleadoresList.get(i).golesFavor, formatoJugador))
			sheet.addCell(new Label(2, 19 + i,  goleadoresList.get(i).jugador.nombre + " " + goleadoresList.get(i).jugador.apellido, formatoJugador))
			sheet.addCell(new Label(3, 19 + i,  goleadoresList.get(i).equipo.toString(), formatoJugador))
			sheet.setRowView(19 + i, dobleHeight);
		}
		
		int widthInChars = 6;
		for(int j=0; j<11;j++){
			if(j==0)
				widthInChars = 6;
			else if(j==1)
				widthInChars = 13;
			else if(j==2)
				widthInChars = 30;
			else if(j==3)
				widthInChars = 30;
			else
				widthInChars = 6;
			sheet.setColumnView(j, widthInChars);
		}
		
		File imgFile = null;
		if (Environment.current == Environment.DEVELOPMENT) {
			imgFile = grailsAttributes.getApplicationContext().getResource("logo.png").getFile()
		} else if (Environment.current == Environment.PRODUCTION) {
			def url = 'https://torneobarker-barkeropenshift.rhcloud.com/assets/logo.png'
					def file = new File('logo.png').newOutputStream()
					file << new URL(url).openStream()
					file.close()
					
					imgFile=new File("logo.png");
		}
		
		
		WritableImage wImage = new WritableImage(
				2.5, 1,   //column, row
				0.6, 8,   //width, height in terms of number of cells
				new File("logo.png")); //Supports only 'png' images
		
		sheet.addImage(wImage);
		
		
	}
	private escribirFairPlay(WritableSheet sheet, WritableCellFormat negrita, Torneo torneo, Fecha fecha, WritableCellFormat cabecera, int dobleHeight, WritableCellFormat formatoJugador, org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes grailsAttributes, WritableWorkbook workbook) {
		sheet.mergeCells(0, 9, 5, 9);
		sheet.addCell(new Label(0, 9, "BARKER COLLEGE", negrita));
		
		sheet.mergeCells(0, 11, 5, 11);
		sheet.addCell(new Label(0, 11, torneo.toString(), negrita));
		
		sheet.mergeCells(0, 13, 5, 13);
		sheet.addCell(new Label(0, 13, "TABLA DE FAIR PLAY " + torneo.categoria.toString(), negrita));
		
		sheet.mergeCells(0, 14, 5, 14);
		sheet.addCell(new Label(0, 14, (fecha==null?"Inicial":fecha.toString()), negrita));
		
		List<EstadisticaFairPlay> posicionesList= EstadisticaFairPlayController.getFairPlayList(torneo);
				
				sheet.addCell(new Label(0,18,"POS", cabecera))
				sheet.addCell(new Label(1,18, "Ptos. FP", cabecera))
				sheet.addCell(new Label(2,18,"Equipo", cabecera))
				sheet.addCell(new Label(3,18,"Susp. Graves", cabecera))
				sheet.addCell(new Label(4,18,"TR", cabecera))
				sheet.addCell(new Label(5,18,"TA", cabecera))
				sheet.setRowView(18, dobleHeight);
		
		for(int i=0;i<posicionesList.size; i++){
			
			sheet.addCell(new Number(0, 19 + i, posicionesList.get(i).posicion, formatoJugador))
			sheet.addCell(new Number(1, 19 + i, posicionesList.get(i).puntos, formatoJugador))
			sheet.addCell(new Label(2, 19 + i,  posicionesList.get(i).getEquipo().getColegio().toString(), formatoJugador))
			sheet.addCell(new Number(3, 19 + i,  posicionesList.get(i).cantSansionesGraves, formatoJugador))
			sheet.addCell(new Number(4, 19 + i, posicionesList.get(i).tarjetasRojas, formatoJugador))
			sheet.addCell(new Number(5, 19 + i, posicionesList.get(i).tarjetasAmarillas, formatoJugador))
			sheet.setRowView(19 + i, dobleHeight);
		}
		
		int widthInChars = 6;
		for(int j=0; j<11;j++){
			if(j==0)
				widthInChars = 6;
			else if(j==1)
				widthInChars = 12;
			else if(j==2)
				widthInChars = 25;
			else if(j==3)
				widthInChars = 15;
			else
				widthInChars = 6;
			sheet.setColumnView(j, widthInChars);
		}
		
		File imgFile = null;
		if (Environment.current == Environment.DEVELOPMENT) {
			imgFile = grailsAttributes.getApplicationContext().getResource("logo.png").getFile()
		} else if (Environment.current == Environment.PRODUCTION) {
			def url = 'https://torneobarker-barkeropenshift.rhcloud.com/assets/logo.png'
					def file = new File('logo.png').newOutputStream()
					file << new URL(url).openStream()
					file.close()
					
					imgFile=new File("logo.png");
		}
		
		
		WritableImage wImage = new WritableImage(
				2.4, 1,   //column, row
				0.7, 7,   //width, height in terms of number of cells
				new File("logo.png")); //Supports only 'png' images
		
		sheet.addImage(wImage);
		
		
	}
}
