package my.shop.repository;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import my.shop.customer.Customer;

public class CustomerDaoJdbc {
 
 
	private Connection connection;
	
	public CustomerDaoJdbc(Connection connection) {
		this.connection = connection;
	}
	
	public void insertCustomer(String name, double cash){
 
		String sql = "INSERT INTO  customer (name, cash) VALUES (?, ?)";
	    try (PreparedStatement statement= connection.prepareStatement(sql)){
		    statement.setString(1, name);
		    statement.setDouble(2, cash);
		    statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateCash(long costumerId, double cash){
		String sql = "UPDATE customer SET cash=? where id=?";
	    try (PreparedStatement statement= connection.prepareStatement(sql)){
		    statement.setDouble(1, cash);
		    statement.setLong(2, costumerId);
		    statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Customer selectCustomerByName(String name){
		String sql = "SELECT * FROM customer WHERE name=?";
	    try (PreparedStatement statement= connection.prepareStatement(sql)){
		    statement.setString(1, name);
		    ResultSet resultSet = statement.executeQuery();
		    if (!resultSet.next()) {
		        System.out.println("No such file stored.");
		    } else {
		    	Customer customer = new Customer(resultSet.getString("name"), resultSet.getLong("id"), resultSet.getDouble("cash"));
		        return customer;
		    }
	    } catch (SQLException e) {
			e.printStackTrace();
		}
	    return null;
	}
	
	public List<Customer> selectCustomers() {
 
	    List<Customer> customers = new ArrayList<>();
		String sql = "SELECT * FROM customer";
	    try (PreparedStatement statement= connection.prepareStatement(sql)){

		    ResultSet resultSet = statement.executeQuery();
		    
		    while (resultSet.next()) {
		    	customers.add(new Customer(resultSet.getString("name"), resultSet.getLong("id"), resultSet.getDouble("cash")));
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return customers;
	}
}
