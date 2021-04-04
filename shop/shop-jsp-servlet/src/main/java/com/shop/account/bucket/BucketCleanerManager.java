package com.shop.account.bucket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.shop.bean.orders.BucketRepository;

public class BucketCleanerManager {
	public void clean(int customerId) {
	    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internet_shop", "root", "1111")) {
	    	new BucketRepository(connection).clearUnProcessedOrders(customerId);
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}
	}
	public void cleanById(int orderId) {
	    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internet_shop", "root", "1111")) {
	    	new BucketRepository(connection).removeOrderById(orderId);
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}
	}
}
