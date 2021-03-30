package my.shop_extended.actions;

import java.util.Objects;

import my.shop_extended.customer.Customer;
import my.shop_extended.products.ProductStash;

public abstract class AbstractAction implements IAction {

	protected IAction next;
	
	protected ProductStash stash;
	protected Customer customer;
	
	protected boolean stop;
	
	protected AbstractAction(ProductStash stash, Customer customer) {
		this.stash = stash;
		this.customer = customer;
		stop = false;
	}
 
	@Override
	public boolean hasNext() {
		return Objects.nonNull(next);
	}

	@Override
	public IAction next() {
		return next;
	}

	@Override
	public boolean stop() {
		return stop;
	}
}
