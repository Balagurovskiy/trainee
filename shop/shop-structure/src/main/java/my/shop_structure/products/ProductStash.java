package my.shop_structure.products;

import java.util.LinkedList;
import java.util.List;

import my.shop_structure.products.impl.Banana;
import my.shop_structure.products.impl.Chair;
import my.shop_structure.products.impl.Spoon;
import my.shop_structure.products.impl.Water;

public class ProductStash {
	private List<Product> stash;
	
	public ProductStash() {
		stash = new LinkedList<>();
		stash.add(new Water());
		stash.add(new Banana());
		stash.add(new Chair());
		stash.add(new Spoon());
	}

	public List<Product> getStash() {
		return stash;
	}
}
