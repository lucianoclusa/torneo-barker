
<%@ page import="ar.com.torneobarker.Jugador" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'jugador.label', default: 'Jugador')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-jugador" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-jugador" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list jugador">
			
				<g:if test="${jugadorInstance?.dni}">
				<li class="fieldcontain">
					<span id="dni-label" class="property-label"><g:message code="jugador.dni.label" default="Dni" /></span>
					
						<span class="property-value" aria-labelledby="dni-label"><g:fieldValue bean="${jugadorInstance}" field="dni"/></span>
					
				</li>
				</g:if>
							
				<g:if test="${jugadorInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="jugador.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${jugadorInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${jugadorInstance?.apellido}">
				<li class="fieldcontain">
					<span id="apellido-label" class="property-label"><g:message code="jugador.apellido.label" default="Apellido" /></span>
					
						<span class="property-value" aria-labelledby="apellido-label"><g:fieldValue bean="${jugadorInstance}" field="apellido"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${jugadorInstance?.fechaNacimiento}">
				<li class="fieldcontain">
					<span id="fechaNacimiento-label" class="property-label"><g:message code="jugador.fechaNacimiento.label" default="Fecha Nacimiento" /></span>
						<span class="property-value" aria-labelledby="fechaNacimiento-label"><g:formatDate format="dd/MM/yyyy" date="${jugadorInstance?.fechaNacimiento}" /></span>
				</li>
				</g:if>
				
				<li class="fieldcontain">
					<span id="equipos-label" class="property-label"><g:message code="jugador.equipos.label" default="Equipos" /></span>
						<g:each in="${ar.com.torneobarker.Equipo.findAll("from Equipo e where ? in elements(e.jugadores)", [jugadorInstance])}" status="i" var="equipoInstance">
							<g:if test="${i>0}" > ,</g:if>

							<span class="property-value" aria-labelledby="fechaNacimiento-label"><g:link controller="equipo" action="show" id="${equipoInstance.id}">${equipoInstance}</g:link>
							</span>
						</g:each>
								
				</li>
			
			</ol>
			<g:form url="[resource:jugadorInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${jugadorInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
