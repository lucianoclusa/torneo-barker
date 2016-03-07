<%@ page import="ar.com.torneobarker.Suspension" %>

<div class="fieldcontain ${hasErrors(bean: suspensionInstance, field: 'fechaSancion', 'error')} required">
	<label for="fechaSancion">
		<g:message code="torneo.fechaSancion.label" default="Fecha Sanción" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaSancion" precision="day"  value="${suspensionInstance?.fechaSancion}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: suspensionInstance, field: 'jugador', 'error')} required">
	<label for="jugador">
		<g:message code="suspension.jugador.label" default="Jugador" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="jugador" name="jugador.id" from="${ar.com.torneobarker.Jugador.list()}" optionKey="id" required="" value="${suspensionInstance?.jugador?.id}" class="many-to-one" onchange="jugadorChanged(this.value)" />

</div>
<script>
    function jugadorChanged(jugadorId) {
        jQuery.ajax({
            type:'GET',
            dataType: "html",
            url:"${createLink(controller: 'suspension', action: 'jugadorChanged')}" + "?jugadorId=" + jugadorId,
            success:function(data,textStatus){
                jQuery('#equiposContainer').html(data);
                },
            error:function(XMLHttpRequest,textStatus,errorThrown){}});
    }
</script>
<div class="fieldcontain ${hasErrors(bean: suspensionInstance, field: 'equipo', 'error')}">
	<label for="equipo">
		<g:message code="suspension.equipo.label" default="Equipo" />
	</label>
	<span id="equiposContainer">
	<g:select id="equipo" name="equipo.id" from="${ar.com.torneobarker.Equipo.findAll("from Equipo e where ? in elements(e.jugadores)", [ar.com.torneobarker.Jugador.list().get(0)])}" optionKey="id" value="${suspensionInstance?.equipo?.id}" class="many-to-one"  onchange="equipoChanged(this.value)" noSelection="['':'-Sin equipo-']"/>
	</span>
</div>
<script>
    function equipoChanged(equipoId) {
        jQuery.ajax({
            type:'GET',
            url:"${createLink(controller: 'suspension', action: 'equipoChanged')}" + "?equipoId=" + equipoId,
            success:function(data,textStatus){
                jQuery('#torneosContainer').html(data);
                },
            error:function(XMLHttpRequest,textStatus,errorThrown){}});
    }
</script>
<div class="fieldcontain ${hasErrors(bean: suspensionInstance, field: 'torneo', 'error')}">
	<label for="torneo">
		<g:message code="suspension.torneo.label" default="Torneo" />
	</label>
	<span id="torneosContainer">
	<g:select id="torneo" name="torneo.id" from="${ar.com.torneobarker.Torneo.list()}" optionKey="id" value="${suspensionInstance?.torneo?.id}" class="many-to-one" onchange="torneoChanged(this.value)" noSelection="['':'-Sin equipo-']"/>
	</span>
</div>
<script>
    function torneoChanged(torneoId) {
        jQuery.ajax({
            type:'GET',
            url:"${createLink(controller: 'suspension', action: 'torneoChanged')}" + "?torneoId=" + torneoId,
            success:function(data,textStatus){
                jQuery('#fechasContainer').html(data);
                },
            error:function(XMLHttpRequest,textStatus,errorThrown){}});
    }
</script>
<div class="fieldcontain ${hasErrors(bean: suspensionInstance, field: 'fecha', 'error')}">
	<label for="torneo">
		<g:message code="suspension.fecha.label" default="Fecha" />
	</label>
	<span id="fechasContainer">
		<g:select id="fecha" name="fecha.id" from="${ ar.com.torneobarker.Fecha.findAllByTorneo(ar.com.torneobarker.Torneo.list().get(0))}" optionKey="id" value="${suspensionInstance?.fecha}" onchange="categoryChanged(this.value)" class="many-to-one" noSelection="['':'-Sin equipo-']"/>
	</span>
</div>
<div class="fieldcontain ${hasErrors(bean: suspensionInstance, field: 'cantFechas', 'error')} ">
	<label for="cantFechas">
		<g:message code="suspension.cantFechas.label" default="Cant Fechas" />
		
	</label>
	<g:field name="cantFechas" type="number" value="${suspensionInstance.cantFechas}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: suspensionInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="suspension.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textArea name="descripcion" value="${suspensionInstance?.descripcion}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: suspensionInstance, field: 'esIndefinido', 'error')} ">
	<label for="esIndefinido">
		<g:message code="suspension.esPermanente.label" default="Permanente" />
		
	</label>
	<g:checkBox name="esIndefinido" value="${suspensionInstance?.esIndefinido}" />

</div>

<div class="fieldcontain ${hasErrors(bean: suspensionInstance, field: 'esProvisoria', 'error')} ">
	<label for="esProvisoria">
		<g:message code="suspension.esProvisoria.label" default="Provisoria" />
		
	</label>
	<g:checkBox name="esProvisoria" value="${suspensionInstance?.esProvisoria}" />

</div>

<div class="fieldcontain ${hasErrors(bean: suspensionInstance, field: 'estaActiva', 'error')} ">
	<label for="estaActiva">
		<g:message code="suspension.estaActiva.label" default="Esta Activa" />
		
	</label>
	<g:checkBox name="estaActiva" value="${suspensionInstance?.estaActiva}" />

</div>

