package gof.pattern.behavioural.strategy;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class StrategyTest {

	public static Vehicle vehicle;
	
	@BeforeClass
	public static void init() {
		 vehicle = new Vehicle();
	}
	@Test
	public void vehicleMoveStrategyTest_ExpectedTrue() {
		vehicle.executeAction( new Move());
		vehicle.executeAction( new Stop());
		vehicle.executeAction( new Move());
		Assert.assertTrue(vehicle.isMoving());
	}
	
	@Test
	public void vehicleStopStrategyTest_ExpectedFalse() {
		vehicle.executeAction( new Move());
		vehicle.executeAction( new Stop());
		Assert.assertFalse(vehicle.isMoving());
	}
}
