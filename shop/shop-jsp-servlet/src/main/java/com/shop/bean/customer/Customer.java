package com.shop.bean.customer;

import com.shop.bean.currency.Currency;

public class Customer {
	private int id;
	private String name;
	private Currency cash;
	private String pass;
	
	public Customer(int id, String name, Currency cash) {
		this.name = name;
		this.cash = cash;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Currency getCash() {
		return cash;
	}

	public void setCash(Currency cash) {
		this.cash = cash;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
}
