package my.shop.actions;

import java.sql.Connection;

import my.shop.customer.Customer;
import my.shop.products.ProductStash;

public class MainMenu extends AbstractAction{
	private Connection connection;
	public MainMenu(ProductStash stash, Customer customer, Connection connection) {
		super(stash, customer);
		this.connection = connection;
	}

	@Override
	public void createInterface() {
		System.out.println("------------------------------");
		System.out.println("\t SHOP MANAGMENT\n");
		System.out.println(" bucket : " + customer.getName());
		System.out.println(" balance : " + customer.balance());
		System.out.println("- - - - - - - - - - - - - - - \n");
		System.out.println(" [1] product list;");
		System.out.println(" [2] bucket;");
		System.out.println(" [3] warehouse;");
		System.out.println(" [exit] close shop.");
		System.out.println("------------------------------");
	}

	@Override
	public void acceptRequest(String request) {
		switch(request) {
	        case "exit":
	        	this.stop = true;
	            break;
	        case "1":
	        	next = new ProductList(stash, customer, connection);
	            break;
	        case "2":
	        	next = new BucketManagment(stash, customer, connection);
				break;
	        case "3":
	        	next = new WareHouseManagment(stash, customer, connection);
				break;
		}
	}
}
