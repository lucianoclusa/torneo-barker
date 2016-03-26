
<%@ page import="ar.com.torneobarker.Torneo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'torneo.label', default: 'Torneo')}" />
		<title>${torneoInstance.nombre}</title>
	</head>
	<body>
		<a href="#show-torneo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="fixture" controller="Torneo" id="${torneoInstance?.id}" action="show">Fixture</g:link></li>
				<li><g:link class="posiciones" controller="estadisticaEquipo" id="${torneoInstance?.id}" action="showPosiciones">Posiciones</g:link></li>
				<li><g:link class="goleadores" controller="estadisticaJugador" id="${torneoInstance?.id}" action="showGoleadores">Goleadores</g:link></li>
				<li><g:link class="fairPlay" controller="estadisticaFairPlay" id="${torneoInstance?.id}" action="showFairPlay">Fair Play</g:link></li>
			</ul>
		</div>
		<div id="show-torneo" class="content scaffold-show" role="main">
			<h1>${torneoInstance.nombre}</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list torneo">
			
							
				<g:if test="${torneoInstance?.temporada}">
				<li class="fieldcontain">
					<span id="temporada-label" class="property-label"><g:message code="torneo.temporada.label" default="Temporada" /></span>
					
						<span class="property-value" aria-labelledby="temporada-label">${torneoInstance?.temporada?.descripcion?.encodeAsHTML()}</span>
					
				</li>
				</g:if>			
			
				<g:if test="${torneoInstance?.categoria}">
				<li class="fieldcontain">
					<span id="categoria-label" class="property-label"><g:message code="torneo.categoria.label" default="Categoria" /></span>
					
						<span class="property-value" aria-labelledby="categoria-label">${torneoInstance?.categoria?.descripcion?.encodeAsHTML()}</span>
					
				</li>
				</g:if>
			
				<g:if test="${torneoInstance?.fechaInicio}">
				<li class="fieldcontain">
					<span id="fechaInicio-label" class="property-label"><g:message code="torneo.fechaInicio.label" default="Fecha Inicio" /></span>
					
						<span class="property-value" aria-labelledby="fechaInicio-label"><g:formatDate format="dd/MM/yyyy" date="${torneoInstance?.fechaInicio}" /></span>
					
				</li>
				</g:if>
							
				<g:if test="${torneoInstance?.fechaFin}">
				<li class="fieldcontain">
					<span id="fechaFin-label" class="property-label"><g:message code="torneo.fechaFin.label" default="Fecha Fin" /></span>
					
						<span class="property-value" aria-labelledby="fechaFin-label"><g:formatDate format="dd/MM/yyyy" date="${torneoInstance?.fechaFin}" /></span>
					
				</li>
				</g:if>

			
			
				<g:if test="${torneoInstance?.fechas}">
				<li class="fieldcontain">					
						<g:each in="${torneoInstance.fechas.sort { it.numeroFecha }}" var="fecha">
						
						
								<table>
									<thead>
										<tr>
										
											<th colspan="6"><g:message code="partido.local.label" default="${fecha?.descripcion?.encodeAsHTML()}" /></th>
											<th colspan="1"><g:link class ="no-print" controller="estadisticaEquipo" action="descargarPlanillaFecha" params="[fechaId: fecha.id]" align="center"><img title="Descargar Planilla" alt="Descargar Planilla" width="20px" height="20px" src="${createLinkTo(dir: 'images', file: 'excel-xls-icon.png')}"/></g:link></th>
										</tr>
									</thead>
									<tbody>
									<g:each in="${fecha.partidos.sort { it.id }}" status="i" var="partido">
										<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
											<td style="text-align: center"><g:link  controller="equipo" action="show" id="${partido.local.id}">${fieldValue(bean: partido, field: "local.nombre")}</g:link></td>						
											<td>${partido.golesLocal==null?"-":partido.golesLocal}</td>
											<td>VS</td>
											<td>${partido.golesVisitante==null?"-":partido.golesVisitante}</td>
											<td style="text-align: center"><g:link controller="equipo" action="show" id="${partido.visitante.id}">${fieldValue(bean: partido, field: "visitante.nombre")}</g:link>	</td>
											<g:if test="${partido.resultado!=null}">
												<td style="text-align: center"><g:link class ="no-print" controller="resultado" action="eliminarResultado"  params="[resultadoId: partido.resultado.id]" style=""><img title="Borrar resultado" alt="Borrar resultado" width="20px" height="20px" src="${createLinkTo(dir: 'images', file: 'delete_icon.png')}"/></g:link></td>
												<td style="text-align: center"><g:link class ="no-print" controller="resultado" action="show" params="[partidoId: partido.id]" style=""><img title="Ver" alt="Ver" width="20px" height="20px" src="${createLinkTo(dir: 'images', file: 'lupa.png')}"/></g:link></td>
											</g:if>
											<g:else>												
												<td style="text-align: center"><g:link class ="no-print" controller="resultado" action="cargarResultado" params="[partidoId: partido.id]" style=""><img title="Cargar resultado" alt="Caragar resultado" width="20px" height="20px" src="${createLinkTo(dir: 'images', file: 'cargar_resultado.png')}"/></g:link></td>
												<td style="text-align: center"></td>
											</g:else>
										</tr>
									</g:each>
									<g:if test="${fecha.equipoLibre==null?false:true}">
									<tr align="center">
										<td colspan="8" align="center"> Libre: ${fecha.equipoLibre?.nombre} </td>
									</tr>
									</g:if>
									</tbody>
								</table>
						</g:each>
				</li>
				</g:if>
			</ol>
			<g:form url="[resource:torneoInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${torneoInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
