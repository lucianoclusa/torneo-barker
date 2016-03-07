<%@ page import="ar.com.torneobarker.Colegio" %>



<div class="fieldcontain ${hasErrors(bean: colegioInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="colegio.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${colegioInstance?.nombre}"/>

</div>

