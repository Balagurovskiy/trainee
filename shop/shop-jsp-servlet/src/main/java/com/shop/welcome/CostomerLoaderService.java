package com.shop.welcome;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop.bean.customer.Customer;
import com.shop.bean.customer.CustomerRepository;

public class CostomerLoaderService {
	public List<Customer> getAll(){
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internet_shop", "root", "1111")) {
			CustomerRepository customerRepository = new CustomerRepository(connection);
			customers = customerRepository.getAll();
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}
        return customers;
	}
}
