import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_torneoBarker_equipoloadFromExcel_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/equipo/loadFromExcel.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(1)
invokeTag('set','g',5,['var':("entityName"),'value':(message(code: 'equipo.label', default: 'Equipo'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',7,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',9,['code':("default.link.skip.label"),'default':("Skip to content&hellip;")],-1)
printHtmlPart(5)
createTagBody(2, {->
invokeTag('message','g',12,['code':("default.home.label")],-1)
})
invokeTag('link','g',12,['class':("home"),'action':("index")],2)
printHtmlPart(6)
createTagBody(2, {->
invokeTag('message','g',13,['code':("default.new.label"),'args':([entityName])],-1)
})
invokeTag('link','g',13,['class':("create"),'action':("create")],2)
printHtmlPart(6)
createClosureForHtmlPart(7, 2)
invokeTag('link','g',14,['class':("create"),'action':("showLoadFromExcel")],2)
printHtmlPart(8)
if(true && (flash.message)) {
printHtmlPart(9)
expressionOut.print(flash.message)
printHtmlPart(10)
}
printHtmlPart(11)
createTagBody(2, {->
printHtmlPart(12)
createTagBody(3, {->
printHtmlPart(13)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(14)
expressionOut.print(error.field)
printHtmlPart(15)
}
printHtmlPart(16)
invokeTag('message','g',25,['error':(error)],-1)
printHtmlPart(17)
})
invokeTag('eachError','g',26,['bean':(equipoInstance),'var':("error")],3)
printHtmlPart(18)
})
invokeTag('hasErrors','g',28,['bean':(equipoInstance)],2)
printHtmlPart(11)
createTagBody(2, {->
printHtmlPart(19)
invokeTag('submitButton','g',34,['name':("create"),'class':("save"),'value':(message(code: 'default.button.load.label', default: 'Cargar'))],-1)
printHtmlPart(20)
})
invokeTag('form','g',36,['action':("loadFromExcel"),'method':("post"),'enctype':("multipart/form-data")],2)
printHtmlPart(21)
})
invokeTag('captureBody','sitemesh',40,[:],1)
printHtmlPart(22)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1444048531184L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
