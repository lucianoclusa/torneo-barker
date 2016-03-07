<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Fair Play - ${torneoInstance}</title>
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

						<g:sortableColumn property="equipo" title="Equipo" />
				
						<g:sortableColumn property="tarjetasRojas" title="TR" />
					
						<g:sortableColumn property="tarjetasAmarillas" title="TA" />

						<g:sortableColumn property="puntosFairPlay" title="Ptos.FP" />

					</tr>
				</thead>
				<tbody>
					<g:each in="${fairPlayList}" status="i" var="estadisticaFairPlay">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td>${fieldValue(bean: estadisticaFairPlay, field: "posicion")}</td>

						<td>${fieldValue(bean: estadisticaFairPlay, field: "puntos")}</td>
						
						<td><g:link controller="equipo" action="show" id="${estadisticaFairPlay.equipo.id}">${fieldValue(bean: estadisticaFairPlay, field: "equipo")}</g:link></td>

						<td>${fieldValue(bean: estadisticaFairPlay, field: "tarjetasRojas")}</td>
						
						<td>${fieldValue(bean: estadisticaFairPlay, field: "tarjetasAmarillas")}</td>

					</tr>
				</g:each>
				</tbody>
			</table>
		</div>
	</body>
</html>
