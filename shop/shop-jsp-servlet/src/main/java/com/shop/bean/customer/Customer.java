package com.shop.bean.customer;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.shop.MappedEntity;
import com.shop.bean.currency.CurrencyEntity;
import com.shop.bean.orders.Bucket;
import com.shop.bean.orders.Order;

@Entity
@Table(name = "customers")
public class Customer extends MappedEntity{
	
	@Column
	private double cash;
    @ManyToOne
    @JoinColumn(name="currencyId", nullable=false)
	private CurrencyEntity currency;
	@Column
	private String pass;
	
	@OneToMany(mappedBy = "customer", orphanRemoval = true)
	private List<Bucket> bucket;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Order> orders;
	
	public double getCash() {
		return cash;
	}
	public void setCash(double cash) {
		this.cash = cash;
	}
	public CurrencyEntity getCurrency() {
		return currency;
	}
	public void setCurrency(CurrencyEntity currency) {
		this.currency = currency;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public List<Bucket> getBucket() {
		return bucket;
	}
	public void setBucket(List<Bucket> bucket) {
		this.bucket = bucket;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
}
