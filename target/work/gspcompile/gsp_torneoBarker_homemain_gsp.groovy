import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_torneoBarker_homemain_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/home/main.gsp" }
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
createTagBody(3, {->
printHtmlPart(2)
expressionOut.print(torneoInstance)
})
invokeTag('captureTitle','sitemesh',5,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',5,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',6,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
expressionOut.print(createLink(uri: '/'))
printHtmlPart(5)
invokeTag('message','g',10,['code':("default.home.label")],-1)
printHtmlPart(6)
createClosureForHtmlPart(7, 2)
invokeTag('link','g',11,['class':("fixture"),'controller':("Torneo"),'id':(torneoInstance?.id),'action':("show")],2)
printHtmlPart(8)
createClosureForHtmlPart(9, 2)
invokeTag('link','g',12,['class':("posiciones"),'controller':("estadisticaEquipo"),'id':(torneoInstance?.id),'action':("showPosiciones")],2)
printHtmlPart(8)
createClosureForHtmlPart(10, 2)
invokeTag('link','g',13,['class':("goleadores"),'controller':("estadisticaJugador"),'id':(torneoInstance?.id),'action':("showGoleadores")],2)
printHtmlPart(8)
createClosureForHtmlPart(11, 2)
invokeTag('link','g',14,['class':("fairPlay"),'controller':("estadisticaFairPlay"),'id':(torneoInstance?.id),'action':("showFairPlay")],2)
printHtmlPart(12)
expressionOut.print(torneoInstance)
createTagBody(2, {->
printHtmlPart(13)
expressionOut.print(createLinkTo(dir: 'images', file: 'excel-xls-icon.png'))
printHtmlPart(14)
})
invokeTag('link','g',18,['style':("position:absolute; margin-left: 2em;"),'action':("descargarPlanillaPosiciones"),'id':(torneoInstance?.id),'align':("center")],2)
printHtmlPart(15)
if(true && (flash.message)) {
printHtmlPart(16)
expressionOut.print(flash.message)
printHtmlPart(17)
}
printHtmlPart(18)
invokeTag('sortableColumn','g',25,['property':("posicion"),'title':("Posicion")],-1)
printHtmlPart(19)
invokeTag('sortableColumn','g',27,['property':("equipo"),'title':("Equipo")],-1)
printHtmlPart(20)
invokeTag('sortableColumn','g',29,['property':("partidosJugados"),'title':("PJ")],-1)
printHtmlPart(21)
invokeTag('sortableColumn','g',31,['property':("partidosGanados"),'title':("PG")],-1)
printHtmlPart(20)
invokeTag('sortableColumn','g',33,['property':("partidosEmpatados"),'title':("PE")],-1)
printHtmlPart(20)
invokeTag('sortableColumn','g',35,['property':("partidosPerdidos"),'title':("PP")],-1)
printHtmlPart(20)
invokeTag('sortableColumn','g',37,['property':("golesFavor"),'title':("GF")],-1)
printHtmlPart(20)
invokeTag('sortableColumn','g',39,['property':("golesContra"),'title':("GC")],-1)
printHtmlPart(20)
invokeTag('sortableColumn','g',41,['property':("diferenciaGol"),'title':("DF")],-1)
printHtmlPart(20)
invokeTag('sortableColumn','g',43,['property':("puntos"),'title':("Ptos")],-1)
printHtmlPart(22)
loop:{
int i = 0
for( estadisticaEquipo in (posicionesList) ) {
printHtmlPart(23)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(24)
expressionOut.print(fieldValue(bean: estadisticaEquipo, field: "posicion"))
printHtmlPart(25)
createTagBody(3, {->
expressionOut.print(fieldValue(bean: estadisticaEquipo.equipo, field: "nombre"))
})
invokeTag('link','g',53,['controller':("equipo"),'action':("show"),'id':(estadisticaEquipo.equipo.id)],3)
printHtmlPart(26)
expressionOut.print(fieldValue(bean: estadisticaEquipo, field: "partidosJugados"))
printHtmlPart(26)
expressionOut.print(fieldValue(bean: estadisticaEquipo, field: "partidosGanados"))
printHtmlPart(26)
expressionOut.print(fieldValue(bean: estadisticaEquipo, field: "partidosEmpatados"))
printHtmlPart(26)
expressionOut.print(fieldValue(bean: estadisticaEquipo, field: "partidosPerdidos"))
printHtmlPart(26)
expressionOut.print(fieldValue(bean: estadisticaEquipo, field: "golesFavor"))
printHtmlPart(26)
expressionOut.print(fieldValue(bean: estadisticaEquipo, field: "golesContra"))
printHtmlPart(26)
expressionOut.print(fieldValue(bean: estadisticaEquipo, field: "diferenciaGol"))
printHtmlPart(26)
expressionOut.print(fieldValue(bean: estadisticaEquipo, field: "puntos"))
printHtmlPart(27)
i++
}
}
printHtmlPart(28)
invokeTag('paginate','g',76,['total':(equipoInstanceCount ?: 0)],-1)
printHtmlPart(29)
})
invokeTag('captureBody','sitemesh',79,[:],1)
printHtmlPart(30)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1450448033639L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
