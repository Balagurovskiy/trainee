package com.shop.welcome;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shop.bean.customer.CustomerRepository;

@Configuration
public class WelcomeConfig {
	
	@Bean
	public CustomerLoaderService customerLoaderService(CustomerRepository customerRepository) {
		CustomerLoaderService customerLoaderService = new CustomerLoaderService();
		customerLoaderService.setCustomerRepository(customerRepository);
		return customerLoaderService;
	}
}
