package my.shop.actions;

import java.sql.Connection;
import java.util.List;

import my.shop.currency.Course;
import my.shop.currency.Currency;
import my.shop.customer.Customer;
import my.shop.products.ProductStash;
import my.shop.repository.OrderDaoJdbc;
import my.shop.repository.OrderInfo;

public class WareHouseManagment extends AbstractAction{
	private Connection connection;
	private Currency total;
	public WareHouseManagment(ProductStash stash, Customer customer, Connection connection) {
		super(stash, customer);
		this.connection = connection;
		total = new Currency(Course.POUND);
	}
	@Override
	public void createInterface() {
		 List<OrderInfo> info = new OrderDaoJdbc(connection).getCustomerHistory(customer.getId());
		System.out.println("------------------------------");
		System.out.println("\t WAREHOUSE \n");
		System.out.println(" bucket : " + customer.getName());
		System.out.println(" balance : " + customer.balance());
		System.out.println("- - - - - - - - - - - - - - - \n");
		for(OrderInfo oi : info) {
			System.out.println(" > " + oi.getProduct().getName() + " : " + oi.getProduct().price().getAmount() + " " + oi.getProduct().price().getCourse().getName() + " | " +oi.getDate() + " " + ";");
			double current = oi.getProduct().price().convertTo(total.getCourse());
			total.setAmount(total.getAmount() + current);
		}
		System.out.println("\n TOTAL : " + total.getAmount() + "  " + total.getCourse().getName());
		System.out.println(" [back] go to main manu.");
		System.out.println("------------------------------");
	}

	@Override
	public void acceptRequest(String request) {
		
		switch(request) {
            case "back":
            	next = new MainMenu(stash, customer, connection);
            default:
        }
	}

}
