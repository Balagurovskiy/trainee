package gof.pattern.creational.builder;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BuilderTest {

	private Director director;
	private VehicleBuilder builder;
	
	@Before
	public void init() {
		this.director = new Director();
		this.builder = new CarBuilder();
	}
	
	@Test
	public void isCasualCar_ExpectedTrue() {
		director.setupCasualCar(builder);
		Vehicle car = builder.getVehicle();
		assertEquals(2, car.getEngineL());
		assertEquals(4, car.getNumberOfDoors());
	}
	
	@Test
	public void isSportCar_ExpectedTrue() {
		director.setupSportCar(builder);
		Vehicle car = builder.getVehicle();
		assertEquals(5, car.getEngineL());
		assertEquals(2, car.getNumberOfDoors());
	}
}
