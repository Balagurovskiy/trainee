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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter @Setter @NoArgsConstructor
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
		
}
