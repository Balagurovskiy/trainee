package gof.pattern.creational.prototype;

public abstract class Vehicle {
	private int weight;
	private int speed;
	
	public Vehicle() {
	}
	
	public Vehicle(Vehicle vehicle) {
		if (vehicle == null) {
			return ;
		}
		this.speed = vehicle.getSpeed();
		this.weight = vehicle.getWeight();
	}
	
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public abstract Vehicle prototype();
}
