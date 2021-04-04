package com.shop.bean.orders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop.bean.currency.Currency;
import com.shop.bean.products.Product;

public class OrdersRepository {
	private Connection connection;
	
	public OrdersRepository(Connection connection) {
		this.connection = connection;
	}
	
	public void create(int customerId, int productId){
		String sql = "INSERT INTO orders (customerId, productId, processed_date) VALUES (?, ?, CURDATE())";
	    try (PreparedStatement statement= connection.prepareStatement(sql)){
		    statement.setInt(1, customerId);
		    statement.setInt(2, productId);
		    statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<Order> getCustomerHistory(int customerId) {
	    List<Order> p = new ArrayList<>();
		String sql = "CALL internet_shop.get_customer_history(?)";
	    try (PreparedStatement statement= connection.prepareStatement(sql)){
	    	statement.setInt(1, customerId);
		    ResultSet resultSet = statement.executeQuery();
		    
		    while (resultSet.next()) {
		    	Currency curr = new Currency(resultSet.getInt("currency.id"), resultSet.getString("currency.name"), resultSet.getDouble("currency.koef"));
		    	curr.setAmount(resultSet.getDouble("products.price"));
		    	Product product = new Product(resultSet.getInt("products.id"), resultSet.getString("products.name"), curr, resultSet.getInt("products.eatable"));
		    	Order order = new Order(resultSet.getInt("orders.id"), product, null, resultSet.getDate("orders.processed_date"), 1);
		    	p.add(order);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return p;
	}
	
	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internet_shop", "root", "1111")) {
			OrdersRepository customerRepository = new OrdersRepository(connection);
			for(Order c : customerRepository.getCustomerHistory(2)) {
				System.out.println(c.getProduct().getName() + "  " + c.getProduct().getPrice().getAmount() + "  " + c.getProduct().getPrice().getKoef()+ "  " + c.getProduct().getPrice().getName());
			}
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}
	}
	
}
