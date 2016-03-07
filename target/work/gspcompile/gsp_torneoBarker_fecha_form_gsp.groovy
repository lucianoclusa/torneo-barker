import ar.com.torneobarker.Fecha
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_torneoBarker_fecha_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/fecha/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: fechaInstance, field: 'equipoLibre', 'error'))
printHtmlPart(1)
invokeTag('message','g',7,['code':("fecha.equipoLibre.label"),'default':("Equipo Libre")],-1)
printHtmlPart(2)
invokeTag('select','g',10,['id':("equipoLibre"),'name':("equipoLibre.id"),'from':(ar.com.torneobarker.Equipo.list()),'optionKey':("id"),'value':(fechaInstance?.equipoLibre?.id),'class':("many-to-one"),'noSelection':(['null': ''])],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: fechaInstance, field: 'descripcion', 'error'))
printHtmlPart(4)
invokeTag('message','g',16,['code':("fecha.descripcion.label"),'default':("Descripcion")],-1)
printHtmlPart(5)
invokeTag('textField','g',19,['name':("descripcion"),'required':(""),'value':(fechaInstance?.descripcion)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: fechaInstance, field: 'numeroFecha', 'error'))
printHtmlPart(6)
invokeTag('message','g',25,['code':("fecha.numeroFecha.label"),'default':("Numero Fecha")],-1)
printHtmlPart(5)
invokeTag('field','g',28,['name':("numeroFecha"),'type':("number"),'value':(fechaInstance.numeroFecha),'required':("")],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: fechaInstance, field: 'partidos', 'error'))
printHtmlPart(7)
invokeTag('message','g',34,['code':("fecha.partidos.label"),'default':("Partidos")],-1)
printHtmlPart(8)
for( p in (fechaInstance?.partidos) ) {
printHtmlPart(9)
createTagBody(2, {->
expressionOut.print(p?.encodeAsHTML())
})
invokeTag('link','g',40,['controller':("partido"),'action':("show"),'id':(p.id)],2)
printHtmlPart(10)
}
printHtmlPart(11)
createTagBody(1, {->
expressionOut.print(message(code: 'default.add.label', args: [message(code: 'partido.label', default: 'Partido')]))
})
invokeTag('link','g',43,['controller':("partido"),'action':("create"),'params':(['fecha.id': fechaInstance?.id])],1)
printHtmlPart(12)
expressionOut.print(hasErrors(bean: fechaInstance, field: 'torneo', 'error'))
printHtmlPart(13)
invokeTag('message','g',52,['code':("fecha.torneo.label"),'default':("Torneo")],-1)
printHtmlPart(5)
invokeTag('select','g',55,['id':("torneo"),'name':("torneo.id"),'from':(ar.com.torneobarker.Torneo.list()),'optionKey':("id"),'required':(""),'value':(fechaInstance?.torneo?.id),'class':("many-to-one")],-1)
printHtmlPart(14)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1443191647775L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
