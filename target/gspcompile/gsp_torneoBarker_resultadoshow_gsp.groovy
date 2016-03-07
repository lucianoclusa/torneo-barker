import ar.com.torneobarker.Resultado
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_torneoBarker_resultadoshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/resultado/show.gsp" }
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
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'resultado.label', default: 'Resultado'))],-1)
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
createClosureForHtmlPart(8, 2)
invokeTag('link','g',15,['class':("fixture"),'controller':("Torneo"),'id':(torneoInstance?.id),'action':("show")],2)
printHtmlPart(9)
createClosureForHtmlPart(10, 2)
invokeTag('link','g',16,['class':("posiciones"),'controller':("estadisticaEquipo"),'id':(torneoInstance?.id),'action':("showPosiciones")],2)
printHtmlPart(9)
createClosureForHtmlPart(11, 2)
invokeTag('link','g',17,['class':("goleadores"),'controller':("estadisticaJugador"),'id':(torneoInstance?.id),'action':("showGoleadores")],2)
printHtmlPart(9)
createClosureForHtmlPart(12, 2)
invokeTag('link','g',18,['class':("fairPlay"),'controller':("estadisticaFairPlay"),'id':(torneoInstance?.id),'action':("showFairPlay")],2)
printHtmlPart(13)
invokeTag('message','g',22,['code':("default.show.label"),'args':([entityName])],-1)
printHtmlPart(14)
if(true && (flash.message)) {
printHtmlPart(15)
expressionOut.print(flash.message)
printHtmlPart(16)
}
printHtmlPart(17)
expressionOut.print(local.nombre)
printHtmlPart(18)
invokeTag('sortableColumn','g',33,['property':("jugador"),'title':(message(code: 'jugador.label', default: 'Jugador'))],-1)
printHtmlPart(19)
invokeTag('sortableColumn','g',34,['property':("golesFavor"),'title':(message(code: 'jugador.golesFavor.label', default: 'GF'))],-1)
printHtmlPart(19)
invokeTag('sortableColumn','g',35,['property':("golesContra"),'title':(message(code: 'jugador.golesContra.label', default: 'GC'))],-1)
printHtmlPart(19)
invokeTag('sortableColumn','g',36,['property':("tarjetasAmarillas"),'title':(message(code: 'jugador.tarjetaAmarilla.label', default: 'TA'))],-1)
printHtmlPart(19)
invokeTag('sortableColumn','g',37,['property':("tarjetasRojas"),'title':(message(code: 'jugador.tarjetaRoja.label', default: 'TR'))],-1)
printHtmlPart(19)
invokeTag('sortableColumn','g',38,['property':("cantSuspensiones"),'title':(message(code: 'jugador.tarjetaRoja.label', default: 'Cant. Susp.'))],-1)
printHtmlPart(19)
invokeTag('sortableColumn','g',39,['property':("comentario"),'title':(message(code: 'jugador.comentario.label', default: 'Comentario'))],-1)
printHtmlPart(20)
loop:{
int i = 0
for( jugadorInstance in (jugadoresLocal) ) {
printHtmlPart(21)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(22)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "jugador"))
printHtmlPart(23)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "golesFavor"))
printHtmlPart(23)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "golesContra"))
printHtmlPart(23)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "tarjetasAmarillas"))
printHtmlPart(23)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "tarjetasRojas"))
printHtmlPart(23)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "cantSusp"))
printHtmlPart(23)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "comentario"))
printHtmlPart(24)
i++
}
}
printHtmlPart(25)
expressionOut.print(visitante.nombre)
printHtmlPart(26)
invokeTag('sortableColumn','g',68,['property':("jugador"),'title':(message(code: 'jugador.label', default: 'Jugador'))],-1)
printHtmlPart(19)
invokeTag('sortableColumn','g',69,['property':("golesFavor"),'title':(message(code: 'jugador.golesFavor.label', default: 'GF'))],-1)
printHtmlPart(19)
invokeTag('sortableColumn','g',70,['property':("golesContra"),'title':(message(code: 'jugador.golesContra.label', default: 'GC'))],-1)
printHtmlPart(19)
invokeTag('sortableColumn','g',71,['property':("tarjetasAmarillas"),'title':(message(code: 'jugador.tarjetaAmarilla.label', default: 'TA'))],-1)
printHtmlPart(19)
invokeTag('sortableColumn','g',72,['property':("tarjetasRojas"),'title':(message(code: 'jugador.tarjetaRoja.label', default: 'TR'))],-1)
printHtmlPart(19)
invokeTag('sortableColumn','g',73,['property':("tarjetasRojas"),'title':(message(code: 'jugador.tarjetaRoja.label', default: 'TR'))],-1)
printHtmlPart(19)
invokeTag('sortableColumn','g',74,['property':("cantSuspensiones"),'title':(message(code: 'jugador.tarjetaRoja.label', default: 'Cant. Susp.'))],-1)
printHtmlPart(19)
invokeTag('sortableColumn','g',75,['property':("comentario"),'title':(message(code: 'jugador.comentario.label', default: 'Comentario'))],-1)
printHtmlPart(20)
loop:{
int i = 0
for( jugadorInstance in (jugadoresVisitante) ) {
printHtmlPart(21)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(22)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "jugador"))
printHtmlPart(23)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "golesFavor"))
printHtmlPart(23)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "golesContra"))
printHtmlPart(23)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "tarjetasAmarillas"))
printHtmlPart(23)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "tarjetasRojas"))
printHtmlPart(23)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "cantSusp"))
printHtmlPart(23)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "comentario"))
printHtmlPart(24)
i++
}
}
printHtmlPart(27)
createTagBody(2, {->
printHtmlPart(28)
createTagBody(3, {->
invokeTag('message','g',100,['code':("default.button.edit.label"),'default':("Edit")],-1)
})
invokeTag('link','g',100,['class':("edit"),'action':("edit"),'resource':(resultadoInstance)],3)
printHtmlPart(29)
invokeTag('actionSubmit','g',101,['class':("delete"),'action':("delete"),'value':(message(code: 'default.button.delete.label', default: 'Delete')),'onclick':("return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');")],-1)
printHtmlPart(30)
})
invokeTag('form','g',103,['url':([resource:resultadoInstance, action:'delete']),'method':("DELETE")],2)
printHtmlPart(31)
})
invokeTag('captureBody','sitemesh',105,[:],1)
printHtmlPart(32)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1452181302978L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
