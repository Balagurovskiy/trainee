package my.shop.repository;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import my.shop.customer.Customer;
import my.shop.products.Product;
import my.shop.products.impl.Banana;
import my.shop.products.impl.Chair;
import my.shop.products.impl.Milk;
import my.shop.products.impl.NuclearPoweredAircraftCarrier;
import my.shop.products.impl.Spoon;
import my.shop.products.impl.Water;

public class OrderDaoJdbc {

	private Connection connection;
	
	public OrderDaoJdbc(Connection connection) {
		this.connection = connection;
	}

	public void insertCustomerWholeBucket(Customer customer){
		String sql = "INSERT INTO orders (userId, name, price, currency, processed) VALUES (?, ?, ?, ?, ?)";
	    try (PreparedStatement statement= connection.prepareStatement(sql)){
	    	
	    	customer.getWarehouse().copy().forEach((prod, count) -> {
	    		for (int i = 0; i < count; i++) {
	    		    try {
						statement.setLong(1, customer.getId());
		    		    statement.setString(2, prod.getName());
		    		    statement.setDouble(3, prod.price().getAmount());
		    		    statement.setString(4, prod.price().getCourse().getName());
		    		    statement.setInt(5, 0);
		    		    statement.addBatch();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
	    	});
		    statement.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertProductToOrder(long customerId, Product product){
		String sql = "INSERT INTO orders (userId, name, price, currency, processed) VALUES (?, ?, ?, ?, 0)";
	    try (PreparedStatement statement= connection.prepareStatement(sql)){
			statement.setLong(1, customerId);
		    statement.setString(2, product.getName());
		    statement.setDouble(3, product.price().getAmount());
		    statement.setString(4, product.price().getCourse().getName());
		    statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void setOrderToProcessed(long customerId){
		String sql = "UPDATE orders SET processed=1 where userId=? and processed=0";
	    try (PreparedStatement statement= connection.prepareStatement(sql)){
			statement.setLong(1, customerId);
		    statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteOrderById(long orderId){
		String sql = "DELETE FROM orders WHERE id=?";
	    try (PreparedStatement statement= connection.prepareStatement(sql)){
			statement.setLong(1, orderId);
		    statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteOneOrderByName(String name){
		String sql = "DELETE FROM orders WHERE name=? LIMIT 1";
	    try (PreparedStatement statement= connection.prepareStatement(sql)){
			statement.setString(1, name);
		    statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteNotProcessedOrders(long customerId){
		String sql = "DELETE FROM orders WHERE userId=?";
	    try (PreparedStatement statement= connection.prepareStatement(sql)){
			statement.setLong(1, customerId);
		    statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<Product> selectNotProcessedOrdersByCustomer(long customerId){
		return selectCustomerOrderByProcessed(customerId, 0);
	}
	public List<Product> selectProcessedOrdersByCustomer(long customerId){
		return selectCustomerOrderByProcessed(customerId, 1);
	}
	
	public List<OrderInfo> getCustomerHistory(long customerId) {
		String sql = "CALL get_customer_history(?)";
		List<OrderInfo> prdcts = new ArrayList<>();
	    try (PreparedStatement statement= connection.prepareStatement(sql)){
		    statement.setLong(1, customerId);
		    ResultSet resultSet = statement.executeQuery();
	    	while (resultSet.next()) {
	    		 prdcts.add(new OrderInfo(productSwitcher(resultSet.getString("name")), resultSet.getDate("orderUpdated")));
		    }
	    } catch (SQLException e) {
			e.printStackTrace();
		}
	    return prdcts;
	}
	
	private List<Product> selectCustomerOrderByProcessed(long customerId, int processed) {
		String sql = "SELECT orders.name FROM orders WHERE userId = ? AND processed = ?;";
		List<Product> prdcts = new ArrayList<>();
	    try (PreparedStatement statement= connection.prepareStatement(sql)){
		    statement.setLong(1, customerId);
		    statement.setLong(2, processed);
		    ResultSet resultSet = statement.executeQuery();
	    	while (resultSet.next()) {
	    		 prdcts.add(productSwitcher(resultSet.getString("name")));
		    }
	    } catch (SQLException e) {
			e.printStackTrace();
		}
	    return prdcts;
	}

	public Product productSwitcher(String name) {
		final Class[] productsNames = {
				Milk.class,
				Water.class,
				Banana.class,
				Spoon.class,
				Chair.class,
				NuclearPoweredAircraftCarrier.class
		};
		for (Class pclass : productsNames) {
			if(pclass.getSimpleName().equals(name)) {
				Constructor<?> cons;
				Product p = null;
				try {
					cons = pclass.getConstructor();
					p = (Product) cons.newInstance();
				} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
				return p;
			}
		}
		return null;
	}
}
