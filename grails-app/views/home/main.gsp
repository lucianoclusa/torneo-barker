<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Posiciones - ${torneoInstance}</title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="fixture" controller="Torneo" id="${torneoInstance?.id}" action="show">Fixture</g:link></li>
				<li><g:link class="posiciones" controller="estadisticaEquipo" id="${torneoInstance?.id}" action="showPosiciones">Posiciones</g:link></li>
				<li><g:link class="goleadores" controller="estadisticaJugador" id="${torneoInstance?.id}" action="showGoleadores">Goleadores</g:link></li>
				<li><g:link class="fairPlay" controller="estadisticaFairPlay" id="${torneoInstance?.id}" action="showFairPlay">Fair Play</g:link></li>
			</ul>
		</div>
		<div id="list-estadisticaEquipo" class="content scaffold-list" role="main">
			<h1>${torneoInstance}<g:link style="position:absolute; margin-left: 2em;" action="descargarPlanillaPosiciones"id="${torneoInstance?.id}" align="center"><img title="Descargar Planilla" alt="Descargar Planilla" width="20px" height="20px" src="${createLinkTo(dir: 'images', file: 'excel-xls-icon.png')}"/></g:link></h1>
			<g:if test="${flash.message}">
				<div class="message"  role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
						<g:sortableColumn property="posicion" title="Posicion"/>
					
						<g:sortableColumn property="equipo" title="Equipo" />

						<g:sortableColumn property="partidosJugados" title="PJ" />									
					
						<g:sortableColumn property="partidosGanados" title="PG" />

						<g:sortableColumn property="partidosEmpatados" title="PE" />

						<g:sortableColumn property="partidosPerdidos" title="PP" />

						<g:sortableColumn property="golesFavor" title="GF" />

						<g:sortableColumn property="golesContra" title="GC" />

						<g:sortableColumn property="diferenciaGol" title="DF" />

						<g:sortableColumn property="puntos" title="Ptos" />		

					</tr>
				</thead>
				<tbody>
					<g:each in="${posicionesList}" status="i" var="estadisticaEquipo">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td>${fieldValue(bean: estadisticaEquipo, field: "posicion")}</td>
						
						<td><g:link controller="equipo" action="show" id="${estadisticaEquipo.equipo.id}">${fieldValue(bean: estadisticaEquipo.equipo, field: "nombre")}</g:link></td>

						<td>${fieldValue(bean: estadisticaEquipo, field: "partidosJugados")}</td>

						<td>${fieldValue(bean: estadisticaEquipo, field: "partidosGanados")}</td>

						<td>${fieldValue(bean: estadisticaEquipo, field: "partidosEmpatados")}</td>

						<td>${fieldValue(bean: estadisticaEquipo, field: "partidosPerdidos")}</td>

						<td>${fieldValue(bean: estadisticaEquipo, field: "golesFavor")}</td>

						<td>${fieldValue(bean: estadisticaEquipo, field: "golesContra")}</td>

						<td>${fieldValue(bean: estadisticaEquipo, field: "diferenciaGol")}</td>

						<td>${fieldValue(bean: estadisticaEquipo, field: "puntos")}</td>

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
