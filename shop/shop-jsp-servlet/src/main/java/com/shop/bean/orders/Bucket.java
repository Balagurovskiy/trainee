package com.shop.bean.orders;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.shop.bean.customer.Customer;
import com.shop.bean.products.Product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "bucket")
@Getter @Setter @NoArgsConstructor
public class Bucket{
	@Id
	private int id;
	
	@ManyToOne
    @JoinColumn(name="productId", nullable=false)
	private Product product;
	
	@ManyToOne
    @JoinColumn(name="customerId")
	private Customer customer;
}
