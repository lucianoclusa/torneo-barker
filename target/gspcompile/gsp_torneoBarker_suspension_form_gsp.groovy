import ar.com.torneobarker.Suspension
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_torneoBarker_suspension_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/suspension/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: suspensionInstance, field: 'fechaSancion', 'error'))
printHtmlPart(1)
invokeTag('message','g',5,['code':("torneo.fechaSancion.label"),'default':("Fecha Sanción")],-1)
printHtmlPart(2)
invokeTag('datePicker','g',8,['name':("fechaSancion"),'precision':("day"),'value':(suspensionInstance?.fechaSancion)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: suspensionInstance, field: 'jugador', 'error'))
printHtmlPart(4)
invokeTag('message','g',13,['code':("suspension.jugador.label"),'default':("Jugador")],-1)
printHtmlPart(2)
invokeTag('select','g',16,['id':("jugador"),'name':("jugador.id"),'from':(ar.com.torneobarker.Jugador.list()),'optionKey':("id"),'required':(""),'value':(suspensionInstance?.jugador?.id),'class':("many-to-one"),'onchange':("jugadorChanged(this.value)")],-1)
printHtmlPart(5)
expressionOut.print(createLink(controller: 'suspension', action: 'jugadorChanged'))
printHtmlPart(6)
expressionOut.print(hasErrors(bean: suspensionInstance, field: 'equipo', 'error'))
printHtmlPart(7)
invokeTag('message','g',33,['code':("suspension.equipo.label"),'default':("Equipo")],-1)
printHtmlPart(8)
invokeTag('select','g',36,['id':("equipo"),'name':("equipo.id"),'from':(ar.com.torneobarker.Equipo.findAll("from Equipo e where ? in elements(e.jugadores)", [ar.com.torneobarker.Jugador.list().get(0)])),'optionKey':("id"),'value':(suspensionInstance?.equipo?.id),'class':("many-to-one"),'onchange':("equipoChanged(this.value)"),'noSelection':(['':'-Sin equipo-'])],-1)
printHtmlPart(9)
expressionOut.print(createLink(controller: 'suspension', action: 'equipoChanged'))
printHtmlPart(10)
expressionOut.print(hasErrors(bean: suspensionInstance, field: 'torneo', 'error'))
printHtmlPart(11)
invokeTag('message','g',52,['code':("suspension.torneo.label"),'default':("Torneo")],-1)
printHtmlPart(12)
invokeTag('select','g',55,['id':("torneo"),'name':("torneo.id"),'from':(ar.com.torneobarker.Torneo.list()),'optionKey':("id"),'value':(suspensionInstance?.torneo?.id),'class':("many-to-one"),'onchange':("torneoChanged(this.value)"),'noSelection':(['':'-Sin equipo-'])],-1)
printHtmlPart(13)
expressionOut.print(createLink(controller: 'suspension', action: 'torneoChanged'))
printHtmlPart(14)
expressionOut.print(hasErrors(bean: suspensionInstance, field: 'fecha', 'error'))
printHtmlPart(11)
invokeTag('message','g',71,['code':("suspension.fecha.label"),'default':("Fecha")],-1)
printHtmlPart(15)
invokeTag('select','g',74,['id':("fecha"),'name':("fecha.id"),'from':(ar.com.torneobarker.Fecha.findAllByTorneo(ar.com.torneobarker.Torneo.list().get(0))),'optionKey':("id"),'value':(suspensionInstance?.fecha),'onchange':("categoryChanged(this.value)"),'class':("many-to-one"),'noSelection':(['':'-Sin equipo-'])],-1)
printHtmlPart(16)
expressionOut.print(hasErrors(bean: suspensionInstance, field: 'cantFechas', 'error'))
printHtmlPart(17)
invokeTag('message','g',79,['code':("suspension.cantFechas.label"),'default':("Cant Fechas")],-1)
printHtmlPart(18)
invokeTag('field','g',82,['name':("cantFechas"),'type':("number"),'value':(suspensionInstance.cantFechas)],-1)
printHtmlPart(19)
expressionOut.print(hasErrors(bean: suspensionInstance, field: 'descripcion', 'error'))
printHtmlPart(20)
invokeTag('message','g',88,['code':("suspension.descripcion.label"),'default':("Descripcion")],-1)
printHtmlPart(18)
invokeTag('textArea','g',91,['name':("descripcion"),'value':(suspensionInstance?.descripcion)],-1)
printHtmlPart(19)
expressionOut.print(hasErrors(bean: suspensionInstance, field: 'esIndefinido', 'error'))
printHtmlPart(21)
invokeTag('message','g',97,['code':("suspension.esPermanente.label"),'default':("Permanente")],-1)
printHtmlPart(18)
invokeTag('checkBox','g',100,['name':("esIndefinido"),'value':(suspensionInstance?.esIndefinido)],-1)
printHtmlPart(19)
expressionOut.print(hasErrors(bean: suspensionInstance, field: 'esProvisoria', 'error'))
printHtmlPart(22)
invokeTag('message','g',106,['code':("suspension.esProvisoria.label"),'default':("Provisoria")],-1)
printHtmlPart(18)
invokeTag('checkBox','g',109,['name':("esProvisoria"),'value':(suspensionInstance?.esProvisoria)],-1)
printHtmlPart(19)
expressionOut.print(hasErrors(bean: suspensionInstance, field: 'estaActiva', 'error'))
printHtmlPart(23)
invokeTag('message','g',115,['code':("suspension.estaActiva.label"),'default':("Esta Activa")],-1)
printHtmlPart(18)
invokeTag('checkBox','g',118,['name':("estaActiva"),'value':(suspensionInstance?.estaActiva)],-1)
printHtmlPart(24)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1455117458610L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
