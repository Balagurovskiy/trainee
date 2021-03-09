package gof.pattern.creational.factory;

public class HatchBackFactory implements CarFactory {

	@Override
	public Car createCar() {
		return new HatchBack();
	}
}
