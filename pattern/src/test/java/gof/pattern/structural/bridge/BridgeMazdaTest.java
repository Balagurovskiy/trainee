package gof.pattern.structural.bridge;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class BridgeMazdaTest {
	private static Car mazdaV6;
	private static Car mazdaV8;
	
	@BeforeClass
	public static void init() {
		mazdaV6 = new Mazda(new V6());
		mazdaV8 = new Mazda(new V8());
	}
	
	@Before
	public void stopBeforeTest() {
		mazdaV6.stop();
		mazdaV8.stop();
	}
	@Test
	public void mazdaV6BrackeTest_ExpectedTrue() {
		mazdaV6.acselerate();
		mazdaV6.brake();
		Assert.assertTrue( Utils.compareDouble(mazdaV6.getSpeed(), 3., 0.5) );
	}
	
	@Test
	public void mazdaV6AcsTest_ExpectedTrue() {
		mazdaV6.acselerate();
		Assert.assertTrue( Utils.compareDouble(mazdaV6.getSpeed(), 87., 1.) );
	}
	@Test
	public void mazdaV6AcsTest_ExpectedFalse() {
		mazdaV6.acselerate();
		mazdaV6.acselerate();
		Assert.assertFalse( Utils.compareDouble(mazdaV6.getSpeed(), 87., 1.) );
	}
	
	@Test
	public void mazdaV8BrackeTest_ExpectedTrue() {
		mazdaV8.acselerate();
		mazdaV8.brake();
		Assert.assertTrue( Utils.compareDouble(mazdaV8.getSpeed(), 4., 0.5) );
	}
	
	@Test
	public void mazdaV8AcsTest_ExpectedTrue() {
		mazdaV8.acselerate();
		Assert.assertTrue( Utils.compareDouble(mazdaV8.getSpeed(), 140., 1.) );
	}
	@Test
	public void mazdaV8AcsTest_ExpectedFalse() {
		mazdaV8.acselerate();
		mazdaV8.acselerate();
		Assert.assertFalse( Utils.compareDouble(mazdaV8.getSpeed(), 140., 1.) );
	}
}
