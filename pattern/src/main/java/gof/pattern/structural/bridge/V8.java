package gof.pattern.structural.bridge;

public class V8 implements Engine {

	private final double POWER = 400.;
	private double torque;
	
	@Override
	public double getPower() {
		return POWER;
	}

	@Override
	public double getTorque() {
		return torque;
	}

	@Override
	public void setTorque(double torque) {
		this.torque = torque;
	}

}
