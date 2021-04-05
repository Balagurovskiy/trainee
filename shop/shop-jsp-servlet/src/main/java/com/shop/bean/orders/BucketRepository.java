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

public class BucketRepository {
	private Connection connection;
	
	public BucketRepository(Connection connection) {
		this.connection = connection;
	}
	
	public void create(int customerId, int productId){
		String sql = "INSERT INTO bucket (customerId, productId, processed) VALUES (?, ?, 0)";
	    try (PreparedStatement statement= connection.prepareStatement(sql)){
		    statement.setInt(1, customerId);
		    statement.setInt(2, productId);
		    statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Order> getAllNotProcessedByCustomerId(int customerId) {
	    List<Order> p = new ArrayList<>();
		String sql = "SELECT * FROM internet_shop.bucket "
						+"JOIN internet_shop.products ON productId = products.id "
					    +"JOIN internet_shop.currency ON products.currencyId = currency.id "
					+"WHERE customerId = ? AND processed = 0";
	    try (PreparedStatement statement= connection.prepareStatement(sql)){
	    	statement.setInt(1, customerId);
		    ResultSet resultSet = statement.executeQuery();
		    
		    while (resultSet.next()) {
		    	Currency curr = new Currency(resultSet.getInt("currency.id"), resultSet.getString("currency.name"), resultSet.getDouble("currency.koef"));
		    	curr.setAmount(resultSet.getDouble("products.price"));
		    	Product product = new Product(resultSet.getInt("products.id"), resultSet.getString("products.name"), curr, resultSet.getInt("products.eatable"));
		    	Order order = new Order(resultSet.getInt("bucket.id"), product, null, null, resultSet.getInt("bucket.processed"));
		    	p.add(order);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return p;
	}
	
	public void clearProcessedOrders(int customerId){
		String sql = "DELETE FROM internet_shop.bucket  WHERE processed=1 AND customerId=?";
	    try (PreparedStatement statement= connection.prepareStatement(sql)){
	    	statement.setInt(1, customerId);
		    statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void removeOrderById(int orderId){
		String sql = "DELETE FROM internet_shop.bucket  WHERE id=?";
	    try (PreparedStatement statement= connection.prepareStatement(sql)){
	    	statement.setInt(1, orderId);
		    statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void clearUnProcessedOrders(int customerId){
		String sql = "DELETE FROM internet_shop.bucket  WHERE processed=0 AND customerId=?";
	    try (PreparedStatement statement= connection.prepareStatement(sql)){
	    	statement.setInt(1, customerId);
		    statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void setToProcessedByCustomerId(int customerId){
		String sql = "UPDATE internet_shop.bucket SET processed=1 WHERE customerId=?";
	    try (PreparedStatement statement= connection.prepareStatement(sql)){
	    	statement.setInt(1, customerId);
		    statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internet_shop", "root", "1111")) {
			BucketRepository customerRepository = new BucketRepository(connection);
			customerRepository.setToProcessedByCustomerId(2);
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}
	}
}
