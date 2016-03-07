import ar.com.torneobarker.Suspension
import ar.com.torneobarker.Fecha
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_torneoBarker_suspensionshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/suspension/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',7,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(2)
invokeTag('set','g',8,['var':("entityName"),'value':(message(code: 'suspension.label', default: 'Suspension'))],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',9,['code':("default.show.label"),'args':([entityName])],-1)
})
invokeTag('captureTitle','sitemesh',9,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',9,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',10,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',12,['code':("default.link.skip.label"),'default':("Skip to content&hellip;")],-1)
printHtmlPart(5)
expressionOut.print(createLink(uri: '/'))
printHtmlPart(6)
invokeTag('message','g',15,['code':("default.home.label")],-1)
printHtmlPart(7)
createTagBody(2, {->
invokeTag('message','g',16,['code':("default.list.label"),'args':([entityName])],-1)
})
invokeTag('link','g',16,['class':("list"),'action':("index")],2)
printHtmlPart(8)
createTagBody(2, {->
invokeTag('message','g',17,['code':("default.new.label"),'args':([entityName])],-1)
})
invokeTag('link','g',17,['class':("create"),'action':("create")],2)
printHtmlPart(9)
invokeTag('message','g',21,['code':("default.show.label"),'args':([entityName])],-1)
printHtmlPart(10)
if(true && (flash.message)) {
printHtmlPart(11)
expressionOut.print(flash.message)
printHtmlPart(12)
}
printHtmlPart(13)
if(true && (suspensionInstance?.jugador)) {
printHtmlPart(14)
invokeTag('message','g',29,['code':("suspension.jugador.label"),'default':("Jugador")],-1)
printHtmlPart(15)
createTagBody(3, {->
expressionOut.print(suspensionInstance?.jugador?.encodeAsHTML())
})
invokeTag('link','g',31,['controller':("jugador"),'action':("show"),'id':(suspensionInstance?.jugador?.id)],3)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (suspensionInstance?.fechaSancion)) {
printHtmlPart(18)
invokeTag('message','g',39,['code':("suspension.fechaSancion.label"),'default':("Fecha Sancion")],-1)
printHtmlPart(19)
invokeTag('formatDate','g',41,['format':("dd/MM/yyyy"),'date':(suspensionInstance?.fechaSancion)],-1)
printHtmlPart(16)
}
printHtmlPart(20)
if(true && (suspensionInstance?.cantFechas)) {
printHtmlPart(21)
invokeTag('message','g',48,['code':("suspension.cantFechas.label"),'default':("Cant Fechas")],-1)
printHtmlPart(22)
invokeTag('fieldValue','g',50,['bean':(suspensionInstance),'field':("cantFechas")],-1)
printHtmlPart(16)
}
printHtmlPart(23)
if(true && (suspensionInstance?.descripcion)) {
printHtmlPart(24)
invokeTag('message','g',57,['code':("suspension.descripcion.label"),'default':("Descripcion")],-1)
printHtmlPart(25)
invokeTag('fieldValue','g',59,['bean':(suspensionInstance),'field':("descripcion")],-1)
printHtmlPart(16)
}
printHtmlPart(20)
if(true && (suspensionInstance?.equipo)) {
printHtmlPart(26)
invokeTag('message','g',66,['code':("suspension.equipo.label"),'default':("Equipo")],-1)
printHtmlPart(27)
createTagBody(3, {->
expressionOut.print(suspensionInstance?.equipo?.encodeAsHTML())
})
invokeTag('link','g',68,['controller':("equipo"),'action':("show"),'id':(suspensionInstance?.equipo?.id)],3)
printHtmlPart(16)
}
printHtmlPart(23)
if(true && (suspensionInstance?.partido)) {
printHtmlPart(28)
invokeTag('message','g',75,['code':("suspension.partido.label"),'default':("Partido")],-1)
printHtmlPart(29)
createTagBody(3, {->
expressionOut.print(suspensionInstance?.partido?.encodeAsHTML())
})
invokeTag('link','g',77,['controller':("partido"),'action':("show"),'id':(suspensionInstance?.partido?.id)],3)
printHtmlPart(16)
}
printHtmlPart(30)
if(true && (suspensionInstance?.partido?.fecha)) {
printHtmlPart(31)
invokeTag('message','g',84,['code':("suspension.fecha.label"),'default':("Fecha")],-1)
printHtmlPart(32)
expressionOut.print(suspensionInstance.partido.fecha)
printHtmlPart(33)
}
printHtmlPart(30)
if(true && (suspensionInstance?.esIndefinido)) {
printHtmlPart(34)
invokeTag('message','g',93,['code':("suspension.esIndefinido.label"),'default':("Es Indefinido")],-1)
printHtmlPart(35)
invokeTag('formatBoolean','g',95,['boolean':(suspensionInstance?.esIndefinido)],-1)
printHtmlPart(33)
}
printHtmlPart(30)
if(true && (suspensionInstance?.torneo)) {
printHtmlPart(36)
invokeTag('message','g',102,['code':("suspension.torneo.label"),'default':("Torneo")],-1)
printHtmlPart(37)
createTagBody(3, {->
expressionOut.print(suspensionInstance?.torneo?.encodeAsHTML())
})
invokeTag('link','g',104,['controller':("torneo"),'action':("show"),'id':(suspensionInstance?.torneo?.id)],3)
printHtmlPart(16)
}
printHtmlPart(20)
if(true && (suspensionInstance?.estaActiva)) {
printHtmlPart(38)
invokeTag('message','g',111,['code':("suspension.estaActiva.label"),'default':("Esta Activa")],-1)
printHtmlPart(39)
invokeTag('formatBoolean','g',113,['boolean':(suspensionInstance?.estaActiva)],-1)
printHtmlPart(33)
}
printHtmlPart(20)
if(true && (suspensionInstance?.esProvisoria)) {
printHtmlPart(40)
invokeTag('message','g',120,['code':("suspension.esProvisoria.label"),'default':("Provisoria")],-1)
printHtmlPart(41)
invokeTag('formatBoolean','g',122,['boolean':(suspensionInstance?.esProvisoria)],-1)
printHtmlPart(33)
}
printHtmlPart(42)
createTagBody(2, {->
printHtmlPart(43)
createTagBody(3, {->
invokeTag('message','g',129,['code':("default.button.edit.label"),'default':("Edit")],-1)
})
invokeTag('link','g',129,['class':("edit"),'action':("edit"),'resource':(suspensionInstance)],3)
printHtmlPart(44)
invokeTag('actionSubmit','g',130,['class':("delete"),'action':("delete"),'value':(message(code: 'default.button.delete.label', default: 'Delete')),'onclick':("return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');")],-1)
printHtmlPart(45)
})
invokeTag('form','g',132,['url':([resource:suspensionInstance, action:'delete']),'method':("DELETE")],2)
printHtmlPart(46)
})
invokeTag('captureBody','sitemesh',134,[:],1)
printHtmlPart(47)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1454612589317L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
