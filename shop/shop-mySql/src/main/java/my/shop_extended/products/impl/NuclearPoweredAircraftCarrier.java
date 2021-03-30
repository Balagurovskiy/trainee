package my.shop_extended.products.impl;

import my.shop_extended.currency.Course;
import my.shop_extended.currency.Currency;
import my.shop_extended.products.AbstractItem;
import my.shop_extended.products.Uneatable;

public class NuclearPoweredAircraftCarrier extends AbstractItem implements Uneatable{
 
	private static final long serialVersionUID = -3425793358075764598L;

	public NuclearPoweredAircraftCarrier() {
		super("Nuclear-powered aircraft carrier", new Currency(12_000_000.27, Course.DOLLAR));
	}
	
}
