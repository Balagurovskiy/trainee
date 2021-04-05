package com.shop.bean.customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop.bean.currency.Currency;

public class CustomerRepository {
	
	private Connection connection;
	
	public CustomerRepository(Connection connection) {
		this.connection = connection;
	}
	
	public boolean create(String name, double cash, int currencyId){
		String sqlTest = "SELECT * FROM internet_shop.customers WHERE customers.name=?";
	    try (PreparedStatement statement= connection.prepareStatement(sqlTest)){
		    statement.setString(1, name);
		    ResultSet resultSet = statement.executeQuery();
		    if (resultSet.next()) {
		       return false;
		    }
	    } catch (SQLException e) {
			e.printStackTrace();
		}
		String sql = "INSERT INTO internet_shop.customers (name, cash, currencyId, pass) VALUES (?, ?, ?, '111')";
	    try (PreparedStatement statement= connection.prepareStatement(sql)){
		    statement.setString(1, name);
		    statement.setDouble(2, cash);
		    statement.setInt(3, currencyId);
		    statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return true;
	}
	public void updateCash(int customerId, double amount){
		String sql = "UPDATE internet_shop.customers SET cash=? WHERE id=?";
	    try (PreparedStatement statement= connection.prepareStatement(sql)){
	    	statement.setDouble(1, amount);
	    	statement.setInt(2, customerId);
		    statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Customer getByName(String name){
		String sql = "SELECT * FROM internet_shop.customers JOIN internet_shop.currency ON currencyId = currency.id WHERE customers.name=?";
	    try (PreparedStatement statement= connection.prepareStatement(sql)){
		    statement.setString(1, name);
		    ResultSet resultSet = statement.executeQuery();
		    if (!resultSet.next()) {
		        System.out.println("No such Customer stored.");
		    } else {
		    	Currency cash = new Currency(resultSet.getInt("currencyId"), resultSet.getString("currency.name"), resultSet.getDouble("currency.koef"));
		    	cash.setAmount(resultSet.getDouble("cash"));
		    	return new Customer(resultSet.getInt("id"), resultSet.getString("name"), cash);
		    }
	    } catch (SQLException e) {
			e.printStackTrace();
		}
	    return null;
	}
	
	public Customer getById(int id){
		String sql = "SELECT * FROM internet_shop.customers JOIN internet_shop.currency ON currencyId = currency.id WHERE customers.id=?";
	    try (PreparedStatement statement= connection.prepareStatement(sql)){
		    statement.setInt(1, id);
		    ResultSet resultSet = statement.executeQuery();
		    if (!resultSet.next()) {
		        System.out.println("No such Customer stored.");
		    } else {
		    	Currency cash = new Currency(resultSet.getInt("currencyId"), resultSet.getString("currency.name"), resultSet.getDouble("currency.koef"));
		    	cash.setAmount(resultSet.getDouble("cash"));
		    	return new Customer(resultSet.getInt("id"), resultSet.getString("name"), cash);
		    }
	    } catch (SQLException e) {
			e.printStackTrace();
		}
	    return null;
	}
	
	public List<Customer> getAll() {
	    List<Customer> customers = new ArrayList<>();
		String sql = "SELECT * FROM internet_shop.customers JOIN internet_shop.currency ON currencyId = currency.id";
	    try (PreparedStatement statement= connection.prepareStatement(sql)){

		    ResultSet resultSet = statement.executeQuery();
		    
		    while (resultSet.next()) {
		    	Currency cash = new Currency(resultSet.getInt("currencyId"), resultSet.getString("currency.name"), resultSet.getDouble("currency.koef"));
		    	cash.setAmount(resultSet.getDouble("cash"));
		    	customers.add(new Customer(resultSet.getInt("id"), resultSet.getString("name"), cash));
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return customers;
	}
	
}
