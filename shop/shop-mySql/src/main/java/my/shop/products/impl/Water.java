package my.shop.products.impl;

import java.time.LocalDate;
import java.util.Objects;

import my.shop.currency.Course;
import my.shop.currency.Currency;
import my.shop.products.AbstractItem;
import my.shop.products.CurrentDataStamp;
import my.shop.products.Eatable;

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

