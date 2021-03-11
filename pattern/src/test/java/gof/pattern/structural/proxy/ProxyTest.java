package gof.pattern.structural.proxy;

import static org.junit.Assert.fail;

import org.junit.Test;

public class ProxyTest {
	@Test
	public void carWithValidCargo_ExpectedNoExceptions() {
		//junit 5 // assertThrows(Exception.class, ()->{});
		try {
			Vehicle car = new Car(1000);
			car.loadCargo(454);
			car.loadCargo(454);
			car.move();
		} catch (RuntimeException e){
			fail();
		}
	}
	
	@Test(expected = RuntimeException.class)
	public void carWithInvalidCargo_ExpectedRuntimeException() {
		Vehicle car = new Car(1000);
		car.loadCargo(1001);
		car.move();
	}
	
	@Test
	public void proxyCarWithInvalidCargo_ExpectedNoExceptions() {
		try {
			Vehicle car = new ProxyCar(1000);
			car.loadCargo(1001);
			car.move();
		} catch (RuntimeException e){
			fail();
		}
	}
}
