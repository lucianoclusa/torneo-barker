<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'equipo.label', default: 'Equipo')}" />
		<title>Cargar equipo desde Excel</title>
	</head>
	<body>
		<a href="#create-equipo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="home" action="index"><g:message code="default.home.label"/></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="showLoadFromExcel">Cargar desde EXCEL</g:link></li>
			</ul>
		</div>
		<div id="create-equipo" class="content scaffold-create" role="main">
			<h1>Cargar desde EXCEL</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${equipoInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${equipoInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="loadFromExcel" method="post" enctype="multipart/form-data" >
				<fieldset class="form">
					<input class="create" type='file' name="cfile">
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.load.label', default: 'Cargar')}" />
				</fieldset>
			</g:form>
			
			
		</div>
	</body>
</html>
