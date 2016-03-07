<%@ page import="ar.com.torneobarker.Gol" %>



<div class="fieldcontain ${hasErrors(bean: golInstance, field: 'enContra', 'error')} ">
	<label for="enContra">
		<g:message code="gol.enContra.label" default="En Contra" />
		
	</label>
	<g:checkBox name="enContra" value="${golInstance?.enContra}" />

</div>

<div class="fieldcontain ${hasErrors(bean: golInstance, field: 'jugador', 'error')} required">
	<label for="jugador">
		<g:message code="gol.jugador.label" default="Jugador" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="jugador" name="jugador.id" from="${ar.com.torneobarker.Jugador.list()}" optionKey="id" required="" value="${golInstance?.jugador?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: golInstance, field: 'partido', 'error')} required">
	<label for="partido">
		<g:message code="gol.partido.label" default="Partido" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="partido" name="partido.id" from="${ar.com.torneobarker.Partido.list()}" optionKey="id" required="" value="${golInstance?.partido?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: golInstance, field: 'torneo', 'error')} required">
	<label for="torneo">
		<g:message code="gol.torneo.label" default="Torneo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="torneo" name="torneo.id" from="${ar.com.torneobarker.Torneo.list()}" optionKey="id" required="" value="${golInstance?.torneo?.id}" class="many-to-one"/>

</div>

