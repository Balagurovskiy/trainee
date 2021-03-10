package gof.pattern.structural.bridge;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BridgeNissanTest {
	private static Car nissanV6;
	private static Car nissanV8;
	
	@BeforeClass
	public static void init() {
		nissanV6 = new Nissan(new V6());
		nissanV8 = new Nissan(new V8());
	}
	
	@Before
	public void stopBeforeTest() {
		nissanV6.stop();
		nissanV8.stop();
	}
	@Test
	public void nissanV6BrackeTest_ExpectedTrue() {
		nissanV6.acselerate(); 
		nissanV6.brake(); 
		Assert.assertTrue( Utils.compareDouble(nissanV6.getSpeed(), 0., 0.5) );
	}
	
	@Test
	public void nissanV6AcsTest_ExpectedTrue() {
		nissanV6.acselerate();
		Assert.assertTrue( Utils.compareDouble(nissanV6.getSpeed(), 131., 1.) );
	}
	@Test
	public void nissanV6AcsTest_ExpectedFalse() {
		nissanV6.acselerate();
		nissanV6.acselerate();
		Assert.assertFalse( Utils.compareDouble(nissanV6.getSpeed(), 131., 1.) );
	}
	
	@Test
	public void nissanV8BrackeTest_ExpectedTrue() {
		nissanV8.acselerate(); 
		nissanV8.brake(); 
		Assert.assertTrue( Utils.compareDouble(nissanV8.getSpeed(), 0., 0.5) );
	}
	
	@Test
	public void nissanV8AcsTest_ExpectedTrue() {
		nissanV8.acselerate();
		Assert.assertTrue( Utils.compareDouble(nissanV8.getSpeed(), 210., 1.) );
	}
	@Test
	public void nissanV8AcsTest_ExpectedFalse() {
		nissanV8.acselerate();
		nissanV8.acselerate();
		Assert.assertFalse( Utils.compareDouble(nissanV8.getSpeed(), 210., 1.) );
	}
}
