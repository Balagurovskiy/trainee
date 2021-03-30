package my.shop_structure.actions;

import java.util.Objects;

import my.shop_structure.bucket.Bucket;
import my.shop_structure.products.ProductStash;

public class MainMenu implements IAction {

	private IAction next;
	private boolean stop;
	
	private ProductStash stash;
	private Bucket bucket;
	
	public MainMenu(ProductStash stash, Bucket bucket) {
		this.stash = stash;
		this.bucket = bucket;
	}
	
	@Override
	public void createInterface() {
		System.out.println("------------------------------");
		System.out.println("\t SHOP MANAGMENT\n");
		System.out.println(" [1] product list;");
		System.out.println(" [2] bucket;");
		System.out.println(" [exit] close shop.");
		System.out.println("------------------------------");
	}

	@Override
	public void acceptRequest(String request) {
		if (request.equals("exit")) {
			this.stop = true;
		} else if (request.equals("1")){
			next = new ProductList(stash, bucket);
		} else if (request.equals("2")){
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
		return stop;
	}
}
