package gof.pattern.behavioural.chain;

import java.util.Objects;

public abstract class Production {
	protected Production next;
	public void linkWith(Production next) {
		this.next = next;
	}
	
	protected boolean forward(Car car) {
		return Objects.isNull(next) ? true : next.executeTask(car);
	}
	public abstract boolean executeTask(Car car);
}
