package com.shop.register;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.shop.bean.customer.Customer;
import com.shop.bean.customer.CustomerRepository;

public class CustomerRegistrationService {
	public Customer createAndReturn(String name){
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internet_shop", "root", "1111")) {
        	CustomerRepository customerRepository = new CustomerRepository(connection);
			if(customerRepository.create(name, 20_000.0, 2)) {
				return customerRepository.getByName(name);
			}
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}
        return null;
	}
}
