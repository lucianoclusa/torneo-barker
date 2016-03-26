
<%@ page import="ar.com.torneobarker.Fecha" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'fecha.label', default: 'Fecha')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-fecha" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-fecha" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="fecha.equipoLibre.label" default="Equipo Libre" /></th>
					
						<g:sortableColumn property="descripcion" title="${message(code: 'fecha.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="numeroFecha" title="${message(code: 'fecha.numeroFecha.label', default: 'Numero Fecha')}" />
					
						<th><g:message code="fecha.torneo.label" default="Torneo" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${fechaInstanceList}" status="i" var="fechaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${fechaInstance.id}">${fieldValue(bean: fechaInstance, field: "equipoLibre")}</g:link></td>
					
						<td>${fieldValue(bean: fechaInstance, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: fechaInstance, field: "numeroFecha")}</td>
					
						<td>${fieldValue(bean: fechaInstance, field: "torneo.nombre")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${fechaInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
