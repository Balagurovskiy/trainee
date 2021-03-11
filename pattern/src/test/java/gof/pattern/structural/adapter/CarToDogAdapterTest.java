package gof.pattern.structural.adapter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class CarToDogAdapterTest {

	@Test
	public void dogIsCallingCarMethodAfterAdapter_ExpectsTrue() {
		Car car = mock(Car.class);

		Dog adaptedDog = new CarToDogAdapter(car);
		adaptedDog.move();
		
		verify(car, times(1)).ride();
	}

}
