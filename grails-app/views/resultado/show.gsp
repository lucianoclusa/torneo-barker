
<%@ page import="ar.com.torneobarker.Resultado" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'resultado.label', default: 'Resultado')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-resultado" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="fixture" controller="Torneo" id="${torneoInstance?.id}" action="show">Fixture</g:link></li>
				<li><g:link class="posiciones" controller="estadisticaEquipo" id="${torneoInstance?.id}" action="showPosiciones">Posiciones</g:link></li>
				<li><g:link class="goleadores" controller="estadisticaJugador" id="${torneoInstance?.id}" action="showGoleadores">Goleadores</g:link></li>
				<li><g:link class="fairPlay" controller="estadisticaFairPlay" id="${torneoInstance?.id}" action="showFairPlay">Fair Play</g:link></li>
			</ul>
		</div>
		<div id="show-resultado" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list resultado">
				<li class="fieldcontain equipoContainer">
					<div id="list-jugador" class="content scaffold-list" role="main">
						<h1>${local.nombre}</h1>
						<table>
						<thead>
								<tr>
									<g:sortableColumn property="jugador" title="${message(code: 'jugador.label', default: 'Jugador')}" />
									<g:sortableColumn property="golesFavor" title="${message(code: 'jugador.golesFavor.label', default: 'GF')}" />
									<g:sortableColumn property="golesContra" title="${message(code: 'jugador.golesContra.label', default: 'GC')}" />
									<g:sortableColumn property="tarjetasAmarillas" title="${message(code: 'jugador.tarjetaAmarilla.label', default: 'TA')}" />
									<g:sortableColumn property="tarjetasRojas" title="${message(code: 'jugador.tarjetaRoja.label', default: 'TR')}" />
									<g:sortableColumn property="cantSuspensiones" title="${message(code: 'jugador.tarjetaRoja.label', default: 'Cant. Susp.')}" />
									<g:sortableColumn property="comentario" title="${message(code: 'jugador.comentario.label', default: 'Comentario')}" />
								
								</tr>
							</thead>
							<tbody>
							<g:each in="${jugadoresLocal}" status="i" var="jugadorInstance">
								<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
								
									<td>${fieldValue(bean: jugadorInstance, field: "jugador")}</td>
									<td>${fieldValue(bean: jugadorInstance, field: "golesFavor")}</td>
									<td>${fieldValue(bean: jugadorInstance, field: "golesContra")}</td>
									<td>${fieldValue(bean: jugadorInstance, field: "tarjetasAmarillas")}</td>
									<td>${fieldValue(bean: jugadorInstance, field: "tarjetasRojas")}</td>
									<td>${fieldValue(bean: jugadorInstance, field: "cantSusp")}</td>
									<td>${fieldValue(bean: jugadorInstance, field: "comentario")}</td>
								
								</tr>
							</g:each>
							</tbody>
						</table>
					</div>
				</li>
				<li class="fieldcontain equipoContainer">
					<div id="list-jugador" class="content scaffold-list" role="main">
						<h1>${visitante.nombre}</h1>
						<table>
						<thead>
								<tr>
								
									<g:sortableColumn property="jugador" title="${message(code: 'jugador.label', default: 'Jugador')}" />
									<g:sortableColumn property="golesFavor" title="${message(code: 'jugador.golesFavor.label', default: 'GF')}" />
									<g:sortableColumn property="golesContra" title="${message(code: 'jugador.golesContra.label', default: 'GC')}" />
									<g:sortableColumn property="tarjetasAmarillas" title="${message(code: 'jugador.tarjetaAmarilla.label', default: 'TA')}" />
									<g:sortableColumn property="tarjetasRojas" title="${message(code: 'jugador.tarjetaRoja.label', default: 'TR')}" />
									<g:sortableColumn property="tarjetasRojas" title="${message(code: 'jugador.tarjetaRoja.label', default: 'TR')}" />
									<g:sortableColumn property="cantSuspensiones" title="${message(code: 'jugador.tarjetaRoja.label', default: 'Cant. Susp.')}" />
									<g:sortableColumn property="comentario" title="${message(code: 'jugador.comentario.label', default: 'Comentario')}" />
								
								</tr>
							</thead>
							<tbody>
							<g:each in="${jugadoresVisitante}" status="i" var="jugadorInstance">
								<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
								
									<td>${fieldValue(bean: jugadorInstance, field: "jugador")}</td>
									<td>${fieldValue(bean: jugadorInstance, field: "golesFavor")}</td>
									<td>${fieldValue(bean: jugadorInstance, field: "golesContra")}</td>
									<td>${fieldValue(bean: jugadorInstance, field: "tarjetasAmarillas")}</td>
									<td>${fieldValue(bean: jugadorInstance, field: "tarjetasRojas")}</td>
									<td>${fieldValue(bean: jugadorInstance, field: "cantSusp")}</td>
									<td>${fieldValue(bean: jugadorInstance, field: "comentario")}</td>
								
								</tr>
							</g:each>
							</tbody>
						</table>
					</div>
				</li>
			</ol>
			<g:form url="[resource:resultadoInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${resultadoInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
