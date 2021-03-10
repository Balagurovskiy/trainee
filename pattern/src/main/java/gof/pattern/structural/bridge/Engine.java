package gof.pattern.structural.bridge;

public interface Engine {
	public double getPower();
	public double getTorque();
	public void setTorque(double torque);
}
