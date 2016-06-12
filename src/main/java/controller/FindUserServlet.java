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

public class FindUserServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("text/html");

        int userid = Integer.parseInt(req.getParameter("finduser"));

        Service s = Provider.getService();
        Patient founduser = s.getPatientforlist(userid);
        JSONArray jsonmetingen = founduser.getjsonmetingen();
        req.getSession().setAttribute("founduser", founduser);
        req.getSession().setAttribute("json", jsonmetingen);
        
        resp.sendRedirect(req.getContextPath() + "/loggedIn/Medewerker/Klantgegevens.jsp");
    }
}
