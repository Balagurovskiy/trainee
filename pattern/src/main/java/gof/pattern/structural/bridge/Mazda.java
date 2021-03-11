package gof.pattern.structural.bridge;

public class Mazda extends Car {

	public Mazda(Engine engine) {
		super(engine);
	}

	@Override
	public void acselerate() {
		engine.setTorque( engine.getTorque() + 1500. );
		calcSpeed(false);
	}

	@Override
	public void brake() {
		double torque = engine.getTorque();
		double brakeTorque = torque - 1000.;
		if (brakeTorque >= 0.) {
			engine.setTorque( brakeTorque );
		}
		calcSpeed(true);
	}

}
