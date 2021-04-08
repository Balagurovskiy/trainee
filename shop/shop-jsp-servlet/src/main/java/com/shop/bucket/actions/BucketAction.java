package com.shop.bucket.actions;

import javax.servlet.http.HttpServletRequest;

import com.shop.bean.customer.Customer;

public abstract class BucketAction {
	
	private String ACTION;
	protected Customer customer;
	
	protected BucketAction(String action) {
		this.ACTION = action;
	}
	
	public boolean equalsTo(String action) {
		return this.ACTION.equalsIgnoreCase(action);
	}
	public abstract String execute(HttpServletRequest req);
 
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
