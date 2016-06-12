<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Pijnmeting" %>
<%@ page import="model.User" %>
<%@ page import="model.Patient" %>
<%@ page import="model.Provider" %>
<%@ page import="model.Service" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <meta charset="utf-8" />
    	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
    	<meta name="viewport" content="width=device-width, initial-scale=1" />
    	<title>pijnmetingen SteniaFysio</title>

		<!--			imports				-->
		<link rel="stylesheet" type="text/css" href="../../bootstrap-3.3.6-dist/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="../../styles/Pijnmetingen.css" />
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
				  <li role="presentation" class="active"><a href="Pijnmetingen.jsp">Pijn Signaleren</a></li>
				  <li role="presentation"><a href="vraagstellen.html">Vraag Stellen</a></li>
				</ul>
			</div>
		</nav>

		<div id="block">
			<h1>Uw Pijnsignalementen</h1>
			<a href="PijnSignaleren.html" class="btn btn-primary">Pijn Signaleren<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span></a>
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
			<%
                User u = (User)request.getSession().getAttribute("loggedUser");
				Patient p = Provider.getService().getPatient(u.getID());
				
                List<Pijnmeting> Pijnmetingen = p.getMijnpijnmetingen();

                for (Pijnmeting pm: Pijnmetingen){  %>
                    <tr>
    					<td><%= pm.getDatum()%></td>
    					<td><%= pm.getTijd()%></td> 
   						<td><%= pm.getHoeveelheid()%></td>
   						<td><%= pm.getLocatie()%></td>
   						<td><%= pm.getOpmerking()%></td>
  					</tr>
                    <% } %>
                 </tbody>
            </table>
		</div>
		<div id="copyright"><p>Copyright ©2016 - Stenia Fysio</p></div>          

</body>
</html>
