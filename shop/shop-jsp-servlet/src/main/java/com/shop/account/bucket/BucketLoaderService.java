package com.shop.account.bucket;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.bean.customer.Customer;
import com.shop.bean.orders.Order;

public class BucketLoaderService {
	public void sendBucketList(HttpServletRequest req, HttpServletResponse resp, Customer customer, RequestDispatcher requestDispatcher) {
		req.setAttribute("page_header", "INTERNET SHOP " + customer.getName() + "'s bucket.");
	    req.setAttribute("message", "Order Submition. Available cash : " + customer.getCash().getAmount() + " " +customer.getCash().getName() );
	   
	    List<Order> products = new BucketTransactionManager().getList(customer.getId());
	    req.setAttribute("bucket", products);
	    req.setAttribute("total", new BucketTransactionManager().totalPrice( products, customer.getCash() ));
	    try {
			requestDispatcher.forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
