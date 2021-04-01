package my.shop.products.impl;

import my.shop.currency.Course;
import my.shop.currency.Currency;
import my.shop.products.AbstractItem;
import my.shop.products.Uneatable;

public final class Chair extends AbstractItem implements Uneatable{
 
	private static final long serialVersionUID = -37787666259110095L;

	public Chair() {
		super("Chair", new Currency(11.10, Course.DOLLAR));
	}
}
