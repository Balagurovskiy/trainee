package com.shop.account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

import com.shop.bean.customer.Customer;
import com.shop.bean.customer.CustomerRepository;

public class CustomerCollectService {
	
	public Customer getCustomerById(String idstr) {
		
		if (Objects.nonNull(idstr) && idstr.matches("\\d+")) {
			int id = Integer.valueOf(idstr);
		    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internet_shop", "root", "1111")) {
		    	CustomerRepository customerRepository = new CustomerRepository(connection);
				return new CustomerRepository(connection).getById(id);
			} catch (SQLException ex) {
			    ex.printStackTrace();
			}
		}
	    return null;
	}
	public Customer getCustomerById(int id) {
	    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internet_shop", "root", "1111")) {
	    	CustomerRepository customerRepository = new CustomerRepository(connection);
			return new CustomerRepository(connection).getById(id);
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}
	    return null;
	}
}
