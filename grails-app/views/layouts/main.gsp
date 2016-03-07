<%@ page import="ar.com.torneobarker.Anio"%>
<%@ page import="ar.com.torneobarker.Temporada"%>
<%@ page import="ar.com.torneobarker.Categoria"%>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title><g:layoutTitle default="Torneo Barker" /></title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}"
	type="image/x-icon">
<link rel="apple-touch-icon"
	href="${assetPath(src: 'apple-touch-icon.png')}">
<link rel="apple-touch-icon" sizes="114x114"
	href="${assetPath(src: 'apple-touch-icon-retina.png')}">

<!-- Bootstrap-->
<!-- Latest compiled and minified CSS -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> -->
<!-- Optional theme -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"> -->
<!-- Latest compiled and minified JavaScript -->
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->
<!-- FIN Bootstrap-->
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>

<asset:stylesheet src="application.css" />
<asset:javascript src="application.js" />
<asset:stylesheet src="styles.css" />
<asset:javascript src="script.js" />
<g:layoutHead />

</head>
<body>
	<header>
		<div id="grailsLogo" role="banner">
			<div id="imagen" style="width: 80%;display: inline;"><asset:image width="80px" height="80px" id="logoImg" src="logo.jpg" alt="Grails"/></div>

			<div class="login no-print" style="width: 20%;display: inline;">
				<sec:ifLoggedIn>
					<g:link class="login" controller="logout">Logout</g:link>
				</sec:ifLoggedIn>
				<sec:ifNotLoggedIn>
					<g:link class="login" controller="login" action="index">Login</g:link>
				</sec:ifNotLoggedIn>
			</div>
		</div>

	</header>
	<div>
	<script type="text/javascript">
	$(function() {
		$('#torneoMenu').click(function() {
			sessionStorage.tabSelected = "torneo";
		});
		$('#equipoMenu').click(function() {
			sessionStorage.tabSelected = "equipo";
		});
		$('#jugadorMenu').click(function() {
			sessionStorage.tabSelected = "jugador";
		});
		$('#suspensionMenu').click(function() {
			sessionStorage.tabSelected = "suspension";
		});

		if(sessionStorage.tabSelected == "suspension"){
			$('#suspensionMenu').addClass('clicked');
		}else if(sessionStorage.tabSelected == "jugador"){
			$('#jugadorMenu').addClass('clicked');
		}else if(sessionStorage.tabSelected == "equipo"){
			$('#equipoMenu').addClass('clicked');
		}else {
			$('#torneoMenu').addClass('clicked');
		}
		
	});
	</script>
	
	<div id='cssmenu' class="no-print">
	<ul>
	   <li><g:link elementId="torneoMenu" controller="torneo" action="index"><span>Torneos</span></g:link></li>
	   <li><g:link elementId="equipoMenu" controller="equipo" action="index"><span>Equipos</span></g:link></li>
	   <li><g:link elementId="jugadorMenu" controller="jugador" action="index"><span>Jugadores</span></g:link></li>
	   <li><g:link elementId="suspensionMenu" controller="suspension" action="index"><span>Suspensiones</span></g:link></li>
	</ul>
	</div>

	<div id='cssbody'>
	<g:layoutBody/>
	</div>
	</div>
	<div class="footer no-print" role="contentinfo"></div>
	<div id="spinner" class="spinner" style="display: none;">
		<g:message code="spinner.alt" default="Loading&hellip;" />
	</div>
</body>
</html>
