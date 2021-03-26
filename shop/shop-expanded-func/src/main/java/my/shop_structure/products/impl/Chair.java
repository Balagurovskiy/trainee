package my.shop_structure.products.impl;

import my.shop_structure.products.AbstractItem;
import my.shop_structure.products.Uneatable;

public final class Chair extends AbstractItem implements Uneatable{
	public Chair() {
		super("Chair");
	}
}
