package my.shop_structure.products.impl;

import my.shop_structure.products.AbstractItem;
import my.shop_structure.products.Eatable;

public final class Banana extends AbstractItem implements Eatable{
	public Banana() {
		super("Banana");
	}
}
