package my.shop_structure.testing;

import java.util.Objects;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import my.shop_structure.actions.BucketManament;
import my.shop_structure.actions.MainMenu;
import my.shop_structure.actions.ProductList;
import my.shop_structure.bucket.Bucket;
import my.shop_structure.products.ProductStash;

public class MainMenuActionTest {
	
	private static MainMenu tested;
	private static ProductStash stash;
	private static Bucket bucket;
	
	@BeforeClass
	public static void init() {
		stash = new ProductStash();
		bucket = new Bucket();
		tested = new MainMenu(stash, bucket);
	}
	@Test
	public void mainMenuTest_hasNextAfterRequest_1_ExpectedTrue() {
		tested.acceptRequest("1");
		Assert.assertTrue(tested.hasNext());
	}
	@Test
	public void mainMenuTest_hasNextAfterRequest_2_ExpectedTrue() {
		tested.acceptRequest("2");
		Assert.assertTrue(tested.hasNext());
	}
	@Test
	public void mainMenuTest_hasNextAfterExitRequest_ExpectedFalse() {
		tested.acceptRequest("exit");
		Assert.assertFalse(Objects.isNull(tested.hasNext()));
	}
	@Test
	public void mainMenuTest_nextIsProductListInstance_ExpectedTrue() {
		tested.acceptRequest("1");
		Assert.assertTrue(tested.next() instanceof ProductList);
	}
	@Test
	public void mainMenuTest_nextIsBucketManagerInstance_ExpectedTrue() {
		tested.acceptRequest("2");
		Assert.assertTrue(tested.next() instanceof BucketManament);
	}
	@Test
	public void mainMenuTest_exitRquest_ExpectedTrue() {
		tested.acceptRequest("exit");
		Assert.assertTrue(tested.stop());
	}
}
