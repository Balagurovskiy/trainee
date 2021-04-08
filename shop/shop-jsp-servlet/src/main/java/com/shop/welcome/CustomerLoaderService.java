package com.shop.welcome;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shop.bean.customer.Customer;
import com.shop.bean.customer.CustomerRepository;

public class CustomerLoaderService {
	
	private CustomerRepository customerRepository;
	
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	@Transactional(readOnly = true)
	public List<Customer> getAll(){
        return customerRepository.getAll();
	}
}
