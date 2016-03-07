import ar.com.torneobarker.Suspension
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_torneoBarker_suspensionindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/suspension/index.gsp" }
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
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'suspension.label', default: 'Suspension'))],-1)
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
invokeTag('form','g',27,['controller':("suspension"),'action':("searchSuspensiones")],2)
printHtmlPart(14)
invokeTag('message','g',30,['code':("default.list.label"),'args':([entityName])],-1)
printHtmlPart(15)
if(true && (flash.message)) {
printHtmlPart(16)
expressionOut.print(flash.message)
printHtmlPart(17)
}
printHtmlPart(18)
invokeTag('sortableColumn','g',38,['property':("fechaSancion"),'title':(message(code: 'suspension.fechaInicio.label', default: 'Fecha Susp.'))],-1)
printHtmlPart(19)
invokeTag('sortableColumn','g',40,['property':("cantFechas"),'title':(message(code: 'suspension.cantFechas.label', default: 'Cant.'))],-1)
printHtmlPart(20)
invokeTag('sortableColumn','g',42,['property':("descripcion"),'title':(message(code: 'suspension.descripcion.label', default: 'Descripción'))],-1)
printHtmlPart(21)
invokeTag('sortableColumn','g',44,['property':("jugador"),'title':(message(code: 'suspension.jugador.label', default: 'Jugador'))],-1)
printHtmlPart(21)
invokeTag('sortableColumn','g',46,['property':("equipo"),'title':(message(code: 'suspension.equipo.label', default: 'Equipo'))],-1)
printHtmlPart(21)
invokeTag('sortableColumn','g',48,['property':("torneo"),'title':(message(code: 'suspension.torneo.label', default: 'Torneo'))],-1)
printHtmlPart(22)
invokeTag('sortableColumn','g',50,['property':("fecha"),'title':(message(code: 'suspension.fecha.label', default: 'Fecha N°'))],-1)
printHtmlPart(23)
loop:{
int i = 0
for( suspensionInstance in (suspensionInstanceList) ) {
printHtmlPart(24)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(25)
createTagBody(3, {->
printHtmlPart(26)
expressionOut.print(createLinkTo(dir: 'images', file: 'lupa.png'))
printHtmlPart(27)
})
invokeTag('link','g',58,['action':("show"),'id':(suspensionInstance.id)],3)
printHtmlPart(28)
invokeTag('formatDate','g',60,['format':("dd/MM/yyyy"),'date':(suspensionInstance.fechaSancion)],-1)
printHtmlPart(29)
expressionOut.print(fieldValue(bean: suspensionInstance, field: "cantFechas"))
printHtmlPart(30)
expressionOut.print(fieldValue(bean: suspensionInstance, field: "descripcion"))
printHtmlPart(30)
expressionOut.print(fieldValue(bean: suspensionInstance, field: "jugador"))
printHtmlPart(29)
expressionOut.print(fieldValue(bean: suspensionInstance, field: "equipo.colegio"))
printHtmlPart(30)
expressionOut.print(fieldValue(bean: suspensionInstance, field: "torneo"))
printHtmlPart(30)
expressionOut.print(fieldValue(bean: suspensionInstance, field: "fecha"))
printHtmlPart(31)
i++
}
}
printHtmlPart(32)
invokeTag('paginate','g',79,['total':(suspensionInstanceCount ?: 0)],-1)
printHtmlPart(33)
})
invokeTag('captureBody','sitemesh',82,[:],1)
printHtmlPart(34)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1455117636640L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
