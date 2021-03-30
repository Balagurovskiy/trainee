package my.shop_extended.products.impl;

import my.shop_extended.currency.Course;
import my.shop_extended.currency.Currency;
import my.shop_extended.products.AbstractItem;
import my.shop_extended.products.Uneatable;

public final class Spoon extends AbstractItem implements Uneatable{
 
	private static final long serialVersionUID = -4747275875336573370L;

	public Spoon() {
		super("Spoon", new Currency(1.77, Course.EURO));
	}
}
