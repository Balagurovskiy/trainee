package my.shop.products;

import java.io.Serializable;

import my.shop.currency.Currency;

public interface Product extends Serializable{
	public String getName();
	public Currency price();
}
