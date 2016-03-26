
<%@ page import="ar.com.torneobarker.Fecha" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'fecha.label', default: 'Fecha')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-fecha" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-fecha" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list fecha">
			
				<g:if test="${fechaInstance?.equipoLibre}">
				<li class="fieldcontain">
					<span id="equipoLibre-label" class="property-label"><g:message code="fecha.equipoLibre.label" default="Equipo Libre" /></span>
					
						<span class="property-value" aria-labelledby="equipoLibre-label"><g:link controller="equipo" action="show" id="${fechaInstance?.equipoLibre?.id}">${fechaInstance?.equipoLibre?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${fechaInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="fecha.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${fechaInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fechaInstance?.numeroFecha}">
				<li class="fieldcontain">
					<span id="numeroFecha-label" class="property-label"><g:message code="fecha.numeroFecha.label" default="Numero Fecha" /></span>
					
						<span class="property-value" aria-labelledby="numeroFecha-label"><g:fieldValue bean="${fechaInstance}" field="numeroFecha"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fechaInstance?.partidos}">
				<li class="fieldcontain">
					<span id="partidos-label" class="property-label"><g:message code="fecha.partidos.label" default="Partidos" /></span>
					
						<g:each in="${fechaInstance.partidos}" var="p">
						<span class="property-value" aria-labelledby="partidos-label"><g:link controller="partido" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${fechaInstance?.torneo}">
				<li class="fieldcontain">
					<span id="torneo-label" class="property-label"><g:message code="fecha.torneo.label" default="Torneo" /></span>
					
						<span class="property-value" aria-labelledby="torneo-label"><g:link controller="torneo" action="show" id="${fechaInstance?.torneo?.id}">${fechaInstance?.torneo?.nombre?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:fechaInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${fechaInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
