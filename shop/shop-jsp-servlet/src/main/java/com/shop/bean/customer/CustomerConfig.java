package com.shop.bean.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shop.EntityManagerProvider;

@Configuration
public class CustomerConfig {

	@Bean
	public CustomerRepository customerRepositoryJpa(EntityManagerProvider entityManagerProvider) {
		return new CustomerRepositoryJpa(entityManagerProvider.get());
	}
}
