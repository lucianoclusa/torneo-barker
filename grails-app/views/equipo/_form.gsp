<%@ page import="ar.com.torneobarker.Equipo" %>



<div class="fieldcontain ${hasErrors(bean: equipoInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="equipo.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${equipoInstance?.nombre}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: equipoInstance, field: 'colegio', 'error')} ">
	<label for="colegio">
		<g:message code="equipo.colegio.label" default="Colegio" />
		
	</label>
	<g:select id="colegio" name="colegio.id" from="${ar.com.torneobarker.Colegio.list()}" optionKey="id" value="${equipoInstance?.colegio?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: equipoInstance, field: 'categoria', 'error')} required">
	<label for="categoria">
		<g:message code="equipo.categoria.label" default="Categoria" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="categoria" name="categoria.id" from="${ar.com.torneobarker.Categoria.list()}" optionKey="id" required="" value="${equipoInstance?.categoria?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: equipoInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="equipo.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${equipoInstance?.descripcion}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: equipoInstance, field: 'fechaCreacion', 'error')} required">
	<label for="fechaCreacion">
		<g:message code="equipo.fechaCreacion.label" default="Fecha Creacion" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaCreacion" precision="day"  value="${equipoInstance?.fechaCreacion}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: equipoInstance, field: 'jugadores', 'error')} ">
	<label for="jugadores">
		<g:message code="equipo.jugadores.label" default="Jugadores" />
		
	</label>
	<g:select name="jugadores" from="${ar.com.torneobarker.Jugador.list()}" style="height:300px; width:300px" multiple="multiple" optionKey="id" size="5" value="${equipoInstance?.jugadores*.id}" class="many-to-many"/>

</div>

