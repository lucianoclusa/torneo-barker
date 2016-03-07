
<%@ page import="ar.com.torneobarker.Gol" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'gol.label', default: 'Gol')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-gol" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-gol" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="enContra" title="${message(code: 'gol.enContra.label', default: 'En Contra')}" />
					
						<th><g:message code="gol.jugador.label" default="Jugador" /></th>
					
						<th><g:message code="gol.partido.label" default="Partido" /></th>
					
						<th><g:message code="gol.torneo.label" default="Torneo" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${golInstanceList}" status="i" var="golInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${golInstance.id}">${fieldValue(bean: golInstance, field: "enContra")}</g:link></td>
					
						<td>${fieldValue(bean: golInstance, field: "jugador")}</td>
					
						<td>${fieldValue(bean: golInstance, field: "partido")}</td>
					
						<td>${fieldValue(bean: golInstance, field: "torneo")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${golInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
