package my.shop.products.impl;

import my.shop.currency.Course;
import my.shop.currency.Currency;
import my.shop.products.AbstractItem;
import my.shop.products.Uneatable;

public final class Spoon extends AbstractItem implements Uneatable{
 
	private static final long serialVersionUID = -4747275875336573370L;

	public Spoon() {
		super("Spoon", new Currency(1.77, Course.EURO));
	}
}
