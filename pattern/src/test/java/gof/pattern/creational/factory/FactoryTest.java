package gof.pattern.creational.factory;

import org.junit.Assert;
import org.junit.Test;

public class FactoryTest {

	@Test
	public void isSedan_ExpectedTrue() {
		Car car = createCarByBodyType(CarType.SEDAN);
		Assert.assertTrue(car instanceof Sedan);
	}
	
	@Test
	public void isHatchBack_ExpectedTrue() {
		Car car = createCarByBodyType(CarType.HATCHBACK);
		Assert.assertTrue(car instanceof HatchBack);
	}
	
	private static Car createCarByBodyType(CarType type) {
		CarFactory carFactory = createCarFactoryByBodyType( type );
		return carFactory.createCar();
	}
	
	private static CarFactory createCarFactoryByBodyType(CarType type) {
		if (type.equals(CarType.SEDAN)) {
			return new SedanFactory();
		} else if (type.equals(CarType.HATCHBACK)) {
			return new HatchBackFactory();
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public enum CarType {
		SEDAN,
		HATCHBACK
	}
}
