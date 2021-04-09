package com.shop.bean.orders;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.shop.bean.customer.CustomerEntity;
import com.shop.bean.products.ProductEntity;

@MappedSuperclass
public abstract class OrderBaseEntity {
	@Id
	private int id;
	@ManyToOne
    @JoinColumn(name="productId", nullable=false)
	private ProductEntity product;
	@ManyToOne
    @JoinColumn(name="customerId", nullable=false)
	private CustomerEntity customer;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ProductEntity getProduct() {
		return product;
	}
	public void setProduct(ProductEntity product) {
		this.product = product;
	}
	public CustomerEntity getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}
}
