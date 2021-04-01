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

public class MainMenuActionTest {
	
	private static MainMenu tested;
	private static ProductStash stash;
	private static Customer customer;
	
	@BeforeClass
	public static void init() {
		stash = new ProductStash();
		customer = new Customer("test",999);
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "1111")) {
			tested = new MainMenu(stash, customer, connection);
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}
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
		Assert.assertTrue(tested.next() instanceof BucketManagment);
	}
	@Test
	public void mainMenuTest_exitRquest_ExpectedTrue() {
		tested.acceptRequest("exit");
		Assert.assertTrue(tested.stop());
	}
}
