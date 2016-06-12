package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Patient;
import model.Provider;
import model.Service;

public class WijzigKlantGegevens extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("text/html");

        String klantVoornaam = req.getParameter("klantVoornaam");
        String klantAchternaam = req.getParameter("klantAchternaam");
        String klantWoonplaats = req.getParameter("klantWoonplaats");
        String klantPostcode = req.getParameter("klantPostcode");
        String klanthuisnummer = req.getParameter("klantHuisnummer");
        
        String klantToevoeging = req.getParameter("klantToevoeging");
        String klantGeboortedatum = req.getParameter("klantGeboortedatum");
        String klantInschrijfdatum = req.getParameter("klantInschrijfdatum");
        String klantemail = req.getParameter("klantemail");
        String klantTelefoonnummer = req.getParameter("klanttelefoonnummer");
        
        int klanttelefoonnummer = Integer.parseInt(klantTelefoonnummer);
        int klantHuisnummer = Integer.parseInt(klanthuisnummer);
        
        Patient klant = (Patient) req.getSession().getAttribute("founduser");
        
        Provider.getService().wijzigklantgegevens(klant, klantVoornaam, klantAchternaam, klantWoonplaats, klantPostcode, klantHuisnummer, klantToevoeging, klantGeboortedatum, klantInschrijfdatum, klantemail, klanttelefoonnummer);
        
        resp.sendRedirect(req.getContextPath() + "/loggedIn/Medewerker/Klantgegevens.jsp");
    }
}
