package gof.pattern.creational.builder;

public class CarBuilder extends VehicleBuilder {
	public CarBuilder() {
		super(new Car());
	}
}
