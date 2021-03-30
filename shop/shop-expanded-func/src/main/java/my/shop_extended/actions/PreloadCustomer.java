package my.shop_extended.actions;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import my.shop_extended.ByteLoader;
import my.shop_extended.customer.Customer;
import my.shop_extended.products.ProductStash;

public class PreloadCustomer  extends AbstractAction{
	
	private List<String> buckets;
	
	public PreloadCustomer(ProductStash stash, Customer customer) {
		super(stash, customer);
		buckets = Stream.of(new File(".").listFiles())
			      .filter(file -> !file.isDirectory())
			      .map(File::getName)
			      .filter(name -> name.contains(".bucket"))
			      .collect(Collectors.toList());
	}
	
	@Override
	public void createInterface() {
		System.out.println("------------------------------");
		System.out.println("\t SELECT WAREHOUSE OR MAKE NEW\n");
		IntStream
	      .range(0, buckets.size())
	      .forEach(i -> {
	    	  String file = buckets.get(i);
	    	  String name = buckets.get(i).substring(0, file.lastIndexOf("."));
	    	  System.out.println(i + ") " + name +";");
	      });
		System.out.println(" [...] name of warehouse or new name;");
		System.out.println(" [exit] close shop.");
		System.out.println("------------------------------");
	}

	@Override
	public void acceptRequest(String request) {
		
		switch(request) {
	        case "exit":
	        	this.stop = true;
	            break;
	        default:
	        	String newBucketFileName = request + ".bucket";
				if (buckets.contains(newBucketFileName)) {
					customer = (Customer) ByteLoader.read(newBucketFileName);
					System.out.println("Using existed bucket.");
				} else {
					customer = new Customer(request, 100_500.0);
					ByteLoader.write(newBucketFileName, customer);
					System.out.println("Created new bucket.");
				}
				next = new MainMenu(stash, customer);
	            break;
		}
		
	}
}
