package com.shop.account;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.bean.currency.Currency;
import com.shop.bean.customer.Customer;

public class AccountController extends HttpServlet {

	private static final long serialVersionUID = 8914646419982130161L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String destination = "WEB-INF/view/account.jsp";
		RequestDispatcher requestDispatcher = req.getRequestDispatcher(destination);
		
		Customer customer = null;

		HttpSession session = req.getSession();
    	if (Objects.nonNull(session)) {
    		customer = (Customer) session.getAttribute("customer");
        	session.setMaxInactiveInterval(5 * 60);
    	} 
    	if (Objects.isNull(customer)) {
    		String id = req.getParameter("customer_cache");
    		customer = new CustomerCollectService().getCustomerById(id);
    	}
    	
    	if (Objects.nonNull(customer)) {
        	req.setAttribute("customer", customer);
        	Currency curr = customer.getCash();
        	req.setAttribute("balance", curr.getAmount() + " " + curr.getName());
        	
            req.setAttribute("page_header", "INTERNET SHOP " + customer.getName() + "'s Account!");
            req.setAttribute("message", "Select from options below:");
            session.setAttribute("customer", customer);
            requestDispatcher.forward(req, resp);
    	} else {
    		resp.sendRedirect(req.getContextPath() + "/");
    	}
    }
}
