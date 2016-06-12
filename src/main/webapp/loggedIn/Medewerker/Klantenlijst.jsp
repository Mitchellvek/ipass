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
		<link rel="stylesheet" type="text/css" href="../../styles/Klantenlijst.css" />
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
			<h1>KlantenLijst</h1>
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
					<form action="FindUserServlet.do" method="post">
			<%
                User u = (User)request.getSession().getAttribute("loggedUser");
				Service s = Provider.getService();
				
                List<Patient> patienten = s.getKlantenlijst();

                for (Patient p: patienten){  
                	int ID = p.getID();%>
                    <tr>
    					<td><input type="submit" name="finduser" value="<%= ID%>"></input></td>
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
                    <% } %>
                    </form>
                 </tbody>
            </table>
            <script>
            function goDoSomething(d){
                alert(d.getAttribute("data-id"));
            }
            </script>
		</div>
		<div id="copyright"><p>Copyright ©2016 - Stenia Fysio</p></div>          

</body>
</html>
