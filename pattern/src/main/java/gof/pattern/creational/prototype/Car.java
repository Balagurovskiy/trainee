package gof.pattern.creational.prototype;

public class Car extends Vehicle {

	private int wheels;
	
	public Car() {
	}
	
	public Car(Car car) {
		super(car);
		if (car == null) {
			return ;
		}
		this.wheels = car.getWheels();
	}
	
	@Override
	public Vehicle prototype() {
		return new Car(this);
	}

	public int getWheels() {
		return wheels;
	}

	public void setWheels(int wheels) {
		this.wheels = wheels;
	}

}
