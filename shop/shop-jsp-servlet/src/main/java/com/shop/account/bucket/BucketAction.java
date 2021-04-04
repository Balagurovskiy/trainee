package com.shop.account.bucket;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.bean.customer.Customer;

public abstract class BucketAction {
	
	private final String ACTION;
	protected Customer customer;
	protected RequestDispatcher requestDispatcher;
	
	protected BucketAction(String action) {
		this.ACTION = action;
	}
	
	public boolean equalsTo(String action) {
		return this.ACTION.equalsIgnoreCase(action);
	}
	abstract void execute(HttpServletRequest req, HttpServletResponse resp);
 
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setRequestDispatcher(RequestDispatcher requestDispatcher) {
		this.requestDispatcher = requestDispatcher;
	}
}
