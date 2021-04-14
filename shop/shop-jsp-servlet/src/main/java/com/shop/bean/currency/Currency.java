package com.shop.bean.currency;

public class Currency {
	private int id;
	private double amount;
	private double koef;
	private String name;
	
	public Currency(int id, String name, double koef) {
		this.id = id;
		amount = 0.0;
		this.koef = koef;
		this.name = name;
	}
	public Currency(int id, String name, double amount, double koef) {
		this.id = id;
		this.amount = amount;
		this.koef = koef;
		this.name = name;
	}
  
	public double convertTo(double koef) {
		double multiplier = 1.0;
		
		if (Math.abs(this.koef - koef) > 0.001) {
			multiplier = koef / this.koef;
		}
		return amount * multiplier;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getKoef() {
		return koef;
	}

	public void setKoef(double koef) {
		this.koef = koef;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
 
	
}
