package com.shop.account;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shop.bean.customer.CustomerRepository;

@Configuration
public class AccountConfig {
	@Bean
	public CustomerCollectService customerCollectService(@Qualifier("customerRepositoryJpa")CustomerRepository customerRepository) {
		CustomerCollectService ccs = new CustomerCollectService();
		ccs.setCustomerRepository(customerRepository);
		return ccs;
	}
}
