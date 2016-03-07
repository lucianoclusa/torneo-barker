import ar.com.torneobarker.Torneo
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_torneoBarker_torneoindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/torneo/index.gsp" }
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
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'torneo.label', default: 'Torneo'))],-1)
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
invokeTag('message','g',19,['code':("default.list.label"),'args':([entityName])],-1)
printHtmlPart(9)
if(true && (flash.message)) {
printHtmlPart(10)
expressionOut.print(flash.message)
printHtmlPart(11)
}
printHtmlPart(12)
invokeTag('sortableColumn','g',29,['property':("anio"),'title':(message(code: 'torneo.anio.label', default: 'Año'))],-1)
printHtmlPart(13)
invokeTag('sortableColumn','g',31,['property':("temporada"),'title':(message(code: 'torneo.temporada.label', default: 'Temporada'))],-1)
printHtmlPart(14)
invokeTag('sortableColumn','g',33,['property':("categoria"),'title':(message(code: 'torneo.categoria.label', default: 'Categoria'))],-1)
printHtmlPart(15)
invokeTag('sortableColumn','g',35,['property':("fechaInicio"),'title':(message(code: 'torneo.fechaInicio.label', default: 'Fecha Inicio'))],-1)
printHtmlPart(15)
invokeTag('sortableColumn','g',37,['property':("fechaFin"),'title':(message(code: 'torneo.fechaFin.label', default: 'Fecha Fin'))],-1)
printHtmlPart(16)
loop:{
int i = 0
for( torneoInstance in (torneoInstanceList) ) {
printHtmlPart(17)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(18)
createTagBody(3, {->
printHtmlPart(19)
expressionOut.print(createLinkTo(dir: 'images', file: 'lupa.png'))
printHtmlPart(20)
})
invokeTag('link','g',45,['action':("show"),'id':(torneoInstance.id)],3)
printHtmlPart(21)
expressionOut.print(fieldValue(bean: torneoInstance, field: "anio"))
printHtmlPart(21)
expressionOut.print(fieldValue(bean: torneoInstance, field: "temporada.descripcion"))
printHtmlPart(21)
expressionOut.print(fieldValue(bean: torneoInstance, field: "categoria.descripcion"))
printHtmlPart(22)
invokeTag('formatDate','g',53,['format':("dd/MM/yyyy"),'date':(torneoInstance.fechaInicio)],-1)
printHtmlPart(23)
invokeTag('formatDate','g',55,['format':("dd/MM/yyyy"),'date':(torneoInstance.fechaFin)],-1)
printHtmlPart(24)
i++
}
}
printHtmlPart(25)
invokeTag('paginate','g',62,['total':(torneoInstanceCount ?: 0)],-1)
printHtmlPart(26)
})
invokeTag('captureBody','sitemesh',65,[:],1)
printHtmlPart(27)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1454617456545L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
