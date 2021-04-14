package com.shop.bean.customer;

import java.util.List;

import javax.persistence.EntityManager;

public interface CustomerRepository {
	public long count(String name);
	
	public void create(String name, double cash, int currencyId);
	
	public void updateCash(Customer customer, double amount);
	
	public Customer getByName(String name);
	
	public Customer getById(int id);
	
	public List<Customer> findAll();

	public EntityManager callManager();
	
	public Customer getAndRefresh(Customer customer) ;
}
