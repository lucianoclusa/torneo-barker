<%@ page import="ar.com.torneobarker.Resultado" %>
<%@ page import="ar.com.torneobarker.Jugador" %>


<div class="container-fluid">
  <div class="row">
    <div class="col-sm-12 col-md-12 col-xs-12 equipoContainer">
	  	<label for="equipoLocal">
		<h1>${partido.local.nombre}</h1>
		<input type="hidden" id="partidoId" name="partidoId" value="${partido.id}">	
		</label>
		<table >
			<thead>
					<tr>
						<th style="width: 20%"><g:message code="jugador.apellido.label" default="Jugador" /></th>
						<th style="width: 10%"><g:message code="goles.favor.label" default="GF" /></th>
						<th style="width: 10%"><g:message code="goles.contra.label" default="GC" /></th>
	 					<th style="width: 10%"><g:message code="tarjeta.amarilla.label" default="TA" /></th>
						<th style="width: 10%"><g:message code="tarjeta.roja.label" default="TR" /></th>
						<th style="width: 10%"><g:message code="cantidad.suspencion.label" default="Cant Susp." /></th>
						<th style="width: 30%"><g:message code="resultado.comentario.label" default="Comentario" /></th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${jugadoresLocal}" status="i" var="jugadorInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td><g:link controller="jugador" action="show" id="${jugadorInstance.jugador.id}">${fieldValue(bean: jugadorInstance, field: "jugador.apellido")}, ${fieldValue(bean: jugadorInstance, field: "jugador.nombre")}</g:link></td>
						<td><input type="number" id="golFavor_${jugadorInstance.jugador.id}" name="golFavor_${jugadorInstance.jugador.id}" min="0" max="99" value="${fieldValue(bean: jugadorInstance, field: "golesFavor")}"></td>
						<td><input type="number" id="golContra_${jugadorInstance.jugador.id}" name="golContra_${jugadorInstance.jugador.id}" min="0" max="99" value="${fieldValue(bean: jugadorInstance, field: "golesContra")}"></td>
						<td><input type="number" id="tarjetaAmarilla_${jugadorInstance.jugador.id}" name="tarjetaAmarilla_${jugadorInstance.jugador.id}" min="0" max="5" value="${fieldValue(bean: jugadorInstance, field: "tarjetasAmarillas")}"></td>
						<td><input type="number" id="tarjetaRoja_${jugadorInstance.jugador.id}" name="tarjetaRoja_${jugadorInstance.jugador.id}" min="0" max="5" value="${fieldValue(bean: jugadorInstance, field: "tarjetasRojas")}"></td>
						<td><input type="number" id="cantSuspension_${jugadorInstance.jugador.id}" name="cantSuspension_${jugadorInstance.jugador.id}" min="0" max="99" value="${fieldValue(bean: jugadorInstance, field: "cantSusp")}"></td>
						<td><input type="text" id="descripcion_${jugadorInstance.jugador.id}" name="descripcion_${jugadorInstance.jugador.id}" class="resize: both" value="${fieldValue(bean: jugadorInstance, field: "comentario")}"></td>
					</tr>
				</g:each>
					</tbody>
		</table>


	</div>
    <div class="col-sm-12 col-md-12 col-xs-12 equipoContainer">		
   	<label for="equipoVisitante">
		<h1>${partido.visitante.nombre}</h1>
		
	</label>
    <table>
			<thead>
					<tr>
						<th style="width: 20%"><g:message code="jugador.apellido.label" default="Jugador" /></th>
						<th style="width: 10%"><g:message code="goles.favor.label" default="GF" /></th>
						<th style="width: 10%"><g:message code="goles.contra.label" default="GC" /></th>
	 					<th style="width: 10%"><g:message code="tarjeta.amarilla.label" default="TA" /></th>
						<th style="width: 10%"><g:message code="tarjeta.roja.label" default="TR" /></th>
						<th style="width: 10%"><g:message code="cantidad.suspencion.label" default="Cant Susp." /></th>
						<th style="width: 30%"><g:message code="resultado.comentario.label" default="Comentario" /></th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${jugadoresVisitante}" status="i" var="jugadorInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td><g:link controller="jugador" action="show" id="${jugadorInstance.jugador.id}">${fieldValue(bean: jugadorInstance, field: "jugador.apellido")}, ${fieldValue(bean: jugadorInstance, field: "jugador.nombre")}</g:link></td>
						<td><input type="number" id="golFavor_${jugadorInstance.jugador.id}" name="golFavor_${jugadorInstance.jugador.id}" min="0" max="99" value="${fieldValue(bean: jugadorInstance, field: "golesFavor")}"></td>
						<td><input type="number" id="golContra_${jugadorInstance.jugador.id}" name="golContra_${jugadorInstance.jugador.id}" min="0" max="99" value="${fieldValue(bean: jugadorInstance, field: "golesContra")}"></td>
						<td><input type="number" id="tarjetaAmarilla_${jugadorInstance.jugador.id}" name="tarjetaAmarilla_${jugadorInstance.jugador.id}" min="0" max="5" value="${fieldValue(bean: jugadorInstance, field: "tarjetasAmarillas")}"></td>
						<td><input type="number" id="tarjetaRoja_${jugadorInstance.jugador.id}" name="tarjetaRoja_${jugadorInstance.jugador.id}" min="0" max="5" value="${fieldValue(bean: jugadorInstance, field: "tarjetasRojas")}"></td>
						<td><input type="number" id="cantSuspension_${jugadorInstance.jugador.id}" name="cantSuspension_${jugadorInstance.jugador.id}" min="0" max="99" value="${fieldValue(bean: jugadorInstance, field: "cantSusp")}"></td>
						<td><input type="text" id="descripcion_${jugadorInstance.jugador.id}" name="descripcion_${jugadorInstance.jugador.id}" class="resize: both" value="${fieldValue(bean: jugadorInstance, field: "comentario")}"></td>
					</tr>
				</g:each>
					</tbody>
		</table>
</div>
  </div>
</div>
