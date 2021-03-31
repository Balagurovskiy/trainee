package my.shop.products;

import java.util.LinkedList;
import java.util.List;

import my.shop.products.impl.Banana;
import my.shop.products.impl.Chair;
import my.shop.products.impl.Milk;
import my.shop.products.impl.NuclearPoweredAircraftCarrier;
import my.shop.products.impl.Spoon;
import my.shop.products.impl.Water;

public class ProductStash {
	private List<Product> stash;
	
	public ProductStash() {
		stash = new LinkedList<>();
		stash.add(new Water());
		stash.add(new Banana());
		stash.add(new Chair());
		stash.add(new Spoon());
		stash.add(new Milk());
		stash.add(new NuclearPoweredAircraftCarrier());
	}

	public List<Product> getStash() {
		return stash;
	}
}
