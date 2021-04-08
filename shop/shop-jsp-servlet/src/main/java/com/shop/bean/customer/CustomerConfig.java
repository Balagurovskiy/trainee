package com.shop.bean.customer;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {
	@Bean
	public CustomerRepository customerRepository(DataSource datasource) {
		return new CustomerRepository(datasource);
	}
}
