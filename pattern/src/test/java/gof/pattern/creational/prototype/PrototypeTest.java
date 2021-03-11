package gof.pattern.creational.prototype;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class PrototypeTest {

	private final static int EXPECTED_SPEED = 120;
	private final static int EXPECTED_WEIGHT = 700;
	private final static int EXPECTED_WHEELS = 4;
	
	private static Car copy;
	
	@BeforeClass
	public static void init() {
		Car original = new Car();
		original.setSpeed( EXPECTED_SPEED );
		original.setWeight( EXPECTED_WEIGHT );
		original.setWheels( EXPECTED_WHEELS );
		copy = (Car) original.prototype();
	}
	@Test
	public void isNotInstanceOfCar_expectedFalse() {
		Assert.assertFalse( copy == null);
	}
	@Test
	public void prototypeHasSameSpeed_expectedTrue() {
		Assert.assertEquals(copy.getSpeed(), EXPECTED_SPEED);
	}
	@Test
	public void prototypeHasSameWeight_expectedTrue() {
		Assert.assertEquals(copy.getWeight(), EXPECTED_WEIGHT);
	}
	@Test
	public void prototypeHasSameWheels_expectedTrue() {
		Assert.assertEquals(copy.getWheels(), EXPECTED_WHEELS);
	}
}
