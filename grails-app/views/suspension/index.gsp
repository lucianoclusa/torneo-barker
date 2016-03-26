
<%@ page import="ar.com.torneobarker.Suspension" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'suspension.label', default: 'Suspension')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-suspension" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>

	    <g:form controller="suspension" action="searchSuspensiones">
	    <div class="fieldcontain ${hasErrors(bean: suspensionInstance, field: 'descripcion', 'error')} no-print">
			<label for="descripcion">
				<g:message code="suspension.jugador.label" default="Jugador" />
			</label>
			<g:textField name="jugador" value="${jugador}" placeholder="Buscar Jugador"/>
			<g:submitButton name="Buscar"/>
		</div>
    	</g:form>
		
		<div id="list-suspension" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
						<th class="no-print"></th>
						<g:sortableColumn property="fechaSancion" title="${message(code: 'suspension.fechaInicio.label', default: 'Fecha Susp.')}" />

						<g:sortableColumn property="cantFechas" title="${message(code: 'suspension.cantFechas.label', default: 'Cant.')}" />
						
						<g:sortableColumn property="descripcion" title="${message(code: 'suspension.descripcion.label', default: 'Descripción')}" />
					
						<g:sortableColumn property="jugador" title="${message(code: 'suspension.jugador.label', default: 'Jugador')}" />
					
						<g:sortableColumn property="equipo" title="${message(code: 'suspension.equipo.label', default: 'Equipo')}" />
					
						<g:sortableColumn property="torneo" title="${message(code: 'suspension.torneo.label', default: 'Torneo')}" />
	
						<g:sortableColumn property="estado" title="${message(code: 'suspension.estado.label', default: 'Estado')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${suspensionInstanceList}" status="i" var="suspensionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td class="no-print" style="text-align: center"><g:link action="show" id="${suspensionInstance.id}"><img title="Ver" alt="Ver" width="20px" height="20px" src="${createLinkTo(dir: 'images', file: 'lupa.png')}"/></g:link></td>
						
						<td><g:formatDate format="dd/MM/yyyy" date="${suspensionInstance.fechaSancion}" /></td>
					
						<td><g:if test="${suspensionInstance.esIndefinido}">Permanente</g:if>
						<g:elseif test="${suspensionInstance.esProvisoria}">Provisoria</g:elseif>
						<g:else>${fieldValue(bean: suspensionInstance, field: "cantFechas")}</g:else></td>

						<td>${fieldValue(bean: suspensionInstance, field: "descripcion")}</td>

						<td>${fieldValue(bean: suspensionInstance, field: "jugador")}</td>
					
						<td>${fieldValue(bean: suspensionInstance, field: "equipo.colegio")}</td>

						<td>${fieldValue(bean: suspensionInstance, field: "torneo.nombre")}</td>

						<td>${fieldValue(bean: suspensionInstance, field: "estado")} </td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${suspensionInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
