import ar.com.torneobarker.Torneo
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_torneoBarker_torneo_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/torneo/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: torneoInstance, field: 'anio', 'error'))
printHtmlPart(1)
invokeTag('message','g',4,['code':("torneo.anio.label"),'default':("Año")],-1)
printHtmlPart(2)
invokeTag('select','g',7,['id':("anio"),'name':("anio"),'from':(2000..2100),'required':(""),'value':(torneoInstance?.anio),'noSelection':(['':'-Seleccione año-'])],-1)
printHtmlPart(3)
if(true && (torneoInstance.temporada==null)) {
printHtmlPart(0)
expressionOut.print(hasErrors(bean: torneoInstance, field: 'temporada', 'error'))
printHtmlPart(4)
invokeTag('message','g',12,['code':("torneo.temporada.label"),'default':("Temporada")],-1)
printHtmlPart(2)
invokeTag('select','g',15,['id':("temporada"),'name':("temporada.id"),'optionValue':("descripcion"),'from':(ar.com.torneobarker.Temporada.list()),'optionKey':("id"),'required':(""),'value':(torneoInstance?.temporada?.id),'class':("many-to-one")],-1)
printHtmlPart(3)
}
printHtmlPart(5)
if(true && (torneoInstance.categoria==null)) {
printHtmlPart(6)
expressionOut.print(hasErrors(bean: torneoInstance, field: 'categoria', 'error'))
printHtmlPart(7)
invokeTag('message','g',22,['code':("torneo.categoria.label"),'default':("Categoria")],-1)
printHtmlPart(2)
invokeTag('select','g',25,['id':("categoria"),'name':("categoria.id"),'optionValue':("descripcion"),'from':(ar.com.torneobarker.Categoria.list()),'optionKey':("id"),'required':(""),'value':(torneoInstance?.categoria?.id),'class':("many-to-one"),'onchange':("categoriaChanged(this.value)")],-1)
printHtmlPart(8)
expressionOut.print(createLink(controller: 'equipo', action: 'categoriaChanged'))
printHtmlPart(9)
}
printHtmlPart(5)
if(true && (torneoInstance.equipos==null)) {
printHtmlPart(0)
expressionOut.print(hasErrors(bean: torneoInstance, field: 'equipos', 'error'))
printHtmlPart(10)
invokeTag('message','g',42,['code':("torneo.equipos.label"),'default':("Equipos")],-1)
printHtmlPart(11)
invokeTag('select','g',46,['id':("equipos"),'name':("equipos"),'from':(ar.com.torneobarker.Equipo.findAllByCategoria(ar.com.torneobarker.Categoria.list().get(0))),'style':("height:300px; width:300px"),'multiple':("true"),'optionKey':("id"),'size':("5"),'value':(torneoInstante?.equipos*.id),'class':("many-to-many")],-1)
printHtmlPart(12)
}
printHtmlPart(0)
expressionOut.print(hasErrors(bean: torneoInstance, field: 'fechaInicio', 'error'))
printHtmlPart(13)
invokeTag('message','g',53,['code':("torneo.fechaInicio.label"),'default':("Fecha Inicio")],-1)
printHtmlPart(2)
invokeTag('datePicker','g',56,['name':("fechaInicio"),'precision':("day"),'value':(torneoInstance?.fechaInicio)],-1)
printHtmlPart(14)
expressionOut.print(hasErrors(bean: torneoInstance, field: 'fechaFin', 'error'))
printHtmlPart(15)
invokeTag('message','g',61,['code':("torneo.fechaFin.label"),'default':("Fecha Fin")],-1)
printHtmlPart(2)
invokeTag('datePicker','g',64,['name':("fechaFin"),'precision':("day"),'value':(torneoInstance?.fechaFin)],-1)
printHtmlPart(16)
if(true && (false)) {
printHtmlPart(17)
for( fecha in (torneoInstance.fechas.sort { it.numeroFecha }) ) {
printHtmlPart(18)
if(true && (partido.resultado==null)) {
printHtmlPart(19)
invokeTag('message','g',74,['code':("partido.local.label"),'default':(fecha?.descripcion?.encodeAsHTML())],-1)
printHtmlPart(20)
}
printHtmlPart(21)
loop:{
int i = 0
for( partido in (fecha.getPartidos().sort { it.id }) ) {
printHtmlPart(22)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(23)
createTagBody(4, {->
expressionOut.print(fieldValue(bean: partido, field: "local.nombre"))
})
invokeTag('link','g',81,['controller':("equipo"),'action':("show"),'id':(partido.local.id)],4)
printHtmlPart(24)
expressionOut.print(partido.golesLocal==null?"-":partido.golesLocal)
printHtmlPart(25)
expressionOut.print(partido.golesVisitante==null?"-":partido.golesVisitante)
printHtmlPart(26)
createTagBody(4, {->
expressionOut.print(fieldValue(bean: partido, field: "visitante.nombre"))
})
invokeTag('link','g',85,['controller':("equipo"),'action':("show"),'id':(partido.visitante.id)],4)
printHtmlPart(27)
i++
}
}
printHtmlPart(28)
if(true && (fecha.equipoLibre==null?false:true)) {
printHtmlPart(29)
invokeTag('select','g',90,['id':("equipoLibre"),'name':("equipoLibre.id"),'from':(torneoInstance.equipos),'optionKey':("id"),'optionValue':("nombre"),'required':(""),'value':(fecha.equipoLibre?.id),'class':("many-to-one")],-1)
printHtmlPart(30)
}
printHtmlPart(31)
}
printHtmlPart(3)
}
printHtmlPart(5)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1455115058422L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
