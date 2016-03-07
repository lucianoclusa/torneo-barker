import ar.com.torneobarker.Torneo
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_torneoBarker_torneoedit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/torneo/edit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(1)
invokeTag('set','g',6,['var':("entityName"),'value':(message(code: 'torneo.label', default: 'Torneo'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
expressionOut.print(torneoInstance.temporada.descripcion)
printHtmlPart(2)
expressionOut.print(torneoInstance.anio)
printHtmlPart(2)
expressionOut.print(torneoInstance.categoria.descripcion)
})
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',8,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',10,['code':("default.link.skip.label"),'default':("Skip to content&hellip;")],-1)
printHtmlPart(5)
expressionOut.print(createLink(uri: '/'))
printHtmlPart(6)
invokeTag('message','g',13,['code':("default.home.label")],-1)
printHtmlPart(7)
createTagBody(2, {->
invokeTag('message','g',14,['code':("default.list.label"),'args':([entityName])],-1)
})
invokeTag('link','g',14,['class':("list"),'action':("index")],2)
printHtmlPart(8)
createTagBody(2, {->
invokeTag('message','g',15,['code':("default.new.label"),'args':([entityName])],-1)
})
invokeTag('link','g',15,['class':("create"),'action':("create")],2)
printHtmlPart(9)
expressionOut.print(torneoInstance.temporada.descripcion)
printHtmlPart(2)
expressionOut.print(torneoInstance.anio)
printHtmlPart(2)
expressionOut.print(torneoInstance.categoria.descripcion)
printHtmlPart(10)
if(true && (flash.message)) {
printHtmlPart(11)
expressionOut.print(flash.message)
printHtmlPart(12)
}
printHtmlPart(13)
createTagBody(2, {->
printHtmlPart(14)
createTagBody(3, {->
printHtmlPart(15)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(16)
expressionOut.print(error.field)
printHtmlPart(17)
}
printHtmlPart(18)
invokeTag('message','g',26,['error':(error)],-1)
printHtmlPart(19)
})
invokeTag('eachError','g',27,['bean':(torneoInstance),'var':("error")],3)
printHtmlPart(20)
})
invokeTag('hasErrors','g',29,['bean':(torneoInstance)],2)
printHtmlPart(13)
createTagBody(2, {->
printHtmlPart(21)
invokeTag('hiddenField','g',31,['name':("version"),'value':(torneoInstance?.version)],-1)
printHtmlPart(22)
invokeTag('render','g',33,['template':("form")],-1)
printHtmlPart(23)
invokeTag('actionSubmit','g',36,['class':("save"),'action':("update"),'value':(message(code: 'default.button.update.label', default: 'Update'))],-1)
printHtmlPart(24)
})
invokeTag('form','g',38,['url':([resource:torneoInstance, action:'update']),'method':("PUT")],2)
printHtmlPart(25)
})
invokeTag('captureBody','sitemesh',40,[:],1)
printHtmlPart(26)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442253123714L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
