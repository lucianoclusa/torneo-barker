
<%@ page import="ar.com.torneobarker.Equipo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'equipo.label', default: 'Equipo')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-equipo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="showLoadFromExcel">Cargar desde EXCEL</g:link></li>
			</ul>
		</div>
		<div id="show-equipo" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list equipo">
			
				<g:if test="${equipoInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="equipo.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${equipoInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${equipoInstance?.colegio}">
				<li class="fieldcontain">
					<span id="colegio-label" class="property-label"><g:message code="equipo.colegio.label" default="Colegio" /></span>
					
						<span class="property-value" aria-labelledby="colegio-label"><g:link controller="colegio" action="show" id="${equipoInstance?.colegio?.id}">${equipoInstance?.colegio?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${equipoInstance?.categoria}">
				<li class="fieldcontain">
					<span id="categoria-label" class="property-label"><g:message code="equipo.categoria.label" default="Categoria" /></span>
					
						<span class="property-value" aria-labelledby="categoria-label"><g:link controller="categoria" action="show" id="${equipoInstance?.categoria?.id}">${equipoInstance?.categoria?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${equipoInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="equipo.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${equipoInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${equipoInstance?.fechaCreacion}">
				<li class="fieldcontain">
					<span id="fechaCreacion-label" class="property-label"><g:message code="equipo.fechaCreacion.label" default="Fecha Creacion" /></span>
					
						<span class="property-value" aria-labelledby="fechaCreacion-label"><g:formatDate date="${equipoInstance?.fechaCreacion}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${equipoInstance?.jugadores}">
				<li class="fieldcontain">
					<span id="jugadores-label" class="property-label"><g:message code="equipo.jugadores.label" default="Jugadores" /></span>
					
						<g:each in="${equipoInstance.jugadores}" var="j">
						<span class="property-value" aria-labelledby="jugadores-label"><g:link controller="jugador" action="show" id="${j.id}">${j?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:equipoInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${equipoInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
