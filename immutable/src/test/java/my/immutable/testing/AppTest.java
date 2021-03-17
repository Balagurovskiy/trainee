package my.immutable.testing;


import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import my.immutable.IdProvider;
import my.immutable.TraineeString;

public class AppTest {

	@Test
	public void stringTest_changeDate_Equals() {
		TraineeString expected = new TraineeString("test");
		TraineeString test = (TraineeString) expected.clone();
		LocalDate t  = test.getCreated().withYear(2019);
		Assert.assertTrue(expected.equals(test));
	}
	@Test
	public void stringTest_changeIdProvider_Equals() {
		TraineeString expected = new TraineeString("test");
		TraineeString test = (TraineeString) expected.clone();
		IdProvider ip  = test.getIdProvider();
		ip.setId(77777777);
		Assert.assertTrue(expected.equals(test));
	}
}
