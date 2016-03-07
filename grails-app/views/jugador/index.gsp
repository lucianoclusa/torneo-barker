
<%@ page import="ar.com.torneobarker.Jugador" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'jugador.label', default: 'Jugador')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-jugador" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		
		<g:form controller="jugador" action="searchJugador">
	    <div class="fieldcontain ${hasErrors(bean: suspensionInstance, field: 'descripcion', 'error')} no-print">
			<label for="descripcion">
				<g:message code="suspension.jugador.label" default="Jugador" />
			</label>
			<g:textField name="jugador" value="${jugador}" placeholder="Buscar Jugador"/>
			<g:submitButton name="Buscar"/>
		</div>
    	</g:form>
		
		<div id="list-jugador" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
						<th class="no-print"></th>
						<g:sortableColumn property="dni" title="${message(code: 'jugador.dni.label', default: 'Dni')}" />

						<g:sortableColumn property="nombre" title="${message(code: 'jugador.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="apellido" title="${message(code: 'jugador.apellido.label', default: 'Apellido')}" />
					
						<g:sortableColumn property="fechaNacimiento" title="${message(code: 'jugador.fechaNacimiento.label', default: 'Fecha Nacimiento')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${jugadorInstanceList}" status="i" var="jugadorInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td class="no-print"><g:link action="show" id="${jugadorInstance.id}"><img title="Ver" alt="Ver" width="20px" height="20px" src="${createLinkTo(dir: 'images', file: 'lupa.png')}"/></g:link></td>
					
						<td>${fieldValue(bean: jugadorInstance, field: "dni")}</td>

						<td>${fieldValue(bean: jugadorInstance, field: "nombre")}</td>
					
						<td>${fieldValue(bean: jugadorInstance, field: "apellido")}</td>
					
						<td><g:formatDate format="dd/MM/yyyy" date="${jugadorInstance.fechaNacimiento}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${jugadorInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
