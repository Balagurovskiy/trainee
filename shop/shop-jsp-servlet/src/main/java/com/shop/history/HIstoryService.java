package com.shop.history;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop.bean.orders.Order;
import com.shop.bean.orders.OrdersRepository;

public class HIstoryService {

	
//	 List<Order> getCustomerHistory
	
	public List<Order> getCustomerHistory(int customerId){
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internet_shop", "root", "1111")) {
			return new OrdersRepository(connection).getCustomerHistory(customerId);
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}
		return new ArrayList<>();
	}
}
