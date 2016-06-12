package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Medewerker;
import model.Patient;
import model.Provider;

public class LogoutServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		HttpSession session = req.getSession();
		System.out.println(session);

		
			if(session.getAttribute("typeUser").equals("Pati�nt")) {
				Provider.getService().goodbyePatient((Patient) session.getAttribute("loggedUser"));
			}else if (session.getAttribute("typeUser").equals("Medewerker")) {
				Provider.getService().goodbyeMedewerker((Medewerker) session.getAttribute("loggedUser"));
			}
			
			
			
			session.removeAttribute("loggedUser");
			session.removeAttribute("typeUser");
			session.invalidate();
			resp.sendRedirect(req.getContextPath() + "/login.html");
		    
		
	}

}