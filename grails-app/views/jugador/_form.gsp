<%@ page import="ar.com.torneobarker.Jugador" %>

<div class="fieldcontain ${hasErrors(bean: jugadorInstance, field: 'dni', 'error')} required">
	<label for="dni">
		<g:message code="jugador.dni.label" default="Dni" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="dni" required="" value="${jugadorInstance?.dni}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: jugadorInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="jugador.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${jugadorInstance?.nombre}"/>

</div>


<div class="fieldcontain ${hasErrors(bean: jugadorInstance, field: 'apellido', 'error')} required">
	<label for="apellido">
		<g:message code="jugador.apellido.label" default="Apellido" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="apellido" required="" value="${jugadorInstance?.apellido}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: jugadorInstance, field: 'fechaNacimiento', 'error')} required">
	<label for="fechaNacimiento">
		<g:message code="jugador.fechaNacimiento.label" default="Fecha Nacimiento" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaNacimiento" precision="day"  value="${jugadorInstance?.fechaNacimiento}"  />

</div>

