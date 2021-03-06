import ar.com.torneobarker.Gol
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_torneoBarker_gol_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/gol/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: golInstance, field: 'enContra', 'error'))
printHtmlPart(1)
invokeTag('message','g',7,['code':("gol.enContra.label"),'default':("En Contra")],-1)
printHtmlPart(2)
invokeTag('checkBox','g',10,['name':("enContra"),'value':(golInstance?.enContra)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: golInstance, field: 'jugador', 'error'))
printHtmlPart(4)
invokeTag('message','g',16,['code':("gol.jugador.label"),'default':("Jugador")],-1)
printHtmlPart(5)
invokeTag('select','g',19,['id':("jugador"),'name':("jugador.id"),'from':(ar.com.torneobarker.Jugador.list()),'optionKey':("id"),'required':(""),'value':(golInstance?.jugador?.id),'class':("many-to-one")],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: golInstance, field: 'partido', 'error'))
printHtmlPart(6)
invokeTag('message','g',25,['code':("gol.partido.label"),'default':("Partido")],-1)
printHtmlPart(5)
invokeTag('select','g',28,['id':("partido"),'name':("partido.id"),'from':(ar.com.torneobarker.Partido.list()),'optionKey':("id"),'required':(""),'value':(golInstance?.partido?.id),'class':("many-to-one")],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: golInstance, field: 'torneo', 'error'))
printHtmlPart(7)
invokeTag('message','g',34,['code':("gol.torneo.label"),'default':("Torneo")],-1)
printHtmlPart(5)
invokeTag('select','g',37,['id':("torneo"),'name':("torneo.id"),'from':(ar.com.torneobarker.Torneo.list()),'optionKey':("id"),'required':(""),'value':(golInstance?.torneo?.id),'class':("many-to-one")],-1)
printHtmlPart(8)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1447444753430L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
