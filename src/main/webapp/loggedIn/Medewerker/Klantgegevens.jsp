<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Pijnmeting" %>
<%@ page import="model.User" %>
<%@ page import="model.Patient" %>
<%@ page import="model.Provider" %>
<%@ page import="model.Service" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="org.json.simple.JSONArray" %>


<%  Patient p = (Patient)request.getSession().getAttribute("founduser"); 
    List<Pijnmeting> metingen = p.getMijnpijnmetingen();
	User u = (User)request.getSession().getAttribute("loggedUser");
	Service s = Provider.getService();
	JSONArray jm = (JSONArray)request.getSession().getAttribute("json");
				%>
<html>
<head>
    <meta charset="utf-8" />
    	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
    	<meta name="viewport" content="width=device-width, initial-scale=1" />
    	<title>pijnmetingen SteniaFysio</title>

		<!--			imports				-->
		<link rel="stylesheet" type="text/css" href="../../bootstrap-3.3.6-dist/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="../../styles/Klantgegevens.css" />
		<script src="amcharts/amcharts.js" type="text/javascript"></script>
		<script src="amcharts/serial.js" type="text/javascript"></script>
	</head>
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top" id="mainnav">
			<div class="navbar-header">
  				<ul class="nav nav-pills">
				  <li role="presentation" ><a href="../../Home.jsp"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
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
				  <li role="presentation" class="active"><a href="Klantenlijst.jsp">KlantenLijst</a></li>
				</ul>
			</div>
		</nav>

		<div id="block">
			<h1>KlantenGegevens</h1>
			<a href="Klantenlijst.jsp" class="btn backbutton"><span class="glyphicon glyphicon-chevron-left"></span>Back</a>
			<a href="Wijzigklantgegevens.jsp" class="btn btn-primary">Klantgegevens wijzigen <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span></a>
			<table class="table table-condensed">
				<thead>
					<tr>
						<td>IDnummer</td>
						<td>Voornaam</td>
						<td>Achternaam</td>
						<td>Woonplaats</td>
						<td>Postcode</td>
						<td>Huisnummer</td>
						<td>Toevoeging</td>
						<td>Geboortedatum</td>
						<td>InschrijfDatum</td>
						<td>Email</td>
						<td>Telefoonnummer</td>
					</tr>
				</thead>
				<tbody>
                    <tr>
    					<td><%= p.getID()%></td>
    					<td><%= p.getVoornaam()%></input></td> 
   						<td><%= p.getAchternaam()%></td>
   						<td><%= p.getWoonplaats()%></td>
						<td><%= p.getPostcode()%></td>
						<td><%= p.getHuisnummer()%></td>
						<td><%= p.getToevoeging()%></td>
						<td><%= p.getGeboortedatum()%></td>
						<td><%= p.getInschrijfdatum()%></td>
						<td><%= p.getEmail()%></td>
						<td><%= p.getTelefoonnummer()%></td>
  					</tr>
                 </tbody>
            </table>
            <h1>Pijnmetingen: </h2>
            <table class="table table-striped table-condensed">
            	<thead>
            		<tr>
            			<td>Datum</td>
						<td>Tijd</td>
						<td>Hoeveelheid</td>
						<td>Locatie</td>
						<td>Opmerking</td>
            		</tr>
            	</thead>
            	<tbody>
            	<% for (Pijnmeting meting : metingen) { %>
            		<tr>
            			<td><%= meting.getDatum()%></td>
    					<td><%= meting.getTijd()%></td> 
   						<td><%= meting.getHoeveelheid()%></td>
   						<td><%= meting.getLocatie()%></td>
   						<td><%= meting.getOpmerking()%></td>
            		</tr>            	
            	<%}%>
            	</tbody>
            </table>
            <div id="chartdiv" style="width: 640px; height: 400px;"></div>
            <script type="text/javascript">
            var jm = <%= jm%>;
            AmCharts.ready(function() {
            	// chart code will go here
            	var chart = new AmCharts.AmSerialChart();
            	chart.dataProvider = jm;
            	chart.categoryField = "Date";
            	var graph = new AmCharts.AmGraph();
            	graph.valueField = "quantity";
            	graph.type = "line";
            	graph.bullet = "round";
            	graph.lineColor = "#8d1cc6";
            	chart.addGraph(graph);
            	chart.write('chartdiv');
            });
            </script>
            
		</div>
		<div id="copyright"><p>Copyright ©2016 - Stenia Fysio</p></div>          

</body>
</html>
