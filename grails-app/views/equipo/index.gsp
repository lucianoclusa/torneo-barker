
<%@ page import="ar.com.torneobarker.Equipo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'equipo.label', default: 'Equipo')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-equipo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="showLoadFromExcel">Cargar desde EXCEL</g:link></li>
			</ul>
		</div>
		<div id="list-equipo" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
						<th class="no-print"></th>
						
						<g:sortableColumn property="nombre" title="${message(code: 'equipo.nombre.label', default: 'Nombre')}" />
					
						<th><g:message code="equipo.colegio.label" default="Colegio" /></th>
					
						<th><g:message code="equipo.categoria.label" default="Categoria" /></th>
					
						<g:sortableColumn property="descripcion" title="${message(code: 'equipo.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="fechaCreacion" title="${message(code: 'equipo.fechaCreacion.label', default: 'Fecha Creacion')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${equipoInstanceList}" status="i" var="equipoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						
						<td class="no-print"><g:link action="show" id="${equipoInstance.id}"><img title="Ver" alt="Ver" width="20px" height="20px" src="${createLinkTo(dir: 'images', file: 'lupa.png')}"/></g:link></td>
					
						<td>${fieldValue(bean: equipoInstance, field: "nombre")}</td>
					
						<td>${fieldValue(bean: equipoInstance, field: "colegio")}</td>
					
						<td>${fieldValue(bean: equipoInstance, field: "categoria")}</td>
					
						<td>${fieldValue(bean: equipoInstance, field: "descripcion")}</td>
					
						<td><g:formatDate format="dd/MM/yyyy" date="${equipoInstance.fechaCreacion}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${equipoInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
