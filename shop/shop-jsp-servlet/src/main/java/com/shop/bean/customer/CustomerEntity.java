package com.shop.bean.customer;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.shop.bean.currency.CurrencyEntity;
import com.shop.bean.orders.BucketEntity;
import com.shop.bean.orders.OrderEntity;

@Entity
@Table(name = "customers")
public class CustomerEntity {
	@Id
	private int id;
	@Column
	private String name;
	@Column
	private double cash;
    @ManyToOne
    @JoinColumn(name="currencyId", nullable=false)
	private CurrencyEntity currency;
	@Column
	private String pass;
	
	@OneToMany(mappedBy = "customer")
	private List<BucketEntity> bucket;
	
	@OneToMany(mappedBy = "customer")
	private List<OrderEntity> orders;
	
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
	public List<BucketEntity> getBucket() {
		return bucket;
	}
	public void setBucket(List<BucketEntity> bucket) {
		this.bucket = bucket;
	}
	public List<OrderEntity> getOrders() {
		return orders;
	}
	public void setOrders(List<OrderEntity> orders) {
		this.orders = orders;
	}
	
}
