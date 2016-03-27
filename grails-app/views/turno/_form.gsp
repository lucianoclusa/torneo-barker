<%@ page import="ar.com.torneobarker.Turno" %>



<div class="fieldcontain ${hasErrors(bean: turnoInstance, field: 'nroTurno', 'error')} required">
	<label for="nroTurno">
		<g:message code="turno.nroTurno.label" default="Nro Turno" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="nroTurno" type="number" value="${turnoInstance.nroTurno}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: turnoInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="turno.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${turnoInstance?.descripcion}"/>

</div>

