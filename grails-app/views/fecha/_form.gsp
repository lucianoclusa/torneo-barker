<%@ page import="ar.com.torneobarker.Fecha" %>



<div class="fieldcontain ${hasErrors(bean: fechaInstance, field: 'equipoLibre', 'error')} ">
	<label for="equipoLibre">
		<g:message code="fecha.equipoLibre.label" default="Equipo Libre" />
		
	</label>
	<g:select id="equipoLibre" name="equipoLibre.id" from="${ar.com.torneobarker.Equipo.list()}" optionKey="id" value="${fechaInstance?.equipoLibre?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: fechaInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="fecha.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${fechaInstance?.descripcion}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: fechaInstance, field: 'numeroFecha', 'error')} required">
	<label for="numeroFecha">
		<g:message code="fecha.numeroFecha.label" default="Numero Fecha" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numeroFecha" type="number" value="${fechaInstance.numeroFecha}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: fechaInstance, field: 'partidos', 'error')} ">
	<label for="partidos">
		<g:message code="fecha.partidos.label" default="Partidos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${fechaInstance?.partidos?}" var="p">
    <li><g:link controller="partido" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="partido" action="create" params="['fecha.id': fechaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'partido.label', default: 'Partido')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: fechaInstance, field: 'torneo', 'error')} required">
	<label for="torneo">
		<g:message code="fecha.torneo.label" default="Torneo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="torneo" name="torneo.id" from="${ar.com.torneobarker.Torneo.list()}" optionKey="nombre" required="" value="${fechaInstance?.torneo?.id}" class="many-to-one"/>

</div>

