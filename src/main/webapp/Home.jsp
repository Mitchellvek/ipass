<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%String u = (String)request.getSession().getAttribute("typeUser");%>
<html>
<head>
    <meta charset="utf-8" />
    	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
    	<meta name="viewport" content="width=device-width, initial-scale=1" />
    	<title>pijnmetingen SteniaFysio</title>

		<!--			imports				-->
		<link rel="stylesheet" type="text/css" href="bootstrap-3.3.6-dist/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="styles/Home.css" />
		<script src="subbar.js" type="text/javascript"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
		<script>
		$(document).ready(function(){
			var usertype = $("#info").attr("data-id");
			if (usertype === undefined || usertype == "null") {
				$("#subnav").hide();
				$("#uitloggen").hide();
			}else if (usertype === "Patiënt") {
				$("#klantenlijst").hide();
				$("#inloggen").hide();
			}else if (usertype == "Medewerker") {
				$("#pijnsignaleren").hide();
				$("#vraagstellen").hide();
				$("#inloggen").hide();
			}
		});
		
		</script>
	</head>
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top" id="mainnav">
			<div class="navbar-header">
  				<ul class="nav nav-pills">
				  <li role="presentation" class="active"><a href="#"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				  <li role="presentation" id="inloggen"><a href="login.html">Login</a></li>
				  <li role="presentation" id="uitloggen">
				  	<form class="form-horizontal" action="LogoutServlet.do" method="get" >
				  		<button type="submit" class="btn btn-success">Uitloggen</button>
				  	</form>
				  </li>
				</ul>
			</div>
		</nav>
		<nav class="navbar navbar-default navbar-fixed-top" id="subnav">
			<div class="navbar-header">
  				<ul class="nav nav-pills">
				  <li role="presentation" id="klantenlijst"><a href="loggedIn/Medewerker/Klantenlijst.jsp">KlantenLijst</a></li>
				  <li role="presentation" id="pijnsignaleren"><a href="loggedIn/Patient/Pijnmetingen.jsp">Pijn Signaleren</a></li>
				  <li role="presentation" id="vraagstellen"><a href="loggedIn/Patient/vraagstellen.html">Vraag Stellen</a></li>
				</ul>
			</div>
		</nav>

		<div id="block">
			<h1>Welkom bij Stenia Fysiotherapie</h1>
			<p>
				Prettig leven willen we allemaal. Dat kan als u kunt vertrouwen op uw eigen lichaam zonder dat u (pijn)klachten heeft.<br>
				Stenia Fysiotherapie helpt u als u bijvoorbeeld herstellende bent van een sportblessure, ziekenhuisopname of als u
				andere lichamelijke klachten ervaart.<br>
				Daarnaast kunt u bij ons terecht om ervoor te zorgen dat uw lichamelijk in conditie komt én blijft.<br>
				Met behulp van fysiotherapie kunnen uw (pijn)klachten verminderen, helemaal verdwijnen of stabiliseren.<br>
				We proberen samen met u, uw	lichamelijke gesteldheid zo te verbeteren dat een normale houding en beweging weer mogelijk zijn.<br>
				Of wij leren u zo goed mogelijk omgaan met eventuele blijvende beperkingen.<br>
			</p>
		</div>
		<div id="info" data-id="<%=u%>"></div>
		<div id="copyright"><p>Copyright ©2016 - Stenia Fysio</p></div>          

</body>
</html>
