package controller;

import model.Patient;
import model.Pijnmeting;
import model.Provider;
import model.Service;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PijnSignalerenServlet extends HttpServlet{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("text/html");

        int hoeveelheid = Integer.parseInt(req.getParameter("hoeveelheid"));
        String locatie = req.getParameter("locatie");
        String opmerking = req.getParameter("Opmerking");

        Service s = Provider.getService();
        Patient user = (Patient) req.getSession().getAttribute("loggedUser");
        //somehow following attributes can get reset
        req.getSession().setAttribute("loggedUser", user);
        req.getSession().setAttribute("typeUser", user.getClass().getSimpleName());
        s.addPijnmetingforPatient(hoeveelheid, locatie, opmerking, user);
        
        resp.sendRedirect(req.getContextPath() + "/loggedIn/Patient/Pijnmetingen.jsp");
    }
}

