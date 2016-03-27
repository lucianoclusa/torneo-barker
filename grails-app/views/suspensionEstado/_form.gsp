<%@ page import="ar.com.torneobarker.SuspensionEstado" %>



<div class="fieldcontain ${hasErrors(bean: suspensionEstadoInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="suspensionEstado.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${suspensionEstadoInstance?.descripcion}"/>

</div>

