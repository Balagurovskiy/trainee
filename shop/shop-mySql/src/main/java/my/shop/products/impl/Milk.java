package my.shop.products.impl;

import java.time.LocalDate;
import java.util.Objects;

import my.shop.currency.Course;
import my.shop.currency.Currency;
import my.shop.products.AbstractItem;
import my.shop.products.CurrentDataStamp;
import my.shop.products.Eatable;

public class Milk extends AbstractItem implements Eatable{
 
	private static final long serialVersionUID = 2698566804048459504L;

	@CurrentDataStamp
	private LocalDate expirationDate;
	
	public Milk() {
		super("Milk", new Currency(2.33, Course.DOLLAR));
	}

	@Override
	public boolean expired() {
		if (Objects.isNull(expirationDate)) {
			return true;
		}
		return expirationDate.isBefore(LocalDate.now());
	}
}
