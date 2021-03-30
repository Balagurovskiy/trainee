package my.shop_extended.products;

import java.io.Serializable;

import my.shop_extended.currency.Currency;

public interface Product extends Serializable{
	public String getName();
	public Currency price();
}
