package my.shop_structure.bucket;

import java.util.LinkedList;
import java.util.List;

import my.shop_structure.products.Product;

public class Bucket {
	private List<Product> stash;
	
	public Bucket() {
		stash = new LinkedList<>();
	}

	public List<Product> getStash() {
		return stash;
	}

	public void setStash(Product p) {
		this.stash.add(p);
	}
	
}
