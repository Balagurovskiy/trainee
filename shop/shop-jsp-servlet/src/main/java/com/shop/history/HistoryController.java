package com.shop.history;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.bean.customer.Customer;

public class HistoryController extends HttpServlet {

	private static final long serialVersionUID = 649499693855800947L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String destination = "WEB-INF/view/history.jsp";
		RequestDispatcher requestDispatcher = req.getRequestDispatcher(destination);
    	
		Customer customer = null;
		HttpSession session = req.getSession(false);
    	if (Objects.nonNull(session)) {
    		customer = (Customer) session.getAttribute("customer");
        	session.setMaxInactiveInterval(5 * 60);
    	}
    	int customerId = Objects.nonNull(customer) ? customer.getId() : -1;
    	String customerName = Objects.nonNull(customer) ? customer.getName() : "-";
		req.setAttribute("history", new HIstoryService().getCustomerHistory(customerId));
    	
        req.setAttribute("page_header", "INTERNET SHOP " + customerName + "'s history");
        req.setAttribute("message", "List processed orders:");
        
		requestDispatcher.forward(req, resp);
	}
}
