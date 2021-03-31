package my.shop.repository;

import java.sql.Date;

import my.shop.products.Product;

public class OrderInfo {
	private final Product product;
	private final Date date;
	
	public OrderInfo(Product product, Date date2) {
		this.product = product;
		this.date = date2;
	}

	public Product getProduct() {
		return product;
	}

	public Date getDate() {
		return date;
	}
	
}
