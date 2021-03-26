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

public class ProductListActionTest {

	private static ProductList tested;
	private static ProductStash stash;
	private static Bucket bucket;
	
	@BeforeClass
	public static void init() {
		stash = new ProductStash();
		bucket = new Bucket();
		tested = new ProductList(stash, bucket);
	}
	@Test
	public void productListTest_hasNextAfterRequest_1_ExpectedTrue() {
		tested.acceptRequest("bucket");
		Assert.assertTrue(tested.hasNext());
	}
	@Test
	public void productListTest_hasNextAfterRequest_2_ExpectedTrue() {
		tested.acceptRequest("back");
		Assert.assertTrue(tested.hasNext());
	}
	@Test
	public void productListTest_hasNextAfterRequest_ExpectedFalse() {
		tested.acceptRequest("0");
		Assert.assertFalse(Objects.isNull(tested.hasNext()));
	}
	@Test
	public void productListTest_bucketListIsEmptyAfterRequest_ExpectedFlse() {
		tested.acceptRequest("1");
		Assert.assertFalse(bucket.getStash().isEmpty());
	}
	@Test
	public void productListTest_nextIsMainMenuInstance_ExpectedTrue() {
		tested.acceptRequest("back");
		Assert.assertTrue(tested.next() instanceof MainMenu);
	}
	@Test
	public void productListTest_nextIsBucketManamentInstance_ExpectedTrue() {
		tested.acceptRequest("bucket");
		Assert.assertTrue(tested.next() instanceof BucketManament);
	}
	@Test
	public void productListTest_exitRquest_ExpectedFalse() {
		tested.acceptRequest("exit");
		Assert.assertFalse(tested.stop());
	}
}
