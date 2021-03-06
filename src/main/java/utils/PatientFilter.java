package utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PatientFilter implements Filter{  
	  

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		String user = (String) httpRequest.getSession(true).getAttribute("typeUser");
		
		if (user != null && user.equals("Patient")) {
			 if(chain != null) {
				 chain.doFilter(req, resp);
			 }
		}else {
			HttpServletResponse httpResponse = (HttpServletResponse) resp;
			httpResponse.sendRedirect(req.getServletContext().getContextPath() + "/login.html");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
