package com.shop.register;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterController extends HttpServlet {

	private static final long serialVersionUID = 4172273450116598396L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();
		String destination = "WEB-INF/view/register.jsp";
		RequestDispatcher requestDispatcher = req.getRequestDispatcher(destination);
        
		req.setAttribute("page_header", "New Customer in INTERNET SHOP!");
	    req.setAttribute("message", "Enter your name please:");
	        
        requestDispatcher.forward(req, resp);
    }
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String newName= req.getParameter("register_name");
        
        if (Objects.nonNull(newName) && !newName.isEmpty()) {
        	
        	HttpSession session = req.getSession(true);
        	
        	session.setAttribute("customer", new CustomerRegistrationService().createAndReturn(newName));
        	session.setMaxInactiveInterval(5 * 60);
        	
			resp.sendRedirect(req.getContextPath() + "/account");
			
		} else {
			req.setAttribute("error", "Please dont leave that field blank");
			req.setAttribute("hasError", true);
			doGet(req, resp);
		}
	}
}
