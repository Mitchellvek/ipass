package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Patient;
import model.Provider;
import model.Service;

public class VraagStellenServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("text/html");

        String vraag = req.getParameter("vraag");

        Service s = Provider.getService();
        Patient user = (Patient) req.getSession().getAttribute("loggedUser");
        s.verstuurvraag(vraag, user);
        //somehow following attributes can get reset, so we put the right values back
        req.getSession().setAttribute("loggedUser", user);
        req.getSession().setAttribute("typeUser", user.getClass().getSimpleName());
        resp.sendRedirect(req.getContextPath() + "/loggedIn/Patient/PijnSignaleren.html");
    }
}
