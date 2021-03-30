package my.shop_extended.actions;

import java.util.stream.IntStream;

import my.shop_extended.ByteLoader;
import my.shop_extended.currency.Currency;
import my.shop_extended.customer.Customer;
import my.shop_extended.products.Product;
import my.shop_extended.products.ProductStash;

public class ProductList extends AbstractAction{

 
	public ProductList(ProductStash stash, Customer customer) {
		super(stash, customer);
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
		System.out.println(" [...] add to the bucket.");
		System.out.println(" [bucket] view warehouse.");
		System.out.println(" [back] go to main manu.");
		System.out.println("------------------------------");
	}

	@Override
	public void acceptRequest(String request) {
		switch(request) {
	        case "back":
	        	next = new MainMenu(stash, customer);
	            break;
	        case "bucket":
	        	next = new BucketManagment(stash, customer);
	            break;
		}
		if (request.matches( "\\d+" )) {
			int intRequest = Integer.parseInt(request);
			if (intRequest < stash.getStash().size() && intRequest >= 0){
				Product p = stash.getStash().get(intRequest);
				System.out.println(p.getName() + " added to the bucket");
				customer.buy(p);
				ByteLoader.write(customer.getName() + ".bucket", customer);
			}
		}
	}
}
