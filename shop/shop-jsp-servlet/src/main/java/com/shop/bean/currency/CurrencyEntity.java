package com.shop.bean.currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "currency")
@Getter @Setter @NoArgsConstructor
public class CurrencyEntity {
	@Id
	private int id;
	@Column 
	private double koef;
	@Column 
	private String name;
  
	public double convertTo(double amount, double koef) {
		double multiplier = 1.0;
		
		if (Math.abs(this.koef - koef) > 0.001) {
			multiplier = koef / this.koef;
		}
		return amount * multiplier;
	}
}
