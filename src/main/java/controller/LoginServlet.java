package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Medewerker;
import model.Patient;
import model.Pijnmeting;
import model.Provider;
import model.User;

public class LoginServlet extends HttpServlet {
	private static Date now= null;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{

		int username = Integer.parseInt(req.getParameter("IDnummer"));
		String pass1 = req.getParameter("Password");
			
		Patient userpatient = Provider.getService().loginPatient(username, pass1);
		Medewerker usermedewerker = Provider.getService().loginMedewerker(username, pass1);
		
	
		
		if (userpatient != null && usermedewerker == null) {
			Cookie cook = new Cookie("gebruikersnaam", req.getParameter("username"));
			resp.addCookie(cook);
			
			req.getSession().setAttribute("loggedUser", userpatient);
			String type = userpatient.getClass().getSimpleName();
			System.out.println(type);
			req.getSession().setAttribute("typeUser", type);
			
			System.out.println("Welcome back, " + userpatient.getVoornaam() + "!");
			resp.sendRedirect(req.getContextPath() + "/loggedIn/Patient/Pijnmetingen.jsp");
		}
		else if(usermedewerker != null && userpatient == null) {
			Cookie cook = new Cookie("gebruikersnaam", req.getParameter("username"));
			resp.addCookie(cook);
			System.out.println("YES!");
			
			req.getSession().setAttribute("loggedUser", usermedewerker);
			String type = usermedewerker.getClass().getSimpleName();
			System.out.println(type);
			req.getSession().setAttribute("typeUser", type);
			
			System.out.println("Welcome back, " + usermedewerker.getVoornaam() + "!");
			resp.sendRedirect(req.getContextPath() + "/loggedIn/Medewerker/Klantenlijst.jsp");
		}
		
		else {
			System.out.println("No match found");
			req.getSession().setAttribute("loggedUser", null);
			req.getSession().setAttribute("typeUser", null);
			resp.sendRedirect(req.getContextPath() + "/login.html");
		}
	}
}

