package my.shop_extended.currency;

import java.io.Serializable;

public class Wallet implements Serializable{
	
	private static final long serialVersionUID = -7801378821143344730L;
	private Currency currency;
	
	public Wallet() {
		currency = new Currency();
	}
	
	public Wallet(Course course) {
		currency = new Currency(course);
	}
	
	public Wallet(double amount, Course course) {
		currency = new Currency(amount, course);
	}

	public boolean deposit(Currency amount) {
		double convertedAmount = amount.convertTo( this.currency.getCourse() );
		currency.setAmount(currency.getAmount() + convertedAmount);
		return true;
	}

	public boolean withdraw(Currency amount) {
		double convertedAmount = amount.convertTo( this.currency.getCourse() );
		if(convertedAmount > this.currency.getAmount()) {
			return false;
		}
		currency.setAmount(currency.getAmount() - convertedAmount);
		return true;
	}

	public Course course() {
		return this.currency.getCourse();
	}
	public double balance() {
		return this.currency.getAmount();
	}
}
