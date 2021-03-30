package my.shop_extended.products;

import java.io.Serializable;
import java.util.Objects;

import my.shop_extended.currency.Currency;

public abstract class AbstractItem implements Serializable{

	private static final long serialVersionUID = -3521403986390773674L;
	protected final String name;
	protected final Currency currency;
	
	protected AbstractItem(String name, Currency currency) {
		this.name = name;
		this.currency = currency;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Currency price() {
		return currency;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			AbstractItem that = (AbstractItem)obj;
			if (Objects.nonNull(that)) {
				if(that.hashCode() == this.hashCode()) {
					return true;
				}
			}
		}
		return false;
	}
}
