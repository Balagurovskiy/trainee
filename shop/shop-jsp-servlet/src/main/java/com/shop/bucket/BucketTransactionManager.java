package com.shop.bucket;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import com.shop.bean.customer.Customer;
import com.shop.bean.customer.CustomerRepository;
import com.shop.bean.orders.Bucket;
import com.shop.bean.orders.BucketRepository;
import com.shop.bean.orders.Order;

public class BucketTransactionManager {

	private CustomerRepository customerRepository;
	private BucketRepository bucketRepository;
	
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	public void setBucketRepository(BucketRepository bucketRepository) {
		this.bucketRepository = bucketRepository;
	}

	@Transactional(readOnly = true)
	public List<Bucket> getListCriteria(int customerId) {
		return bucketRepository.getAllNotProcessedByCustomerId(customerId)
										.stream()
										.filter(b-> !(b instanceof Order))
										.collect(Collectors.toList());
	}
	@Transactional
	public void processBucket(int customerId) {
		bucketRepository.process(customerId);
	}

	public void clearBucket(Customer customer) {
		customerRepository.callManager().persist(customer);
		customerRepository.callManager().getTransaction().begin();
		customer.getBucket().forEach(customerRepository.callManager()::remove);
		customer.getBucket().clear();
		customerRepository.callManager().getTransaction().commit();
	}
	
	public void deleteFromBucket(Customer customer, int bucketId) {
		customerRepository.callManager().persist(customer);
		customerRepository.callManager().getTransaction().begin();
		Optional<Bucket> be = customer.getBucket().stream()
				.filter(b -> b.getId() == bucketId)
				.peek(customerRepository.callManager()::remove)
				.findFirst();
		if (be.isPresent()) {
			customer.getBucket().remove(be.get());
		}
		customerRepository.callManager().getTransaction().commit();
	}
	
	@Transactional
	public void updateCustomerCash(Customer customer, double amount) {
		customerRepository.updateCash(customer, amount);
	}
	
	public double totalPriceEntity(List<Bucket> products, double koef) {
		return products.stream()
						.map(Bucket::getProduct)
						.map(p -> p.getCurrency().convertTo(p.getPrice(), koef))
						.reduce(0.0, Double::sum);
	}
}
