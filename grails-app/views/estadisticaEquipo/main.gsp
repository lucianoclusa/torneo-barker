	<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Torneo Barker</title>
		<g:set var="entityName" value="Posiciones" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
			
		<div id="list-estadisticaEquipo" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
						<g:sortableColumn property="posicion" title="Posicion"/>
					
						<g:sortableColumn property="equipo" title="Equipo" />
					
						<g:sortableColumn property="puntos" title="Ptos" />		

						<g:sortableColumn property="partidosJugados" title="PJ" />									
					
						<g:sortableColumn property="partidosGanados" title="PG" />

						<g:sortableColumn property="partidosPerdidos" title="PP" />

						<g:sortableColumn property="partidosEmpatados" title="PE" />

						<g:sortableColumn property="golesFavor" title="GF" />

						<g:sortableColumn property="golesContra" title="GC" />

					</tr>
				</thead>
				<tbody>
					<g:each in="${posicionesList}" status="i" var="estadisticaEquipo">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td>${fieldValue(bean: estadisticaEquipo, field: "posicion")}</td>
						
						<td><g:link controller="equipo" action="show" id="${estadisticaEquipo.equipo.id}">${fieldValue(bean: estadisticaEquipo.equipo, field: "nombre")}</g:link></td>

						<td>${fieldValue(bean: estadisticaEquipo, field: "puntos")}</td>

						<td>${fieldValue(bean: estadisticaEquipo, field: "partidosJugados")}</td>

						<td>${fieldValue(bean: estadisticaEquipo, field: "partidosGanados")}</td>

						<td>${fieldValue(bean: estadisticaEquipo, field: "partidosPerdidos")}</td>

						<td>${fieldValue(bean: estadisticaEquipo, field: "partidosEmpatados")}</td>

						<td>${fieldValue(bean: estadisticaEquipo, field: "golesFavor")}</td>

						<td>${fieldValue(bean: estadisticaEquipo, field: "golesContra")}</td>

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
