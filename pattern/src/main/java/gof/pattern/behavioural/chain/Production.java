package gof.pattern.behavioural.chain;

public abstract class Production {
	protected Production next;
	public void linkWith(Production next) {
		this.next = next;
	}
	
	protected boolean forward(Car car) {
		if(next != null) {
			return next.executeTask(car);
		}
		return true;
	}
	public abstract boolean executeTask(Car car);
}
