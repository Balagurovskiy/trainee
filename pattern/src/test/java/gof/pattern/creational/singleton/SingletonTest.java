package gof.pattern.creational.singleton;

import org.junit.Test;

import gof.pattern.creational.sigleton.Sigleton;
import org.junit.Assert;

public class SingletonTest {
	
	@Test
	public void isSameInstance_expectedTrue() {
		Assert.assertEquals(Sigleton.getInstance(), Sigleton.getInstance());
	}
}
