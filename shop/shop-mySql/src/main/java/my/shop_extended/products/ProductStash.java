package my.shop_extended.products;

import java.util.LinkedList;
import java.util.List;

import my.shop_extended.products.impl.Banana;
import my.shop_extended.products.impl.Chair;
import my.shop_extended.products.impl.Milk;
import my.shop_extended.products.impl.NuclearPoweredAircraftCarrier;
import my.shop_extended.products.impl.Spoon;
import my.shop_extended.products.impl.Water;

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
