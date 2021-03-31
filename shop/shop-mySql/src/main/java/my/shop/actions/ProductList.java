package my.shop.actions;

import java.sql.Connection;
import java.util.stream.IntStream;

import my.shop.currency.Currency;
import my.shop.customer.Customer;
import my.shop.products.Product;
import my.shop.products.ProductStash;
import my.shop.repository.OrderDaoJdbc;

public class ProductList extends AbstractAction{
 
	private Connection connection;
	public ProductList(ProductStash stash, Customer customer, Connection connection) {
		super(stash, customer);
		this.connection = connection;
	}

	@Override
	public void createInterface() {
		System.out.println("------------------------------");
		System.out.println("\t PRODUCT LIST\n");
		System.out.println(" bucket : " + customer.getName());
		System.out.println(" balance : " + customer.balance());
		System.out.println("- - - - - - - - - - - - - - - \n");
		IntStream
	      .range(0, stash.getStash().size())
	      .forEach(i -> {
	    	  Product product = stash.getStash().get(i);
	    	  Currency price = product.price();
	    	  String item = product.getName() + " " + price.getAmount() + " " + price.getCourse().getName();
	    	  System.out.println(i + ") " + item +";");
	      });
		System.out.println("- - - - - - - - - - - - - - -");
		System.out.println(" [...] add to the bucket by number.");
		System.out.println(" [bucket] view order.");
		System.out.println(" [back] go to main manu.");
		System.out.println("------------------------------");
	}

	@Override
	public void acceptRequest(String request) {
		switch(request) {
	        case "back":
	        	next = new MainMenu(stash, customer, connection);
	            break;
	        case "bucket":
	        	next = new BucketManagment(stash, customer, connection);
	            break;
		}
		if (request.matches( "\\d+" )) {
			int intRequest = Integer.parseInt(request);
			if (intRequest < stash.getStash().size() && intRequest >= 0){
				Product p = stash.getStash().get(intRequest);
				System.out.println(p.getName() + " added to the bucket");
				OrderDaoJdbc dao = new OrderDaoJdbc(connection);
				dao.insertProductToOrder(customer.getId(), p);
			}
		}
	}
}
