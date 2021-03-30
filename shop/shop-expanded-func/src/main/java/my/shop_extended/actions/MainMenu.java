package my.shop_extended.actions;

import my.shop_extended.customer.Customer;
import my.shop_extended.products.ProductStash;

public class MainMenu extends AbstractAction{
	public MainMenu(ProductStash stash, Customer customer) {
		super(stash, customer);
	}

	@Override
	public void createInterface() {
		System.out.println("------------------------------");
		System.out.println("\t SHOP MANAGMENT\n");
		System.out.println(" bucket : " + customer.getName());
		System.out.println(" balance : " + customer.balance());
		System.out.println("- - - - - - - - - - - - - - - \n");
		System.out.println(" [1] product list;");
		System.out.println(" [2] warehouse;");
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
	        	next = new ProductList(stash, customer);
	            break;
	        case "2":
	        	next = new BucketManagment(stash, customer);
				break;
		}
	}
}
