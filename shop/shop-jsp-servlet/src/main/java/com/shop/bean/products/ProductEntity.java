package com.shop.bean.products;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.shop.bean.country.CountryEntity;
import com.shop.bean.currency.CurrencyEntity;

@Entity
@Table(name = "products")
public class ProductEntity {
	@Id
	private int id;
	@Column
	private String name;
	@Column
	private double price;
	@ManyToOne
    @JoinColumn(name="currencyId", nullable=false)
	private CurrencyEntity currency;
	
	@ManyToOne
	@JoinTable(name = "product_country", 
	    joinColumns = { @JoinColumn(name = "productId") }, 
	    inverseJoinColumns = { @JoinColumn(name = "countryId") })
	private CountryEntity country;
	
	@Column
	private int eatable;
	
	
	public CountryEntity getCountry() {
		return country;
	}
	public void setCountry(CountryEntity country) {
		this.country = country;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public CurrencyEntity getCurrency() {
		return currency;
	}
	public void setCurrency(CurrencyEntity currency) {
		this.currency = currency;
	}
	public int getEatable() {
		return eatable;
	}
	public void setEatable(int eatable) {
		this.eatable = eatable;
	}
	public boolean isEatable() {
		return (eatable == 1);
	}
	
}
