<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Goleadores - ${torneoInstance}</title>
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
		<div id="list-estadisticaJugador" class="content scaffold-list" role="main">
			<h1>${torneoInstance}<g:link style="position:absolute; margin-left: 2em;" action="descargarPlanillaPosiciones"id="${torneoInstance?.id}" align="center"><img title="Descargar Planilla" alt="Descargar Planilla" width="20px" height="20px" src="${createLinkTo(dir: 'images', file: 'excel-xls-icon.png')}"/></g:link></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
						<g:sortableColumn property="posicion" title="Posicion"/>
				
						<g:sortableColumn property="cantGoles" title="Cant. Goles" />

						<g:sortableColumn property="jugador" title="Jugador" />
					
						<g:sortableColumn property="equipo" title="Equipo" />
					
					</tr>
				</thead>
				<tbody>
					<g:each in="${goleadoresList}" status="i" var="estadisticaJugador">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td>${fieldValue(bean: estadisticaJugador, field: "posicion")}</td>
						
						<td>${fieldValue(bean: estadisticaJugador, field: "golesFavor")}</td>
						
						<td><g:link controller="jugador" action="show" id="${estadisticaJugador.jugador.id}">${fieldValue(bean: estadisticaJugador, field: "jugador")}</g:link></td>

						<td><g:link controller="equipo" action="show" id="${estadisticaJugador.equipo.id}">${fieldValue(bean: estadisticaJugador, field: "equipo")}</g:link></td>

					</tr>
				</g:each>
				</tbody>
			</table>
		</div>
	</body>
</html>
