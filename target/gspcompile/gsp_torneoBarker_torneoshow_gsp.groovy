import ar.com.torneobarker.Torneo
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_torneoBarker_torneoshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/torneo/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(2)
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'torneo.label', default: 'Torneo'))],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
expressionOut.print(torneoInstance.temporada.descripcion)
printHtmlPart(3)
expressionOut.print(torneoInstance.anio)
printHtmlPart(3)
expressionOut.print(torneoInstance.categoria.descripcion)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(4)
})
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
invokeTag('message','g',11,['code':("default.link.skip.label"),'default':("Skip to content&hellip;")],-1)
printHtmlPart(6)
expressionOut.print(createLink(uri: '/'))
printHtmlPart(7)
invokeTag('message','g',14,['code':("default.home.label")],-1)
printHtmlPart(8)
createClosureForHtmlPart(9, 2)
invokeTag('link','g',15,['class':("fixture"),'controller':("Torneo"),'id':(torneoInstance?.id),'action':("show")],2)
printHtmlPart(10)
createClosureForHtmlPart(11, 2)
invokeTag('link','g',16,['class':("posiciones"),'controller':("estadisticaEquipo"),'id':(torneoInstance?.id),'action':("showPosiciones")],2)
printHtmlPart(10)
createClosureForHtmlPart(12, 2)
invokeTag('link','g',17,['class':("goleadores"),'controller':("estadisticaJugador"),'id':(torneoInstance?.id),'action':("showGoleadores")],2)
printHtmlPart(10)
createClosureForHtmlPart(13, 2)
invokeTag('link','g',18,['class':("fairPlay"),'controller':("estadisticaFairPlay"),'id':(torneoInstance?.id),'action':("showFairPlay")],2)
printHtmlPart(14)
expressionOut.print(torneoInstance.temporada.descripcion)
printHtmlPart(3)
expressionOut.print(torneoInstance.anio)
printHtmlPart(3)
expressionOut.print(torneoInstance.categoria.descripcion)
printHtmlPart(15)
if(true && (flash.message)) {
printHtmlPart(16)
expressionOut.print(flash.message)
printHtmlPart(17)
}
printHtmlPart(18)
if(true && (torneoInstance?.temporada)) {
printHtmlPart(19)
invokeTag('message','g',31,['code':("torneo.temporada.label"),'default':("Temporada")],-1)
printHtmlPart(20)
expressionOut.print(torneoInstance?.temporada?.descripcion?.encodeAsHTML())
printHtmlPart(21)
}
printHtmlPart(22)
if(true && (torneoInstance?.categoria)) {
printHtmlPart(23)
invokeTag('message','g',40,['code':("torneo.categoria.label"),'default':("Categoria")],-1)
printHtmlPart(24)
expressionOut.print(torneoInstance?.categoria?.descripcion?.encodeAsHTML())
printHtmlPart(21)
}
printHtmlPart(25)
if(true && (torneoInstance?.fechaInicio)) {
printHtmlPart(26)
invokeTag('message','g',49,['code':("torneo.fechaInicio.label"),'default':("Fecha Inicio")],-1)
printHtmlPart(27)
invokeTag('formatDate','g',51,['format':("dd/MM/yyyy"),'date':(torneoInstance?.fechaInicio)],-1)
printHtmlPart(21)
}
printHtmlPart(28)
if(true && (torneoInstance?.fechaFin)) {
printHtmlPart(29)
invokeTag('message','g',58,['code':("torneo.fechaFin.label"),'default':("Fecha Fin")],-1)
printHtmlPart(30)
invokeTag('formatDate','g',60,['format':("dd/MM/yyyy"),'date':(torneoInstance?.fechaFin)],-1)
printHtmlPart(21)
}
printHtmlPart(31)
if(true && (torneoInstance?.fechas)) {
printHtmlPart(32)
for( fecha in (torneoInstance.fechas.sort { it.numeroFecha }) ) {
printHtmlPart(33)
invokeTag('message','g',76,['code':("partido.local.label"),'default':(fecha?.descripcion?.encodeAsHTML())],-1)
printHtmlPart(34)
createTagBody(4, {->
printHtmlPart(35)
expressionOut.print(createLinkTo(dir: 'images', file: 'excel-xls-icon.png'))
printHtmlPart(36)
})
invokeTag('link','g',77,['class':("no-print"),'controller':("estadisticaEquipo"),'action':("descargarPlanillaFecha"),'params':([fechaId: fecha.id]),'align':("center")],4)
printHtmlPart(37)
loop:{
int i = 0
for( partido in (fecha.partidos.sort { it.id }) ) {
printHtmlPart(38)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(39)
createTagBody(5, {->
expressionOut.print(fieldValue(bean: partido, field: "local.nombre"))
})
invokeTag('link','g',83,['controller':("equipo"),'action':("show"),'id':(partido.local.id)],5)
printHtmlPart(40)
expressionOut.print(partido.golesLocal==null?"-":partido.golesLocal)
printHtmlPart(41)
expressionOut.print(partido.golesVisitante==null?"-":partido.golesVisitante)
printHtmlPart(42)
createTagBody(5, {->
expressionOut.print(fieldValue(bean: partido, field: "visitante.nombre"))
})
invokeTag('link','g',87,['controller':("equipo"),'action':("show"),'id':(partido.visitante.id)],5)
printHtmlPart(43)
if(true && (partido.resultado!=null)) {
printHtmlPart(44)
createTagBody(6, {->
printHtmlPart(45)
expressionOut.print(createLinkTo(dir: 'images', file: 'delete_icon.png'))
printHtmlPart(36)
})
invokeTag('link','g',89,['class':("no-print"),'controller':("resultado"),'action':("eliminarResultado"),'params':([resultadoId: partido.resultado.id]),'style':("")],6)
printHtmlPart(46)
createTagBody(6, {->
printHtmlPart(47)
expressionOut.print(createLinkTo(dir: 'images', file: 'lupa.png'))
printHtmlPart(36)
})
invokeTag('link','g',90,['class':("no-print"),'controller':("resultado"),'action':("show"),'params':([partidoId: partido.id]),'style':("")],6)
printHtmlPart(48)
}
else {
printHtmlPart(49)
createTagBody(6, {->
printHtmlPart(50)
expressionOut.print(createLinkTo(dir: 'images', file: 'cargar_resultado.png'))
printHtmlPart(36)
})
invokeTag('link','g',93,['class':("no-print"),'controller':("resultado"),'action':("cargarResultado"),'params':([partidoId: partido.id]),'style':("")],6)
printHtmlPart(51)
}
printHtmlPart(52)
i++
}
}
printHtmlPart(53)
if(true && (fecha.equipoLibre==null?false:true)) {
printHtmlPart(54)
expressionOut.print(fecha.equipoLibre?.nombre)
printHtmlPart(55)
}
printHtmlPart(56)
}
printHtmlPart(57)
}
printHtmlPart(58)
createTagBody(2, {->
printHtmlPart(59)
createTagBody(3, {->
invokeTag('message','g',111,['code':("default.button.edit.label"),'default':("Edit")],-1)
})
invokeTag('link','g',111,['class':("edit"),'action':("edit"),'resource':(torneoInstance)],3)
printHtmlPart(60)
invokeTag('actionSubmit','g',112,['class':("delete"),'action':("delete"),'value':(message(code: 'default.button.delete.label', default: 'Delete')),'onclick':("return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');")],-1)
printHtmlPart(61)
})
invokeTag('form','g',114,['url':([resource:torneoInstance, action:'delete']),'method':("DELETE")],2)
printHtmlPart(62)
})
invokeTag('captureBody','sitemesh',116,[:],1)
printHtmlPart(63)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1454612504579L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
