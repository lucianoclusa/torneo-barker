import ar.com.torneobarker.Equipo
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_torneoBarker_equiposhow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/equipo/show.gsp" }
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
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'equipo.label', default: 'Equipo'))],-1)
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
printHtmlPart(8)
createClosureForHtmlPart(9, 2)
invokeTag('link','g',17,['class':("create"),'action':("showLoadFromExcel")],2)
printHtmlPart(10)
invokeTag('message','g',21,['code':("default.show.label"),'args':([entityName])],-1)
printHtmlPart(11)
if(true && (flash.message)) {
printHtmlPart(12)
expressionOut.print(flash.message)
printHtmlPart(13)
}
printHtmlPart(14)
if(true && (equipoInstance?.nombre)) {
printHtmlPart(15)
invokeTag('message','g',29,['code':("equipo.nombre.label"),'default':("Nombre")],-1)
printHtmlPart(16)
invokeTag('fieldValue','g',31,['bean':(equipoInstance),'field':("nombre")],-1)
printHtmlPart(17)
}
printHtmlPart(18)
if(true && (equipoInstance?.colegio)) {
printHtmlPart(19)
invokeTag('message','g',38,['code':("equipo.colegio.label"),'default':("Colegio")],-1)
printHtmlPart(20)
createTagBody(3, {->
expressionOut.print(equipoInstance?.colegio?.encodeAsHTML())
})
invokeTag('link','g',40,['controller':("colegio"),'action':("show"),'id':(equipoInstance?.colegio?.id)],3)
printHtmlPart(17)
}
printHtmlPart(18)
if(true && (equipoInstance?.categoria)) {
printHtmlPart(21)
invokeTag('message','g',47,['code':("equipo.categoria.label"),'default':("Categoria")],-1)
printHtmlPart(22)
createTagBody(3, {->
expressionOut.print(equipoInstance?.categoria?.encodeAsHTML())
})
invokeTag('link','g',49,['controller':("categoria"),'action':("show"),'id':(equipoInstance?.categoria?.id)],3)
printHtmlPart(17)
}
printHtmlPart(18)
if(true && (equipoInstance?.descripcion)) {
printHtmlPart(23)
invokeTag('message','g',56,['code':("equipo.descripcion.label"),'default':("Descripcion")],-1)
printHtmlPart(24)
invokeTag('fieldValue','g',58,['bean':(equipoInstance),'field':("descripcion")],-1)
printHtmlPart(17)
}
printHtmlPart(18)
if(true && (equipoInstance?.fechaCreacion)) {
printHtmlPart(25)
invokeTag('message','g',65,['code':("equipo.fechaCreacion.label"),'default':("Fecha Creacion")],-1)
printHtmlPart(26)
invokeTag('formatDate','g',67,['date':(equipoInstance?.fechaCreacion)],-1)
printHtmlPart(17)
}
printHtmlPart(18)
if(true && (equipoInstance?.jugadores)) {
printHtmlPart(27)
invokeTag('message','g',74,['code':("equipo.jugadores.label"),'default':("Jugadores")],-1)
printHtmlPart(28)
for( j in (equipoInstance.jugadores) ) {
printHtmlPart(29)
createTagBody(4, {->
expressionOut.print(j?.encodeAsHTML())
})
invokeTag('link','g',77,['controller':("jugador"),'action':("show"),'id':(j.id)],4)
printHtmlPart(30)
}
printHtmlPart(31)
}
printHtmlPart(32)
createTagBody(2, {->
printHtmlPart(33)
createTagBody(3, {->
invokeTag('message','g',86,['code':("default.button.edit.label"),'default':("Edit")],-1)
})
invokeTag('link','g',86,['class':("edit"),'action':("edit"),'resource':(equipoInstance)],3)
printHtmlPart(34)
invokeTag('actionSubmit','g',87,['class':("delete"),'action':("delete"),'value':(message(code: 'default.button.delete.label', default: 'Delete')),'onclick':("return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');")],-1)
printHtmlPart(35)
})
invokeTag('form','g',89,['url':([resource:equipoInstance, action:'delete']),'method':("DELETE")],2)
printHtmlPart(36)
})
invokeTag('captureBody','sitemesh',91,[:],1)
printHtmlPart(37)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1444048531043L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
