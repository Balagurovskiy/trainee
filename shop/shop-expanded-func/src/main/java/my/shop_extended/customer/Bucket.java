package my.shop_extended.customer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import my.shop_extended.products.Product;

public class Bucket implements Serializable{
	private static final long serialVersionUID = -2426459057573230291L;
	private Map<Product, Integer> stash;
	
	public Bucket() {
		stash = new HashMap<>();
	}

	public Map<Product, Integer> getStash() {
		return stash;
	}

	public void put(Product product) {
		Integer inc = 1;
		if (stash.containsKey(product)) {
			inc = stash.get(product) + 1;
		} 
		stash.put(product, inc);
	}
	
	public void poll(Product product) {
		if (stash.containsKey(product)) {
			Integer count = stash.get(product) - 1;
			if (count <= 0) {
				stash.remove(product);
			} else {
				stash.replace(product, count);
			}
		} 
	}
}
