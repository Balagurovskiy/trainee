package gof.pattern.behavioural.momento;

import org.junit.Assert;
import org.junit.Test;


public class MomentoTest {
	@Test
	public void saveDistanceTest_ExpectedTrue() {
		Car car = new Car();
		car.setCarDrivenDistance(777);
		DistanceSave save = car.saveCurrentDistance();
		car.setCarDrivenDistance(111);
		save.restore();
		Assert.assertEquals(car.getDistance(), 777);
	}
}
