package my.shop.customer;

import java.io.Serializable;

import my.shop.currency.Course;
import my.shop.currency.Wallet;
import my.shop.products.Product;

public class Customer implements Serializable{
	private static final long serialVersionUID = 5523470099968328470L;
	private Wallet wallet;
	private Warehouse warehouse;
	private final String name;
	private final long id;
	
	public Customer(String name, long id) {
		this.name = name;
		this.id = id;
		wallet = new Wallet();
		warehouse = new Warehouse();
	}
	
	public Customer(String name, long id, double startBalance) {
		this.name = name;
		this.id = id;
		wallet = new Wallet(startBalance, Course.POUND);
		warehouse = new Warehouse();
	}
	
	public boolean buy(Product product) {
		if (wallet.withdraw(product.price())) {
			warehouse.store(product);
			return true;
		}
		System.out.println("Transaction Aborted!");
		System.out.println(name + " haven't enough money to buy " + product.getName());
		return false;
	}
	
	public String getName() {
		return name;
	}
	public long getId() {
		return id;
	}
	public String balance() {
		return wallet.balance() + " " + wallet.course().getName();
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}
}
