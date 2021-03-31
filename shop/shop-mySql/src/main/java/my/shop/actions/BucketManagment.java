package my.shop.actions;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import my.shop.currency.Course;
import my.shop.currency.Currency;
import my.shop.customer.Customer;
import my.shop.products.Product;
import my.shop.products.ProductStash;
import my.shop.repository.CustomerDaoJdbc;
import my.shop.repository.OrderDaoJdbc;

public class BucketManagment extends AbstractAction{

	private Connection connection;
	private OrderDaoJdbc dao;
	
	private List<Product> notProcessedProducts;
	private Currency total;
	
	public BucketManagment(ProductStash stash, Customer customer, Connection connection) {
		super(stash, customer);
		this.connection = connection;
		dao = new OrderDaoJdbc(connection);
		total = new Currency(Course.POUND);
	}

	@Override
	public void createInterface() {
		notProcessedProducts = dao.selectNotProcessedOrdersByCustomer(customer.getId());
		System.out.println("------------------------------");
		System.out.println("\t BUCKET \n");
		System.out.println(" bucket : " + customer.getName());
		System.out.println(" balance : " + customer.balance());
		System.out.println("- - - - - - - - - - - - - - - \n");
		total.setAmount(0);
		for(Product p : notProcessedProducts) {
			System.out.println(" > " + p.getName() + " " + p.price().getAmount() + " " + p.price().getCourse().getName() + ";");
			double current = p.price().convertTo(total.getCourse());
			total.setAmount(total.getAmount() + current);
		}
		System.out.println("\n TOTAL : " + total.getAmount() + "  " + total.getCourse().getName());
		System.out.println("- - - - - - - - - - - - - - -");
		System.out.println(" [product name] delete from bucket.");
		System.out.println(" [clear] remove all products.");
		System.out.println(" [buy] pay for all and move products to warehouse.");
		System.out.println(" [back] go to main manu.");
		System.out.println("------------------------------");
	}

	@Override
	public void acceptRequest(String request) {
		
		switch(request) {
            case "back":
            	next = new MainMenu(stash, customer, connection);
                break;
            case "clear":
            	dao.deleteNotProcessedOrders(customer.getId());
                break;
            case "buy":
            	boolean process = true;
            	for(Product p : notProcessedProducts) {
            		process = customer.buy(p) ? process : false;
            	}
            	if (process) {
            		new CustomerDaoJdbc(connection).updateCash(customer.getId(), total.getAmount());
            		dao.setOrderToProcessed(customer.getId());
            		System.out.println("Transfer approved");
            	} else {
            		System.out.println("Transfer aborted");
            		customer = new CustomerDaoJdbc(connection).selectCustomerByName(customer.getName());
            	}
                break;
            default:
            	dao.deleteOneOrderByName(request);
    			break;
        }
	}
}
