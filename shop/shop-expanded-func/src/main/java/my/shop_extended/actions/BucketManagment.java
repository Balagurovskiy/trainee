package my.shop_extended.actions;


import my.shop_extended.ByteLoader;
import my.shop_extended.customer.Customer;
import my.shop_extended.products.ProductStash;

public class BucketManagment extends AbstractAction{

	public BucketManagment(ProductStash stash, Customer customer) {
		super(stash, customer);
	}

	@Override
	public void createInterface() {
		System.out.println("------------------------------");
		System.out.println("\t WAREHOUSE \n");
		System.out.println(" bucket : " + customer.getName());
		System.out.println(" balance : " + customer.balance());
		System.out.println("- - - - - - - - - - - - - - - \n");
		System.out.println(customer.getWarehouse().stats());
		System.out.println("- - - - - - - - - - - - - - -");
		System.out.println(" [product name] delete from bucket.");
		System.out.println(" [clear] remove all products.");
		System.out.println(" [back] go to main manu.");
		System.out.println("------------------------------");
	}

	@Override
	public void acceptRequest(String request) {
		if (request.equals("back")) {
			next = new MainMenu(stash, customer);
		} else if (request.equals("clear")) {
			customer.getWarehouse().clear();
			ByteLoader.write(customer.getName() + ".bucket", customer);
		} else {
			customer.getWarehouse().remove(request);
			ByteLoader.write(customer.getName() + ".bucket", customer);
		}
	}
}
