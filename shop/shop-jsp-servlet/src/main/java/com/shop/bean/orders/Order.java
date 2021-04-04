package com.shop.bean.orders;

import java.sql.Date;

import com.shop.bean.customer.Customer;
import com.shop.bean.products.Product;

public class Order {
	private int id;
	private Product product;
	private Customer customer;
	private Date date;
	private boolean processed;
	
	public Order(int id, Product product, Customer customer, Date date, int processed) {
		this.id = id;
		this.customer = customer;
		this.date = date;
		this.product = product;
		this.processed = (processed == 1) ? true : false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
}
