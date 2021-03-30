package my.shop_extended.products.impl;

import java.time.LocalDate;
import java.util.Objects;

import my.shop_extended.currency.Course;
import my.shop_extended.currency.Currency;
import my.shop_extended.products.AbstractItem;
import my.shop_extended.products.Eatable;
import my.shop_extended.products.CurrentDataStamp;

public final class Water extends AbstractItem implements Eatable{
 
	private static final long serialVersionUID = 5161235437517807843L;

	@CurrentDataStamp
	private LocalDate expirationDate;
	
	public Water() {
		super("Water", new Currency(13.77, Course.HRYVNIA));
	}

	@Override
	public boolean expired() {
		if (Objects.isNull(expirationDate)) {
			return true;
		}
		return expirationDate.isBefore(LocalDate.now());
	}
}

