package my.shop.actions;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import my.shop.customer.Customer;
import my.shop.products.ProductStash;
import my.shop.repository.CustomerDaoJdbc;

public class PreloadCustomer  extends AbstractAction{
	
	private CustomerDaoJdbc dao;
	private Connection connection;
	private List<Customer> customers;
	public PreloadCustomer(ProductStash stash, Customer customer, Connection connection) {
		super(stash, customer);
		this.connection = connection;
		dao = new CustomerDaoJdbc(connection);
	}
	
	@Override
	public void createInterface() {
		customers = dao.selectCustomers();
		System.out.println("------------------------------");
		System.out.println("\t SELECT WAREHOUSE OR MAKE NEW\n");
		IntStream
	      .range(0, customers.size())
	      .forEach(i -> {
	    	  Customer c = customers.get(i);
	    	  System.out.println(i + ") " + c.getName() + "  " + c.balance() + ";");
	      });
		System.out.println(" [...] name of customer or new one;");
		System.out.println(" [exit] close shop.");
		System.out.println("------------------------------");
	}

	@Override
	public void acceptRequest(String request) {
		
		switch(request) {
	        case "exit":
	        	this.stop = true;
	            break;
	        default:
	        	Optional<Customer> opCust = customers.stream().filter(c -> c.getName().equals(request)).findAny();
	        	if (opCust.isPresent()) {
	        		customer = opCust.get();
	        		System.out.println("Customer loaded");
	        	} else {
	        		dao.insertCustomer(request, 1_000_000.0);
	        		customer = dao.selectCustomerByName(request);
	        		System.out.println("New customer created");
	        	}
				next = new MainMenu(stash, customer, connection);
	            break;
		}
		
	}
}
