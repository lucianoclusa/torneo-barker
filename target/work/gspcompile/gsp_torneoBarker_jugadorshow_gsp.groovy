import ar.com.torneobarker.Jugador
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_torneoBarker_jugadorshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/jugador/show.gsp" }
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
invokeTag('message','g',8,['code':("default.show.label"),'args':([entityName])],-1)
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
invokeTag('message','g',15,['code':("default.list.label"),'args':([entityName])],-1)
})
invokeTag('link','g',15,['class':("list"),'action':("index")],2)
printHtmlPart(8)
createTagBody(2, {->
invokeTag('message','g',16,['code':("default.new.label"),'args':([entityName])],-1)
})
invokeTag('link','g',16,['class':("create"),'action':("create")],2)
printHtmlPart(9)
invokeTag('message','g',20,['code':("default.show.label"),'args':([entityName])],-1)
printHtmlPart(10)
if(true && (flash.message)) {
printHtmlPart(11)
expressionOut.print(flash.message)
printHtmlPart(12)
}
printHtmlPart(13)
if(true && (jugadorInstance?.dni)) {
printHtmlPart(14)
invokeTag('message','g',28,['code':("jugador.dni.label"),'default':("Dni")],-1)
printHtmlPart(15)
invokeTag('fieldValue','g',30,['bean':(jugadorInstance),'field':("dni")],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (jugadorInstance?.nombre)) {
printHtmlPart(18)
invokeTag('message','g',37,['code':("jugador.nombre.label"),'default':("Nombre")],-1)
printHtmlPart(19)
invokeTag('fieldValue','g',39,['bean':(jugadorInstance),'field':("nombre")],-1)
printHtmlPart(16)
}
printHtmlPart(20)
if(true && (jugadorInstance?.apellido)) {
printHtmlPart(21)
invokeTag('message','g',46,['code':("jugador.apellido.label"),'default':("Apellido")],-1)
printHtmlPart(22)
invokeTag('fieldValue','g',48,['bean':(jugadorInstance),'field':("apellido")],-1)
printHtmlPart(16)
}
printHtmlPart(20)
if(true && (jugadorInstance?.fechaNacimiento)) {
printHtmlPart(23)
invokeTag('message','g',55,['code':("jugador.fechaNacimiento.label"),'default':("Fecha Nacimiento")],-1)
printHtmlPart(24)
invokeTag('formatDate','g',56,['format':("dd/MM/yyyy"),'date':(jugadorInstance?.fechaNacimiento)],-1)
printHtmlPart(25)
}
printHtmlPart(26)
invokeTag('message','g',61,['code':("jugador.equipos.label"),'default':("Equipos")],-1)
printHtmlPart(27)
loop:{
int i = 0
for( equipoInstance in (ar.com.torneobarker.Equipo.findAll("from Equipo e where ? in elements(e.jugadores)", [jugadorInstance])) ) {
printHtmlPart(28)
if(true && (i>0)) {
printHtmlPart(29)
}
printHtmlPart(30)
createTagBody(3, {->
expressionOut.print(equipoInstance)
})
invokeTag('link','g',65,['controller':("equipo"),'action':("show"),'id':(equipoInstance.id)],3)
printHtmlPart(31)
i++
}
}
printHtmlPart(32)
createTagBody(2, {->
printHtmlPart(33)
createTagBody(3, {->
invokeTag('message','g',74,['code':("default.button.edit.label"),'default':("Edit")],-1)
})
invokeTag('link','g',74,['class':("edit"),'action':("edit"),'resource':(jugadorInstance)],3)
printHtmlPart(34)
invokeTag('actionSubmit','g',75,['class':("delete"),'action':("delete"),'value':(message(code: 'default.button.delete.label', default: 'Delete')),'onclick':("return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');")],-1)
printHtmlPart(35)
})
invokeTag('form','g',77,['url':([resource:jugadorInstance, action:'delete']),'method':("DELETE")],2)
printHtmlPart(36)
})
invokeTag('captureBody','sitemesh',79,[:],1)
printHtmlPart(37)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1445348069184L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
