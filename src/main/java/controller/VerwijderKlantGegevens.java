package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import model.Patient;
import model.Provider;
import model.Service;

public class VerwijderKlantGegevens extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("text/html");

        Patient klant = (Patient) req.getSession().getAttribute("founduser");
        Service s = Provider.getService();
        s.verwijderPatient(klant);
        
        resp.sendRedirect(req.getContextPath() + "/loggedIn/Medewerker/Klantenlijst.jsp");
    }
}
	

