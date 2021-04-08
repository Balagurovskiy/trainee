package com.shop.account;

import java.util.Objects;

import org.springframework.transaction.annotation.Transactional;

import com.shop.bean.customer.Customer;
import com.shop.bean.customer.CustomerRepository;

public class CustomerCollectService {
	
	private CustomerRepository customerRepository;
	
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	@Transactional
	public Customer getCustomerById(String idstr) {
		if (Objects.nonNull(idstr) && idstr.matches("\\d+")) {
			int id = Integer.valueOf(idstr);
			return customerRepository.getById(id);
		}
	    return null;
	}
	@Transactional
	public Customer getCustomerById(int id) {
		return customerRepository.getById(id);
	}
}
