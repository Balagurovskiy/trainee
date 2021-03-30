package my.shop_structure.actions;

import java.util.Objects;
import java.util.stream.IntStream;

import my.shop_structure.bucket.Bucket;
import my.shop_structure.products.Product;
import my.shop_structure.products.ProductStash;

public class BucketManament implements IAction {

	private IAction next;
	
	private ProductStash stash;
	private Bucket bucket;
	
	public BucketManament(ProductStash stash, Bucket bucket) {
		this.stash = stash;
		this.bucket = bucket;
	}
	
	@Override
	public void createInterface() {
		System.out.println("------------------------------");
		System.out.println("\t BUCKET LIST\n");
		IntStream
	      .range(0, bucket.getStash().size())
	      .forEach(i -> {
	    	  System.out.println(i + ") " + bucket.getStash().get(i).getName() + ";");
	      });
		if ( bucket.getStash().isEmpty()) {
			System.out.println(" Bucket is EMPTY");
		}
		System.out.println("- - - - - - - - - - - - - - -");
		System.out.println(" [...] delete from bucket.");
		System.out.println(" [clear] remove all products.");
		System.out.println(" [back] go to main manu.");
		System.out.println("------------------------------");
	}

	@Override
	public void acceptRequest(String request) {
		if (request.matches( "\\d+" )) {
			int intRequest = Integer.parseInt(request);
			if (intRequest < bucket.getStash().size() && intRequest >= 0){
				
				Product p = bucket.getStash().get(intRequest);
				System.out.println(p.getName() + " deleted from the bucket");
				bucket.getStash().remove(p);
				
			}
		} else if (request.equals("back")) {
			next = new MainMenu(stash, bucket);
		} else if (request.equals("clear")) {
			bucket.getStash().clear();;
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
