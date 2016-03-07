import ar.com.torneobarker.Equipo
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_torneoBarker_equipoindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/equipo/index.gsp" }
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
createClosureForHtmlPart(9, 2)
invokeTag('link','g',16,['class':("create"),'action':("showLoadFromExcel")],2)
printHtmlPart(10)
invokeTag('message','g',20,['code':("default.list.label"),'args':([entityName])],-1)
printHtmlPart(11)
if(true && (flash.message)) {
printHtmlPart(12)
expressionOut.print(flash.message)
printHtmlPart(13)
}
printHtmlPart(14)
invokeTag('sortableColumn','g',29,['property':("nombre"),'title':(message(code: 'equipo.nombre.label', default: 'Nombre'))],-1)
printHtmlPart(15)
invokeTag('message','g',31,['code':("equipo.colegio.label"),'default':("Colegio")],-1)
printHtmlPart(16)
invokeTag('message','g',33,['code':("equipo.categoria.label"),'default':("Categoria")],-1)
printHtmlPart(17)
invokeTag('sortableColumn','g',35,['property':("descripcion"),'title':(message(code: 'equipo.descripcion.label', default: 'Descripcion'))],-1)
printHtmlPart(18)
invokeTag('sortableColumn','g',37,['property':("fechaCreacion"),'title':(message(code: 'equipo.fechaCreacion.label', default: 'Fecha Creacion'))],-1)
printHtmlPart(19)
loop:{
int i = 0
for( equipoInstance in (equipoInstanceList) ) {
printHtmlPart(20)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(21)
createTagBody(3, {->
printHtmlPart(22)
expressionOut.print(createLinkTo(dir: 'images', file: 'lupa.png'))
printHtmlPart(23)
})
invokeTag('link','g',45,['action':("show"),'id':(equipoInstance.id)],3)
printHtmlPart(24)
expressionOut.print(fieldValue(bean: equipoInstance, field: "nombre"))
printHtmlPart(24)
expressionOut.print(fieldValue(bean: equipoInstance, field: "colegio"))
printHtmlPart(24)
expressionOut.print(fieldValue(bean: equipoInstance, field: "categoria"))
printHtmlPart(24)
expressionOut.print(fieldValue(bean: equipoInstance, field: "descripcion"))
printHtmlPart(24)
invokeTag('formatDate','g',55,['format':("dd/MM/yyyy"),'date':(equipoInstance.fechaCreacion)],-1)
printHtmlPart(25)
i++
}
}
printHtmlPart(26)
invokeTag('paginate','g',62,['total':(equipoInstanceCount ?: 0)],-1)
printHtmlPart(27)
})
invokeTag('captureBody','sitemesh',65,[:],1)
printHtmlPart(28)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1454617386687L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
