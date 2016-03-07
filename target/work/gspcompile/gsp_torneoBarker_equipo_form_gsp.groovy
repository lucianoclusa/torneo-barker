import ar.com.torneobarker.Equipo
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_torneoBarker_equipo_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/equipo/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: equipoInstance, field: 'nombre', 'error'))
printHtmlPart(1)
invokeTag('message','g',7,['code':("equipo.nombre.label"),'default':("Nombre")],-1)
printHtmlPart(2)
invokeTag('textField','g',10,['name':("nombre"),'value':(equipoInstance?.nombre)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: equipoInstance, field: 'colegio', 'error'))
printHtmlPart(4)
invokeTag('message','g',16,['code':("equipo.colegio.label"),'default':("Colegio")],-1)
printHtmlPart(2)
invokeTag('select','g',19,['id':("colegio"),'name':("colegio.id"),'from':(ar.com.torneobarker.Colegio.list()),'optionKey':("id"),'value':(equipoInstance?.colegio?.id),'class':("many-to-one"),'noSelection':(['null': ''])],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: equipoInstance, field: 'categoria', 'error'))
printHtmlPart(5)
invokeTag('message','g',25,['code':("equipo.categoria.label"),'default':("Categoria")],-1)
printHtmlPart(6)
invokeTag('select','g',28,['id':("categoria"),'name':("categoria.id"),'from':(ar.com.torneobarker.Categoria.list()),'optionKey':("id"),'required':(""),'value':(equipoInstance?.categoria?.id),'class':("many-to-one")],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: equipoInstance, field: 'descripcion', 'error'))
printHtmlPart(7)
invokeTag('message','g',34,['code':("equipo.descripcion.label"),'default':("Descripcion")],-1)
printHtmlPart(6)
invokeTag('textField','g',37,['name':("descripcion"),'required':(""),'value':(equipoInstance?.descripcion)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: equipoInstance, field: 'fechaCreacion', 'error'))
printHtmlPart(8)
invokeTag('message','g',43,['code':("equipo.fechaCreacion.label"),'default':("Fecha Creacion")],-1)
printHtmlPart(6)
invokeTag('datePicker','g',46,['name':("fechaCreacion"),'precision':("day"),'value':(equipoInstance?.fechaCreacion)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: equipoInstance, field: 'jugadores', 'error'))
printHtmlPart(9)
invokeTag('message','g',52,['code':("equipo.jugadores.label"),'default':("Jugadores")],-1)
printHtmlPart(2)
invokeTag('select','g',55,['name':("jugadores"),'from':(ar.com.torneobarker.Jugador.list()),'style':("height:300px; width:300px"),'multiple':("multiple"),'optionKey':("id"),'size':("5"),'value':(equipoInstance?.jugadores*.id),'class':("many-to-many")],-1)
printHtmlPart(10)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1447699278505L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
