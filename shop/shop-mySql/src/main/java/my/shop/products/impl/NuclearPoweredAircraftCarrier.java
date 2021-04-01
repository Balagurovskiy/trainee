package my.shop.products.impl;

import my.shop.currency.Course;
import my.shop.currency.Currency;
import my.shop.products.AbstractItem;
import my.shop.products.Uneatable;

public class NuclearPoweredAircraftCarrier extends AbstractItem implements Uneatable{
 
	private static final long serialVersionUID = -3425793358075764598L;

	public NuclearPoweredAircraftCarrier() {
		super("NuclearPoweredAircraftCarrier", new Currency(12_000_000.27, Course.DOLLAR));
	}
	
}
