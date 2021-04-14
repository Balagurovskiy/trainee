package com.shop.register;


import org.springframework.transaction.annotation.Transactional;

import com.shop.bean.customer.Customer;
import com.shop.bean.customer.CustomerRepository;

public class CustomerRegistrationService {

	private CustomerRepository customerRepository;

	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Transactional
	public Customer createAndReturn(String name){
		if(customerRepository.count(name) == 0) {
			customerRepository.create(name, 20_000.0, 2);
			return customerRepository.getByName(name);
		}
        return null;
	}
}
