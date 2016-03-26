
<%@ page import="ar.com.torneobarker.Suspension" %>
<%@ page import="ar.com.torneobarker.Fecha" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'suspension.label', default: 'Suspension')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-suspension" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-suspension" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list suspension">
			
				<g:if test="${suspensionInstance?.jugador}">
				<li class="fieldcontain">
					<span id="jugador-label" class="property-label"><g:message code="suspension.jugador.label" default="Jugador" /></span>
					
						<span class="property-value" aria-labelledby="jugador-label"><g:link controller="jugador" action="show" id="${suspensionInstance?.jugador?.id}">${suspensionInstance?.jugador?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
							
							
				<g:if test="${suspensionInstance?.fechaSancion}">
				<li class="fieldcontain">
					<span id="fechaSancion-label" class="property-label"><g:message code="suspension.fechaSancion.label" default="Fecha Sancion" /></span>
					
						<span class="property-value" aria-labelledby="fechaSancion-label"><g:formatDate format="dd/MM/yyyy" date="${suspensionInstance?.fechaSancion}" /></span>
					
				</li>
				</g:if>
				
				<g:if test="${suspensionInstance?.cantFechas}">
				<li class="fieldcontain">
					<span id="cantFechas-label" class="property-label"><g:message code="suspension.cantFechas.label" default="Cant Fechas" /></span>
					
						<span class="property-value" aria-labelledby="cantFechas-label"><g:fieldValue bean="${suspensionInstance}" field="cantFechas"/></span>
					
				</li>
				</g:if>
							
				<g:if test="${suspensionInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="suspension.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${suspensionInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
				
				<g:if test="${suspensionInstance?.equipo}">
				<li class="fieldcontain">
					<span id="equipo-label" class="property-label"><g:message code="suspension.equipo.label" default="Equipo" /></span>
					
						<span class="property-value" aria-labelledby="equipo-label"><g:link controller="equipo" action="show" id="${suspensionInstance?.equipo?.id}">${suspensionInstance?.equipo?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
							
				<g:if test="${suspensionInstance?.partido}">
				<li class="fieldcontain">
					<span id="partido-label" class="property-label"><g:message code="suspension.partido.label" default="Partido" /></span>
					
						<span class="property-value" aria-labelledby="partido-label"><g:link controller="partido" action="show" id="${suspensionInstance?.partido?.id}">${suspensionInstance?.partido?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>

				<g:if test="${suspensionInstance?.partido?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="suspension.fecha.label" default="Fecha" /></span>

						<span class="property-value" aria-labelledby="fecha-label">${ suspensionInstance.partido.fecha }</span>

				</li>
				</g:if>

				<g:if test="${suspensionInstance?.esIndefinido}">
				<li class="fieldcontain">
					<span id="esIndefinido-label" class="property-label"><g:message code="suspension.esIndefinido.label" default="Es Indefinido" /></span>

						<span class="property-value" aria-labelledby="esIndefinido-label"><g:formatBoolean boolean="${suspensionInstance?.esIndefinido}" /></span>

				</li>
				</g:if>

				<g:if test="${suspensionInstance?.torneo}">
				<li class="fieldcontain">
					<span id="torneo-label" class="property-label"><g:message code="suspension.torneo.label" default="Torneo" /></span>
					
						<span class="property-value" aria-labelledby="torneo-label"><g:link controller="torneo" action="show" id="${suspensionInstance?.torneo?.id}">${suspensionInstance?.torneo?.nombre?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>

				
				<g:if test="${suspensionInstance?.esProvisoria}">
				<li class="fieldcontain">
					<span id="esProvisoria-label" class="property-label"><g:message code="suspension.esProvisoria.label" default="Provisoria" /></span>

						<span class="property-value" aria-labelledby="esProvisoria-label"><g:formatBoolean boolean="${suspensionInstance?.esProvisoria}" /></span>

				</li>
				</g:if>	
				<g:if test="${suspensionInstance?.estado}">
				<li class="fieldcontain">
					<span id="estado-label" class="property-label"><g:message code="suspension.estado.label" default="Estado" /></span>

						<span class="property-value" aria-labelledby="estado-label">${suspensionInstance?.estado}</span>

				</li>
				</g:if>
			</ol>

				<g:form url="[resource:suspensionInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${suspensionInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
