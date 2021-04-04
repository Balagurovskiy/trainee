package com.shop.account.bucket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop.bean.currency.Currency;
import com.shop.bean.customer.CustomerRepository;
import com.shop.bean.orders.BucketRepository;
import com.shop.bean.orders.Order;
import com.shop.bean.products.Product;

public class BucketTransactionManager {

	public List<Order> getList(int customerId) {
		List<Order> products = new ArrayList<>(20);
	    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internet_shop", "root", "1111")) {
	    	return new BucketRepository(connection).getAllNotProcessedByCustomerId(customerId);
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}
	    return new ArrayList<>();
	}
	
	public void submitOrder(int customerId) {
	    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internet_shop", "root", "1111")) {
	    	BucketRepository bucketRepository = new BucketRepository(connection);
	    	bucketRepository.setToProcessedByCustomerId(customerId);
	    	bucketRepository.clearProcessedOrders(customerId);
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}
	}
	
	public void updateCustomerCash(int customerId, double amount) {
	    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internet_shop", "root", "1111")) {
	    	new CustomerRepository(connection).updateCash(customerId, amount);
	    } catch (SQLException ex) {
		    ex.printStackTrace();
		}
	}
	public double totalPrice(List<Order> products, Currency tagetCurrency) {
		return products.stream()
						.map(Order::getProduct)
						.map(Product::getPrice)
						.map(curr -> curr.convertTo(tagetCurrency.getKoef()))
						.reduce(0.0, Double::sum);
	}
}
