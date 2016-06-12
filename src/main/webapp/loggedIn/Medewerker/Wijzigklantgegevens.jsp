<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Pijnmeting" %>
<%@ page import="model.User" %>
<%@ page import="model.Patient" %>
<%@ page import="model.Provider" %>
<%@ page import="model.Service" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>


<%  Patient p = (Patient)request.getSession().getAttribute("founduser"); 
    List<Pijnmeting> metingen = p.getMijnpijnmetingen();
	User u = (User)request.getSession().getAttribute("loggedUser");
	Service s = Provider.getService();
				%>
<html>
<head>
    <meta charset="utf-8" />
    	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
    	<meta name="viewport" content="width=device-width, initial-scale=1" />
    	<title>pijnmetingen SteniaFysio</title>

		<!--			imports				-->
		<link rel="stylesheet" type="text/css" href="../../bootstrap-3.3.6-dist/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="../../styles/Wijzigklantgegevens.css" />
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
			<h1>Wijzig KlantenGegevens</h1>
			<form action="WijzigKlantGegevens.do" method="post">
				<table class="table table-condensed">
					<thead>
						<tr>
							<td>IDnummer</td>
							<td>Voornaam</td>
							<td>Achternaam</td>
						</tr>
					</thead>
					<tbody>
	                    <tr>
	    					<td><%= p.getID()%></td>
	    					<td><%= p.getVoornaam()%></td> 
	   						<td><%= p.getAchternaam()%></td>
	  					</tr>
	  					<tr>
	  						
  							<td><input type="text" name="klantID" value="<%= p.getID()%>" required></td>
  							<td><input type="text" name="klantVoornaam" value="<%= p.getVoornaam()%>" required></td>
  							<td><input type="text" name="klantAchternaam" value="<%= p.getAchternaam()%>" required></td>
	  					</tr>
	                 </tbody>
	                 <thead>
	                 	<tr class="fillrow">
	                 		<td></td>
	                 		<td></td>
	                 		<td></td>
	                 		<td></td>
	                 	</tr>
	                 	<tr>
	                 		<td>Geboortedatum</td>
							<td>InschrijfDatum</td>
							<td>Email</td>
							<td>Telefoonnummer</td>
	                 	</tr>
	                 </thead>
	                 <tbody>
	                 	<tr>
	                 		<td><%= p.getGeboortedatum()%></td>
							<td><%= p.getInschrijfdatum()%></td>
							<td><%= p.getEmail()%></td>
							<td><%= p.getTelefoonnummer()%></td>
	                 	</tr>
	                 	<tr>
	                 		<td><input type="text" name="klantGeboortedatum" value="<%= p.getGeboortedatum()%>" required></td>
  							<td><input type="text" name="klantInschrijfdatum" value="<%= p.getInschrijfdatum()%>" required></td>
  							<td><input type="text" name="klantemail" value="<%= p.getEmail()%>" required></td>
  							<td><input type="text" name="klanttelefoonnummer" value="<%= p.getTelefoonnummer()%>" required></td>
	                 	</tr>
	                 </tbody>
	                  <thead>
	                 	<tr class="fillrow">
	                 		<td></td>
	                 		<td></td>
	                 		<td></td>
	                 		<td></td>
	                 	</tr>
	                 	<tr>
	                 		<td>Woonplaats</td>
							<td>Postcode</td>
							<td>Huisnummer</td>
							<td>Toevoeging</td>
	                 	</tr>
	                 </thead>
	                 <tbody>
	                 <tr>
	                 	<td><%= p.getWoonplaats()%></td>
						<td><%= p.getPostcode()%></td>
						<td><%= p.getHuisnummer()%></td>
						<td><%= p.getToevoeging()%></td>
	                 </tr>
	                 <tr>  							
	                 	<td><input type="text" name="klantWoonplaats" value="<%= p.getWoonplaats()%>" required></td>
  						<td><input type="text" name="klantPostcode" value="<%= p.getPostcode()%>" required></td>
  						<td><input type="text" name="klantHuisnummer" value="<%= p.getHuisnummer()%>" required></td>
  						<td><input type="text" name="klantToevoeging" value="<%= p.getToevoeging()%>" required></td>
	                 </tr>
	                 </tbody>
	            </table>
	            <input type="submit" value="submit" class="btn btn-warning">
	         </form>
	         <a href="Klantgegevens.jsp" class="btn backbutton"><span class="glyphicon glyphicon-chevron-left"></span>Back</a>
			<form action="VerwijderKlantGegevens.do" method="post">
				<input type="submit" value="Verwijder Klant" class="btn btn-danger">
			</form>		
		</div>
		<div id="copyright"><p>Copyright ©2016 - Stenia Fysio</p></div>          

</body>
</html>
