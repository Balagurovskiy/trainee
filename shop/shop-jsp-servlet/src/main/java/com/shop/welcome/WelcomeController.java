package com.shop.welcome;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class WelcomeController extends HttpServlet {
	
	private static final long serialVersionUID = -7757215982664550800L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
    	if (Objects.nonNull(session)) {
    		session.invalidate();
    	}
		String destination = "WEB-INF/view/welcome.jsp";
		RequestDispatcher requestDispatcher = req.getRequestDispatcher(destination);
		
        req.setAttribute("page_header", "Welcome to the INTERNET SHOP!");
        req.setAttribute("message", "Select registered customer or create new:");
              
        req.setAttribute("customers", new CostomerLoaderService().getAll());
        
        requestDispatcher.forward(req, resp);

    }
}
