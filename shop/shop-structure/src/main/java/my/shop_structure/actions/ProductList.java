package my.shop_structure.actions;

import java.util.Objects;
import java.util.stream.IntStream;

import my.shop_structure.bucket.Bucket;
import my.shop_structure.products.Product;
import my.shop_structure.products.ProductStash;

public class ProductList implements IAction {

	private IAction next;
	private ProductStash stash;
	private Bucket bucket;
	
	public ProductList(ProductStash stash, Bucket bucket) {
		this.stash = stash;
		this.bucket = bucket;
	}
	
	@Override
	public void createInterface() {
		System.out.println("------------------------------");
		System.out.println("\t PRODUCT LIST\n");
		IntStream
	      .range(0, stash.getStash().size())
	      .forEach(i -> {
	    	  System.out.println(i + ") " + stash.getStash().get(i).getName() + ";");
	      });
		System.out.println("- - - - - - - - - - - - - - -");
		System.out.println(" [...] add to the bucket.");
		System.out.println(" [bucket] view bucket.");
		System.out.println(" [back] go to main manu.");
		System.out.println("------------------------------");
	}

	@Override
	public void acceptRequest(String request) {
		if (request.matches( "\\d+" )) {
			int intRequest = Integer.parseInt(request);
			if (intRequest < stash.getStash().size() && intRequest >= 0){
				Product p = stash.getStash().get(intRequest);
				System.out.println(p.getName() + " added to the bucket");
				bucket.setStash(p);
			}
		} else if (request.equals("back")) {
			next = new MainMenu(stash, bucket);
		} else if (request.equals("bucket")) {
			next = new BucketManament(stash, bucket);
		}
	}

	@Override
	public boolean hasNext() {
		return Objects.nonNull(next);
	}

	@Override
	public IAction next() {
		return next;
	}

	@Override
	public boolean stop() {
		return false;
	}
}
