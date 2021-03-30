package my.shop_extended.customer;

import java.io.Serializable;

import my.shop_extended.currency.Course;
import my.shop_extended.currency.Wallet;
import my.shop_extended.products.Product;

public class Customer implements Serializable{
	private static final long serialVersionUID = 5523470099968328470L;
	private Wallet wallet;
	private Warehouse warehouse;
	private final String name;
	
	public Customer(String name) {
		this.name = name;
		wallet = new Wallet();
		warehouse = new Warehouse();
	}
	
	public Customer(String name, double startBalance) {
		this.name = name;
		wallet = new Wallet(startBalance, Course.POUND);
		warehouse = new Warehouse();
	}
	
	public void buy(Product product) {
		if (wallet.withdraw(product.price())) {
			warehouse.store(product);
		} else {
			System.out.println("Transaction Aborted!");
			System.out.println(name + " haven't enough money to buy " + product.getName());
		}
	}
	
	public String getName() {
		return name;
	}
	
	public String balance() {
		return wallet.balance() + " " + wallet.course().getName();
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}
}
