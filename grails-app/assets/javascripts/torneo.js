function habilitarEditarFecha(fechaId){
	$("div[class~=fecha" + fechaId + "] .no-editable").hide();
	$("div[class~=fecha" + fechaId + "] .editable").show();
	$("div[class~=fecha" + fechaId + "]").addClass("editing");
}

function cancelEdition(fechaId){
	$("div[class~=fecha" + fechaId + "] .no-editable").show();
	$("div[class~=fecha" + fechaId + "] .editable").hide();
	$("div[class~=fecha" + fechaId + "]").removeClass("editing");
}

