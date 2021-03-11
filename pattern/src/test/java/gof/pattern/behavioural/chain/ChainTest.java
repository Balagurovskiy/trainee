package gof.pattern.behavioural.chain;

import org.junit.Assert;
import org.junit.Test;

public class ChainTest {

	@Test
	public void partsValidationTest_ExpectedTrue() {
		Production engine = new EngineAssembler();
		Production w1 = new WheelAssembler();
		Production w2 = new WheelAssembler();
		Production w3 = new WheelAssembler();
		Production w4 = new WheelAssembler();
		Production valid = new PartsValidation();

		engine.linkWith(w1);
		w1.linkWith(w2);
		w2.linkWith(w3);
		w3.linkWith(w4);
		w4.linkWith(valid);

		Car car = new Car();
		
		Assert.assertTrue("", engine.executeTask(car));
	}
	
	@Test
	public void partsValidationTest_ExpectedFalse() {
		Production engine = new EngineAssembler();
		Production w1 = new WheelAssembler();
		Production w2 = new WheelAssembler();
		Production w3 = new WheelAssembler();
		Production valid = new PartsValidation();

		engine.linkWith(w1);
		w1.linkWith(w2);
		w2.linkWith(w3);
		w3.linkWith(valid);

		Car car = new Car();
		
		Assert.assertFalse("", engine.executeTask(car));
	}
}
