
<%@ page import="ar.com.torneobarker.Torneo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'torneo.label', default: 'Torneo')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-torneo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-torneo" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th class="no-print"></th>

						<g:sortableColumn property="nombre" title="${message(code: 'torneo.nombre.label', default: 'Nombre')}" />

						<g:sortableColumn property="anio" title="${message(code: 'torneo.anio.label', default: 'Año')}" />
						
						<g:sortableColumn property="temporada" title="${message(code: 'torneo.temporada.label', default: 'Temporada')}" />

						<g:sortableColumn property="categoria" title="${message(code: 'torneo.categoria.label', default: 'Categoria')}" />
					
						<g:sortableColumn property="fechaInicio" title="${message(code: 'torneo.fechaInicio.label', default: 'Fecha Inicio')}" />
					
						<g:sortableColumn property="fechaFin" title="${message(code: 'torneo.fechaFin.label', default: 'Fecha Fin')}" />

					</tr>
				</thead>
				<tbody>
				<g:each in="${torneoInstanceList}" status="i" var="torneoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td class="no-print"><g:link action="show" id="${torneoInstance.id}"><img title="Ver" alt="Ver" width="20px" height="20px" src="${createLinkTo(dir: 'images', file: 'lupa.png')}"/></g:link></td>
						
						<td>${fieldValue(bean: torneoInstance, field: "nombre")}</td>
						
						<td>${fieldValue(bean: torneoInstance, field: "anio")}</td>
						
						<td>${fieldValue(bean: torneoInstance, field: "temporada.descripcion")}</td>
						
						<td>${fieldValue(bean: torneoInstance, field: "categoria.descripcion")}</td>
											
						<td><g:formatDate format="dd/MM/yyyy" date="${torneoInstance.fechaInicio}" /></td>
					
						<td><g:formatDate format="dd/MM/yyyy" date="${torneoInstance.fechaFin}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${torneoInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
