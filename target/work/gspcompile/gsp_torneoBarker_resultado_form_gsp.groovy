import ar.com.torneobarker.Resultado
import ar.com.torneobarker.Jugador
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_torneoBarker_resultado_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/resultado/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
expressionOut.print(partido.local.nombre)
printHtmlPart(2)
expressionOut.print(partido.id)
printHtmlPart(3)
invokeTag('message','g',15,['code':("jugador.apellido.label"),'default':("Jugador")],-1)
printHtmlPart(4)
invokeTag('message','g',16,['code':("goles.favor.label"),'default':("GF")],-1)
printHtmlPart(4)
invokeTag('message','g',17,['code':("goles.contra.label"),'default':("GC")],-1)
printHtmlPart(5)
invokeTag('message','g',18,['code':("tarjeta.amarilla.label"),'default':("TA")],-1)
printHtmlPart(4)
invokeTag('message','g',19,['code':("tarjeta.roja.label"),'default':("TR")],-1)
printHtmlPart(4)
invokeTag('message','g',20,['code':("cantidad.suspencion.label"),'default':("Cant Susp.")],-1)
printHtmlPart(6)
invokeTag('message','g',21,['code':("resultado.comentario.label"),'default':("Comentario")],-1)
printHtmlPart(7)
loop:{
int i = 0
for( jugadorInstance in (jugadoresLocal) ) {
printHtmlPart(8)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(9)
createTagBody(2, {->
expressionOut.print(fieldValue(bean: jugadorInstance, field: "jugador.apellido"))
printHtmlPart(10)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "jugador.nombre"))
})
invokeTag('link','g',27,['controller':("jugador"),'action':("show"),'id':(jugadorInstance.jugador.id)],2)
printHtmlPart(11)
expressionOut.print(jugadorInstance.jugador.id)
printHtmlPart(12)
expressionOut.print(jugadorInstance.jugador.id)
printHtmlPart(13)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "golesFavor"))
printHtmlPart(14)
expressionOut.print(jugadorInstance.jugador.id)
printHtmlPart(15)
expressionOut.print(jugadorInstance.jugador.id)
printHtmlPart(13)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "golesContra"))
printHtmlPart(16)
expressionOut.print(jugadorInstance.jugador.id)
printHtmlPart(17)
expressionOut.print(jugadorInstance.jugador.id)
printHtmlPart(18)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "tarjetasAmarillas"))
printHtmlPart(19)
expressionOut.print(jugadorInstance.jugador.id)
printHtmlPart(20)
expressionOut.print(jugadorInstance.jugador.id)
printHtmlPart(18)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "tarjetasRojas"))
printHtmlPart(21)
expressionOut.print(jugadorInstance.jugador.id)
printHtmlPart(22)
expressionOut.print(jugadorInstance.jugador.id)
printHtmlPart(13)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "cantSusp"))
printHtmlPart(23)
expressionOut.print(jugadorInstance.jugador.id)
printHtmlPart(24)
expressionOut.print(jugadorInstance.jugador.id)
printHtmlPart(25)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "comentario"))
printHtmlPart(26)
i++
}
}
printHtmlPart(27)
expressionOut.print(partido.visitante.nombre)
printHtmlPart(28)
invokeTag('message','g',49,['code':("jugador.apellido.label"),'default':("Jugador")],-1)
printHtmlPart(4)
invokeTag('message','g',50,['code':("goles.favor.label"),'default':("GF")],-1)
printHtmlPart(4)
invokeTag('message','g',51,['code':("goles.contra.label"),'default':("GC")],-1)
printHtmlPart(5)
invokeTag('message','g',52,['code':("tarjeta.amarilla.label"),'default':("TA")],-1)
printHtmlPart(4)
invokeTag('message','g',53,['code':("tarjeta.roja.label"),'default':("TR")],-1)
printHtmlPart(4)
invokeTag('message','g',54,['code':("cantidad.suspencion.label"),'default':("Cant Susp.")],-1)
printHtmlPart(6)
invokeTag('message','g',55,['code':("resultado.comentario.label"),'default':("Comentario")],-1)
printHtmlPart(7)
loop:{
int i = 0
for( jugadorInstance in (jugadoresVisitante) ) {
printHtmlPart(8)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(9)
createTagBody(2, {->
expressionOut.print(fieldValue(bean: jugadorInstance, field: "jugador.apellido"))
printHtmlPart(10)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "jugador.nombre"))
})
invokeTag('link','g',61,['controller':("jugador"),'action':("show"),'id':(jugadorInstance.jugador.id)],2)
printHtmlPart(11)
expressionOut.print(jugadorInstance.jugador.id)
printHtmlPart(12)
expressionOut.print(jugadorInstance.jugador.id)
printHtmlPart(13)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "golesFavor"))
printHtmlPart(14)
expressionOut.print(jugadorInstance.jugador.id)
printHtmlPart(15)
expressionOut.print(jugadorInstance.jugador.id)
printHtmlPart(13)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "golesContra"))
printHtmlPart(16)
expressionOut.print(jugadorInstance.jugador.id)
printHtmlPart(17)
expressionOut.print(jugadorInstance.jugador.id)
printHtmlPart(18)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "tarjetasAmarillas"))
printHtmlPart(19)
expressionOut.print(jugadorInstance.jugador.id)
printHtmlPart(20)
expressionOut.print(jugadorInstance.jugador.id)
printHtmlPart(18)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "tarjetasRojas"))
printHtmlPart(21)
expressionOut.print(jugadorInstance.jugador.id)
printHtmlPart(22)
expressionOut.print(jugadorInstance.jugador.id)
printHtmlPart(13)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "cantSusp"))
printHtmlPart(23)
expressionOut.print(jugadorInstance.jugador.id)
printHtmlPart(24)
expressionOut.print(jugadorInstance.jugador.id)
printHtmlPart(25)
expressionOut.print(fieldValue(bean: jugadorInstance, field: "comentario"))
printHtmlPart(26)
i++
}
}
printHtmlPart(29)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1450720649392L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
