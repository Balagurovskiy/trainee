package gof.pattern.structural.bridge;

import java.util.List;

public abstract class Car {
	protected Engine engine;
	private double speed;
	
	public Car(Engine engine) {
		this.engine = engine;
	}
	
	public abstract void acselerate();
	public abstract void brake();
	
	protected void calcSpeed(boolean usedBrakeTorque) {
		double torque = engine.getTorque();
		if(engine.getTorque() < 1.) {
			speed = 0.;
		} else {
			double factor = (usedBrakeTorque) ? 1. : 100.;
			speed = factor * (engine.getPower() * 5.26) / torque;
		}
	}
	
	public double getSpeed() {
		return speed;
	}
	public void stop() {
		engine.setTorque( 0. );
		calcSpeed(true);
	}
}
