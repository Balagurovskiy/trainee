package my.shop_extended.products.impl;

import my.shop_extended.currency.Course;
import my.shop_extended.currency.Currency;
import my.shop_extended.products.AbstractItem;
import my.shop_extended.products.Uneatable;

public final class Chair extends AbstractItem implements Uneatable{
 
	private static final long serialVersionUID = -37787666259110095L;

	public Chair() {
		super("Chair", new Currency(11.10, Course.DOLLAR));
	}
}
