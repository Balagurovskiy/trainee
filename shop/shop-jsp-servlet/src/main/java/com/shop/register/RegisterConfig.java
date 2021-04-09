package com.shop.register;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shop.bean.customer.CustomerRepository;

@Configuration
public class RegisterConfig {
	@Bean
	public CustomerRegistrationService customerRegistrationService(CustomerRepository customerRepository) {
		CustomerRegistrationService crs = new CustomerRegistrationService();
		crs.setCustomerRepository(customerRepository);
		return crs;
	}
}
