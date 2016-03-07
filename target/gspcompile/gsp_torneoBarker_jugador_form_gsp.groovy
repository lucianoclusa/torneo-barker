import ar.com.torneobarker.Jugador
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_torneoBarker_jugador_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/jugador/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: jugadorInstance, field: 'dni', 'error'))
printHtmlPart(1)
invokeTag('message','g',5,['code':("jugador.dni.label"),'default':("Dni")],-1)
printHtmlPart(2)
invokeTag('textField','g',8,['name':("dni"),'required':(""),'value':(jugadorInstance?.dni)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: jugadorInstance, field: 'nombre', 'error'))
printHtmlPart(4)
invokeTag('message','g',14,['code':("jugador.nombre.label"),'default':("Nombre")],-1)
printHtmlPart(5)
invokeTag('textField','g',17,['name':("nombre"),'value':(jugadorInstance?.nombre)],-1)
printHtmlPart(6)
expressionOut.print(hasErrors(bean: jugadorInstance, field: 'apellido', 'error'))
printHtmlPart(7)
invokeTag('message','g',24,['code':("jugador.apellido.label"),'default':("Apellido")],-1)
printHtmlPart(2)
invokeTag('textField','g',27,['name':("apellido"),'required':(""),'value':(jugadorInstance?.apellido)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: jugadorInstance, field: 'fechaNacimiento', 'error'))
printHtmlPart(8)
invokeTag('message','g',33,['code':("jugador.fechaNacimiento.label"),'default':("Fecha Nacimiento")],-1)
printHtmlPart(2)
invokeTag('datePicker','g',36,['name':("fechaNacimiento"),'precision':("day"),'value':(jugadorInstance?.fechaNacimiento)],-1)
printHtmlPart(9)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1445347903843L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
