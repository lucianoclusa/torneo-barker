import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_torneoBarker_resultadocreate_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/resultado/create.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
expressionOut.print(g.createLink(controller:'airport',action:'getJson'))
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',25,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(3)
invokeTag('set','g',26,['var':("entityName"),'value':(message(code: 'resultado.label', default: 'Resultado'))],-1)
printHtmlPart(3)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',27,['code':("default.create.label"),'args':([entityName])],-1)
})
invokeTag('captureTitle','sitemesh',27,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',27,[:],2)
printHtmlPart(4)
})
invokeTag('captureHead','sitemesh',28,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
invokeTag('message','g',30,['code':("default.link.skip.label"),'default':("Skip to content&hellip;")],-1)
printHtmlPart(6)
expressionOut.print(createLink(uri: '/'))
printHtmlPart(7)
invokeTag('message','g',33,['code':("default.home.label")],-1)
printHtmlPart(8)
createTagBody(2, {->
invokeTag('message','g',34,['code':("default.list.label"),'args':([entityName])],-1)
})
invokeTag('link','g',34,['class':("list"),'action':("index")],2)
printHtmlPart(9)
invokeTag('message','g',38,['code':("default.create.label"),'args':([entityName])],-1)
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
invokeTag('message','g',45,['error':(error)],-1)
printHtmlPart(19)
})
invokeTag('eachError','g',46,['bean':(resultadoInstance),'var':("error")],3)
printHtmlPart(20)
})
invokeTag('hasErrors','g',48,['bean':(resultadoInstance)],2)
printHtmlPart(13)
createTagBody(2, {->
printHtmlPart(21)
invokeTag('render','g',51,['template':("form")],-1)
printHtmlPart(22)
invokeTag('submitButton','g',54,['name':("create"),'class':("save"),'value':(message(code: 'default.button.create.label', default: 'Create'))],-1)
printHtmlPart(23)
})
invokeTag('form','g',56,['url':([resource:resultadoInstance, action:'save'])],2)
printHtmlPart(24)
})
invokeTag('captureBody','sitemesh',58,[:],1)
printHtmlPart(25)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442253123201L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
