package gof.pattern.behavioural.strategy;

import java.util.concurrent.atomic.AtomicBoolean;

public class Vehicle {
	private AtomicBoolean isMoving;

	public Vehicle() {
		isMoving = new AtomicBoolean(false);
	}
	public boolean isMoving() {
		return isMoving.get();
	}
	
	public void executeAction(Action action) {
		action.execute(isMoving);
	}
}
