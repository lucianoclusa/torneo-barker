import ar.com.torneobarker.Anio
import ar.com.torneobarker.Temporada
import ar.com.torneobarker.Categoria
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_torneoBarker_layoutsmain_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/main.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(0)
invokeTag('captureMeta','sitemesh',13,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("Content-Type"),'content':("text/html; charset=UTF-8")],-1)
printHtmlPart(0)
invokeTag('captureMeta','sitemesh',14,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("X-UA-Compatible"),'content':("IE=edge,chrome=1")],-1)
printHtmlPart(0)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('layoutTitle','g',15,['default':("Torneo Barker")],-1)
})
invokeTag('captureTitle','sitemesh',15,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',15,[:],2)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',17,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("viewport"),'content':("width=device-width, initial-scale=1.0")],-1)
printHtmlPart(3)
expressionOut.print(assetPath(src: 'favicon.ico'))
printHtmlPart(4)
expressionOut.print(assetPath(src: 'apple-touch-icon.png'))
printHtmlPart(5)
expressionOut.print(assetPath(src: 'apple-touch-icon-retina.png'))
printHtmlPart(6)
invokeTag('stylesheet','asset',35,['src':("application.css")],-1)
printHtmlPart(0)
invokeTag('javascript','asset',36,['src':("application.js")],-1)
printHtmlPart(0)
invokeTag('stylesheet','asset',37,['src':("styles.css")],-1)
printHtmlPart(0)
invokeTag('javascript','asset',38,['src':("script.js")],-1)
printHtmlPart(0)
invokeTag('layoutHead','g',39,[:],-1)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',41,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(7)
invokeTag('image','asset',45,['width':("80px"),'height':("80px"),'id':("logoImg"),'src':("logo.jpg"),'alt':("Grails")],-1)
printHtmlPart(8)
createTagBody(2, {->
printHtmlPart(9)
createClosureForHtmlPart(10, 3)
invokeTag('link','g',49,['class':("login"),'controller':("logout")],3)
printHtmlPart(11)
})
invokeTag('ifLoggedIn','sec',50,[:],2)
printHtmlPart(11)
createTagBody(2, {->
printHtmlPart(9)
createClosureForHtmlPart(12, 3)
invokeTag('link','g',52,['class':("login"),'controller':("login"),'action':("index")],3)
printHtmlPart(11)
})
invokeTag('ifNotLoggedIn','sec',53,[:],2)
printHtmlPart(13)
createClosureForHtmlPart(14, 2)
invokeTag('link','g',89,['elementId':("torneoMenu"),'controller':("torneo"),'action':("index")],2)
printHtmlPart(15)
createClosureForHtmlPart(16, 2)
invokeTag('link','g',90,['elementId':("equipoMenu"),'controller':("equipo"),'action':("index")],2)
printHtmlPart(15)
createClosureForHtmlPart(17, 2)
invokeTag('link','g',91,['elementId':("jugadorMenu"),'controller':("jugador"),'action':("index")],2)
printHtmlPart(15)
createClosureForHtmlPart(18, 2)
invokeTag('link','g',92,['elementId':("suspensionMenu"),'controller':("suspension"),'action':("index")],2)
printHtmlPart(19)
invokeTag('layoutBody','g',97,[:],-1)
printHtmlPart(20)
invokeTag('message','g',102,['code':("spinner.alt"),'default':("Loading&hellip;")],-1)
printHtmlPart(21)
})
invokeTag('captureBody','sitemesh',104,[:],1)
printHtmlPart(22)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1454606045086L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
