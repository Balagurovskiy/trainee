package gof.pattern.structural.adapter;

public class CarToDogAdapter implements Dog{
	private Car car;
	
	public CarToDogAdapter(Car car) {
		this.car = car;
	}

	@Override
	public void move() {
		car.ride();
	}
}
