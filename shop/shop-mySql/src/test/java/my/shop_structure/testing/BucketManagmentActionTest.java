package my.shop_structure.testing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import my.shop.actions.BucketManagment;
import my.shop.actions.MainMenu;
import my.shop.actions.ProductList;
import my.shop.customer.Bucket;
import my.shop.customer.Customer;
import my.shop.products.ProductStash;
import my.shop.products.impl.Water;

public class BucketManagmentActionTest {

	private static BucketManagment tested;
	private static ProductStash stash;
	private static Customer customer;
	
	@BeforeClass
	public static void init() {
		stash = new ProductStash();
		customer = new Customer("test",999);
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "1111")) {
			tested = new BucketManagment(stash, customer, connection);
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}
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
		customer.getWarehouse().clear();
		customer.buy(new Water());
		tested.acceptRequest("Water");
		Assert.assertTrue(customer.getWarehouse().isEmpty());
	}
	@Test
	public void bucketManamentTest_clear_ExpectedEmpty() {
		customer.getWarehouse().clear();
		customer.buy(new Water());
		tested.acceptRequest("clear");
		Assert.assertTrue(customer.getWarehouse().isEmpty());
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
