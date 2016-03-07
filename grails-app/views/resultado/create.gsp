<!DOCTYPE html>
<html>
	<head>
	<script type="text/javascript">
    function callAjax(){

			$.ajax({
			    url:"${g.createLink(controller:'airport',action:'getJson')}",
			    dataType: 'json',
			    data: {
			        iata: '.............',
			    },
			    success: function(data) {
			        alert(data)
			    },
			    error: function(request, status, error) {
			        alert(error)
			    },
			    complete: function() {
			    }
			});
    }

	</script>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'resultado.label', default: 'Resultado')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-resultado" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="create-resultado" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${resultadoInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${resultadoInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form url="[resource:resultadoInstance, action:'save']" >
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
