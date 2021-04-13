package com.shop.bean.products;


import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.shop.MappedEntity;
import com.shop.bean.country.CountryEntity;
import com.shop.bean.currency.CurrencyEntity;


@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "eatable")
@Entity
@Table(name = "products")
public class Product extends MappedEntity{
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
	
	
	public CountryEntity getCountry() {
		return country;
	}
	public void setCountry(CountryEntity country) {
		this.country = country;
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
}
