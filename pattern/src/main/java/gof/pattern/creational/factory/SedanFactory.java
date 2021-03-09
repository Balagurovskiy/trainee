package gof.pattern.creational.factory;

public class SedanFactory implements CarFactory {

	@Override
	public Car createCar() {
		return new Sedan();
	}
}
