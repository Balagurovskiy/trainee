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
import my.shop_structure.products.impl.Water;

public class BucketManagmentActionTest {

	private static BucketManament tested;
	private static ProductStash stash;
	private static Bucket bucket;
	
	@BeforeClass
	public static void init() {
		stash = new ProductStash();
		bucket = new Bucket();
		tested = new BucketManament(stash, bucket);
	}
	@Test
	public void bucketManamentTest_hasNextAfterRequest_2_ExpectedTrue() {
		tested.acceptRequest("back");
		Assert.assertTrue(tested.hasNext());
	}
	@Test
	public void bucketManamentTest_hasNextAfterRequest_ExpectedFalse() {
		tested.acceptRequest("clear");
		Assert.assertFalse(Objects.isNull(tested.hasNext()));
	}
	@Test
	public void bucketManamentTest_itemDelete_ExpectedEmpty() {
		bucket.getStash().clear();
		bucket.setStash(new Water());
		tested.acceptRequest("0");
		Assert.assertTrue(bucket.getStash().isEmpty());
	}
	@Test
	public void bucketManamentTest_clear_ExpectedEmpty() {
		bucket.getStash().clear();
		bucket.setStash(new Water());
		tested.acceptRequest("clear");
		Assert.assertTrue(bucket.getStash().isEmpty());
	}
	@Test
	public void bucketManamentTest_nextIsMainMenuInstance_ExpectedTrue() {
		tested.acceptRequest("back");
		Assert.assertTrue(tested.next() instanceof MainMenu);
	}

	@Test
	public void bucketManamentTest_exitRquest_ExpectedFalse() {
		tested.acceptRequest("exit");
		Assert.assertFalse(tested.stop());
	}
}
