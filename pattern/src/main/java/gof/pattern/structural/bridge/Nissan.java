package gof.pattern.structural.bridge;

public class Nissan extends Car {

	public Nissan(Engine engine) {
		super(engine);
	}

	@Override
	public void acselerate() {
		engine.setTorque( engine.getTorque() + 1000. );
		calcSpeed(false);
	}

	@Override
	public void brake() {
		engine.setTorque( engine.getTorque() - 1500. );
		if (engine.getTorque() < 0.) {
			engine.setTorque(0.);
		}
		calcSpeed(true);
	}

}
