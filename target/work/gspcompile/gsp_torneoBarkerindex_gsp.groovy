import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_torneoBarkerindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("main")],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',5,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',5,[:],2)
printHtmlPart(1)
invokeTag('set','g',6,['var':("entityName"),'value':("Posiciones")],-1)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',7,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',11,['code':("default.list.label"),'args':([entityName])],-1)
printHtmlPart(5)
if(true && (flash.message)) {
printHtmlPart(6)
expressionOut.print(flash.message)
printHtmlPart(7)
}
printHtmlPart(8)
invokeTag('sortableColumn','g',18,['property':("posicion"),'title':("Posicion")],-1)
printHtmlPart(9)
invokeTag('sortableColumn','g',20,['property':("equipo"),'title':("Equipo")],-1)
printHtmlPart(9)
invokeTag('sortableColumn','g',22,['property':("puntos"),'title':("Ptos")],-1)
printHtmlPart(10)
invokeTag('sortableColumn','g',24,['property':("partidosJugados"),'title':("PJ")],-1)
printHtmlPart(11)
invokeTag('sortableColumn','g',26,['property':("partidosGanados"),'title':("PG")],-1)
printHtmlPart(12)
invokeTag('sortableColumn','g',28,['property':("partidosPerdidos"),'title':("PP")],-1)
printHtmlPart(12)
invokeTag('sortableColumn','g',30,['property':("partidosEmpatados"),'title':("PE")],-1)
printHtmlPart(12)
invokeTag('sortableColumn','g',32,['property':("golesFavor"),'title':("GF")],-1)
printHtmlPart(12)
invokeTag('sortableColumn','g',34,['property':("golesContra"),'title':("GC")],-1)
printHtmlPart(13)
loop:{
int i = 0
for( estadisticaEquipo in (posicionesList) ) {
printHtmlPart(14)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(15)
expressionOut.print(fieldValue(bean: estadisticaEquipo, field: "puntos"))
printHtmlPart(16)
createTagBody(3, {->
expressionOut.print(fieldValue(bean: estadisticaEquipo.equipo, field: "nombre"))
})
invokeTag('link','g',44,['controller':("equipo"),'action':("show"),'id':(estadisticaEquipo.equipo.id)],3)
printHtmlPart(16)
expressionOut.print(fieldValue(bean: estadisticaEquipo, field: "puntos"))
printHtmlPart(16)
expressionOut.print(fieldValue(bean: estadisticaEquipo, field: "partidosGanados"))
printHtmlPart(16)
expressionOut.print(fieldValue(bean: estadisticaEquipo, field: "partidosPerdidos"))
printHtmlPart(16)
expressionOut.print(fieldValue(bean: estadisticaEquipo, field: "partidosEmpatados"))
printHtmlPart(16)
expressionOut.print(fieldValue(bean: estadisticaEquipo, field: "golesFavor"))
printHtmlPart(16)
expressionOut.print(fieldValue(bean: estadisticaEquipo, field: "golesContra"))
printHtmlPart(17)
i++
}
}
printHtmlPart(18)
invokeTag('paginate','g',63,['total':(equipoInstanceCount ?: 0)],-1)
printHtmlPart(19)
})
invokeTag('captureBody','sitemesh',66,[:],1)
printHtmlPart(20)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442253123802L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
