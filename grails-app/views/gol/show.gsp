
<%@ page import="ar.com.torneobarker.Gol" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'gol.label', default: 'Gol')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-gol" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-gol" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list gol">
			
				<g:if test="${golInstance?.enContra}">
				<li class="fieldcontain">
					<span id="enContra-label" class="property-label"><g:message code="gol.enContra.label" default="En Contra" /></span>
					
						<span class="property-value" aria-labelledby="enContra-label"><g:formatBoolean boolean="${golInstance?.enContra}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${golInstance?.jugador}">
				<li class="fieldcontain">
					<span id="jugador-label" class="property-label"><g:message code="gol.jugador.label" default="Jugador" /></span>
					
						<span class="property-value" aria-labelledby="jugador-label"><g:link controller="jugador" action="show" id="${golInstance?.jugador?.id}">${golInstance?.jugador?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${golInstance?.partido}">
				<li class="fieldcontain">
					<span id="partido-label" class="property-label"><g:message code="gol.partido.label" default="Partido" /></span>
					
						<span class="property-value" aria-labelledby="partido-label"><g:link controller="partido" action="show" id="${golInstance?.partido?.id}">${golInstance?.partido?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${golInstance?.torneo}">
				<li class="fieldcontain">
					<span id="torneo-label" class="property-label"><g:message code="gol.torneo.label" default="Torneo" /></span>
					
						<span class="property-value" aria-labelledby="torneo-label"><g:link controller="torneo" action="show" id="${golInstance?.torneo?.id}">${golInstance?.torneo?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:golInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${golInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
