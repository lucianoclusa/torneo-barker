<%@ page import="ar.com.torneobarker.Torneo" %>

<div class="fieldcontain ${hasErrors(bean: torneoInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="torneo.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${torneoInstance?.nombre}"/>

</div>
<div class="fieldcontain ${hasErrors(bean: torneoInstance, field: 'anio', 'error')} required">
	<label for="anio">
		<g:message code="torneo.anio.label" default="Año" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="anio" name="anio" from="${2000..2100}"  required="" value="${torneoInstance?.anio}" noSelection="['':'-Seleccione año-']"/>
</div>
<g:if test="${torneoInstance.temporada==null}">
<div class="fieldcontain ${hasErrors(bean: torneoInstance, field: 'temporada', 'error')} required">
	<label for="temporada">
		<g:message code="torneo.temporada.label" default="Temporada" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="temporada" name="temporada.id" optionValue="descripcion" from="${ar.com.torneobarker.Temporada.list()}" optionKey="id" required="" value="${torneoInstance?.temporada?.id}" class="many-to-one"/>
</div>
</g:if>
<g:if test="${torneoInstance.categoria==null}">

<div class="fieldcontain ${hasErrors(bean: torneoInstance, field: 'categoria', 'error')} required">
	<label for="categoria">
		<g:message code="torneo.categoria.label" default="Categoria" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="categoria" name="categoria.id" optionValue="descripcion" from="${ar.com.torneobarker.Categoria.list()}" optionKey="id" required="" value="${torneoInstance?.categoria?.id}" class="many-to-one"  onchange="categoriaChanged(this.value)"/>
</div>
<script>
    function categoriaChanged(categoriaId) {
        jQuery.ajax({
            type:'GET',
            url:"${createLink(controller: 'equipo', action: 'categoriaChanged')}" + "?categoriaId=" + categoriaId,
            success:function(data,textStatus){
                jQuery('#equiposContainer').html(data);
                },
            error:function(XMLHttpRequest,textStatus,errorThrown){}});
    }
</script>
</g:if>
<g:if test="${torneoInstance.equipos==null}">
<div class="fieldcontain ${hasErrors(bean: torneoInstance, field: 'equipos', 'error')} required">
	<label for="equipos">
		<g:message code="torneo.equipos.label" default="Equipos" />
		<span class="required-indicator">*</span>
	</label>
	<span id="equiposContainer">
		<g:select id="equipos" name="equipos" from="${ar.com.torneobarker.Equipo.findAllByCategoria(ar.com.torneobarker.Categoria.list().get(0))}" style="height:300px; width:300px" multiple="true" optionKey="id" size="5" value="${torneoInstante?.equipos*.id}" class="many-to-many"/>
	</span>
	
</div>
</g:if>
<div class="fieldcontain ${hasErrors(bean: torneoInstance, field: 'fechaInicio', 'error')} required">
	<label for="fechaInicio">
		<g:message code="torneo.fechaInicio.label" default="Fecha Inicio" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaInicio" precision="day"  value="${torneoInstance?.fechaInicio}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: torneoInstance, field: 'fechaFin', 'error')} required">
	<label for="fechaFin">
		<g:message code="torneo.fechaFin.label" default="Fecha Fin" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaFin" precision="day"  value="${torneoInstance?.fechaFin}"  />
</div>
<!-- Comentado editar fechas -->
<g:if test="${false}">
<div class="fieldcontain">
	<g:each in="${torneoInstance.fechas.sort { it.numeroFecha }}" var="fecha">
			<table>
				<thead>
					<tr>
						<g:if test="${partido.resultado==null}">
							<th colspan="7"><g:message code="partido.local.label" default="${fecha?.descripcion?.encodeAsHTML()}" /></th>
						</g:if>
					</tr>
				</thead>
				<tbody>
				<g:each in="${fecha.getPartidos().sort { it.id }}" status="i" var="partido">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							<td style="text-align: center"><g:link  controller="equipo" action="show" id="${partido.local.id}">${fieldValue(bean: partido, field: "local.nombre")}</g:link></td>						
							<td>${partido.golesLocal==null?"-":partido.golesLocal}</td>
							<td>VS</td>
							<td>${partido.golesVisitante==null?"-":partido.golesVisitante}</td>
							<td style="text-align: center"><g:link controller="equipo" action="show" id="${partido.visitante.id}">${fieldValue(bean: partido, field: "visitante.nombre")}</g:link>	</td>
					</tr>
				</g:each>
				<g:if test="${fecha.equipoLibre==null?false:true}">
				<tr align="center">
					<td colspan="8" align="center">	Libre: <g:select id="equipoLibre" name="equipoLibre.id" from="${torneoInstance.equipos}" optionKey="id" optionValue="nombre" required="" value="${fecha.equipoLibre?.id}" class="many-to-one" /></td>						
				</tr>
				</g:if>
				</tbody>
			</table>
	</g:each>
</div>
</g:if>
