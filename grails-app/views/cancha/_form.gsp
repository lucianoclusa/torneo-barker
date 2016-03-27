<%@ page import="ar.com.torneobarker.Cancha" %>



<div class="fieldcontain ${hasErrors(bean: canchaInstance, field: 'nroCancha', 'error')} required">
	<label for="nroCancha">
		<g:message code="cancha.nroCancha.label" default="Nro Cancha" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="nroCancha" type="number" value="${canchaInstance.nroCancha}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: canchaInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="cancha.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${canchaInstance?.descripcion}"/>

</div>

