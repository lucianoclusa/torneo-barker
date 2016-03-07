import ar.com.torneobarker.Jugador
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_torneoBarker_jugadorindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/jugador/index.gsp" }
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
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'jugador.label', default: 'Jugador'))],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',8,['code':("default.list.label"),'args':([entityName])],-1)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',11,['code':("default.link.skip.label"),'default':("Skip to content&hellip;")],-1)
printHtmlPart(5)
expressionOut.print(createLink(uri: '/'))
printHtmlPart(6)
invokeTag('message','g',14,['code':("default.home.label")],-1)
printHtmlPart(7)
createTagBody(2, {->
invokeTag('message','g',15,['code':("default.new.label"),'args':([entityName])],-1)
})
invokeTag('link','g',15,['class':("create"),'action':("create")],2)
printHtmlPart(8)
createTagBody(2, {->
printHtmlPart(9)
expressionOut.print(hasErrors(bean: suspensionInstance, field: 'descripcion', 'error'))
printHtmlPart(10)
invokeTag('message','g',22,['code':("suspension.jugador.label"),'default':("Jugador")],-1)
printHtmlPart(11)
invokeTag('textField','g',24,['name':("jugador"),'value':(jugador),'placeholder':("Buscar Jugador")],-1)
printHtmlPart(12)
invokeTag('submitButton','g',25,['name':("Buscar")],-1)
printHtmlPart(13)
})
invokeTag('form','g',27,['controller':("jugador"),'action':("searchJugador")],2)
printHtmlPart(14)
invokeTag('message','g',30,['code':("default.list.label"),'args':([entityName])],-1)
printHtmlPart(15)
if(true && (flash.message)) {
printHtmlPart(16)
expressionOut.print(flash.message)
printHtmlPart(17)
}
printHtmlPart(18)
invokeTag('sortableColumn','g',38,['property':("dni"),'title':(message(code: 'jugador.dni.label', default: 'Dni'))],-1)
printHtmlPart(19)
invokeTag('sortableColumn','g',40,['property':("nombre"),'title':(message(code: 'jugador.nombre.label', default: 'Nombre'))],-1)
printHtmlPart(20)
invokeTag('sortableColumn','g',42,['property':("apellido"),'title':(message(code: 'jugador.apellido.label', default: 'Apellido'))],-1)
printHtmlPart(20)
invokeTag('sortableColumn','g',44,['property':("fechaNacimiento"),'title':(message(code: 'jugador.fechaNacimiento.label', default: 'Fecha Nacimiento'))],-1)
printHtmlPart(21)
loop:{
int i = 0
for( jugadorInstance in (jugadorInstanceList) ) {
printHtmlPart(22)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(23)
createTagBody(3, {->
printHtmlPart(24)
expressionOut.print(createLinkTo(dir: 'images', file: 'lupa.png'))
printHtmlPart(25)
})
invokeTag('link','g',51,['action':("show"),'id':(jugadorInstance.id)],3)
printHtmlPart(26)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "dni"))
printHtmlPart(27)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "nombre"))
printHtmlPart(26)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "apellido"))
printHtmlPart(26)
invokeTag('formatDate','g',59,['format':("dd/MM/yyyy"),'date':(jugadorInstance.fechaNacimiento)],-1)
printHtmlPart(28)
i++
}
}
printHtmlPart(29)
invokeTag('paginate','g',66,['total':(jugadorInstanceCount ?: 0)],-1)
printHtmlPart(30)
})
invokeTag('captureBody','sitemesh',69,[:],1)
printHtmlPart(31)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1454617434442L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
