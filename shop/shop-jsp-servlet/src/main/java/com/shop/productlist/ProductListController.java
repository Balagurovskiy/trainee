package com.shop.productlist;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.bean.customer.Customer;

public class ProductListController extends HttpServlet {

	private static final long serialVersionUID = 2168803790406142484L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String destination = "WEB-INF/view/product_list.jsp";
		RequestDispatcher requestDispatcher = req.getRequestDispatcher(destination);

    	
    	req.setAttribute("products", new ProductListService().getList());
    	
        req.setAttribute("page_header", "INTERNET SHOP Products");
        req.setAttribute("message", "List of available producs to order:");
        
		requestDispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Customer customer = null;
		HttpSession session = req.getSession(false);
    	if (Objects.nonNull(session)) {
    		customer = (Customer) session.getAttribute("customer");
        	session.setMaxInactiveInterval(5 * 60);
    	}
    	
    	String productId = req.getParameter("ordered_cache");
    	req.setAttribute("ordered", true);
    	new ProductListService().putToTheBucket(customer.getId(), productId);
    	req.setAttribute("bucket_size", new ProductListService().getBucketSize(customer.getId()));
    	
		doGet(req, resp);
	}
}
