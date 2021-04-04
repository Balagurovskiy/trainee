package com.shop.bean.products;

import com.shop.bean.currency.Currency;

public class Product {
	private int id;
	private String name;
	private Currency price;
	private boolean eatable;
	
	public Product(int id, String name, Currency price, int eatable) {
		 this.eatable = (eatable == 1) ? true : false;
		 this.id = id;
		 this.name = name;
		 this.price = price;
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

	public Currency getPrice() {
		return price;
	}

	public void setPrice(Currency price) {
		this.price = price;
	}

	public boolean isEatable() {
		return eatable;
	}

	public void setEatable(boolean eatable) {
		this.eatable = eatable;
	}
	
}
