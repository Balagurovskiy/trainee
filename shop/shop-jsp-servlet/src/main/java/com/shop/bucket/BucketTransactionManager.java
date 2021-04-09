package com.shop.bucket;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shop.bean.currency.Currency;
import com.shop.bean.customer.CustomerRepository;
import com.shop.bean.orders.BucketRepository;
import com.shop.bean.orders.Order;
import com.shop.bean.products.Product;


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
	public List<Order> getList(int customerId) {
		return bucketRepository.getAllNotProcessedByCustomerId(customerId);
	}
	@Transactional
	public void submitOrder(int customerId) {
    	bucketRepository.setToProcessedByCustomerId(customerId);
    	bucketRepository.clearProcessedOrders(customerId);
	}
	@Transactional
	public void updateCustomerCash(int customerId, double amount) {
		customerRepository.updateCash(customerId, amount);
	}
	public double totalPrice(List<Order> products, Currency tagetCurrency) {
		return products.stream()
						.map(Order::getProduct)
						.map(Product::getPrice)
						.map(curr -> curr.convertTo(tagetCurrency.getKoef()))
						.reduce(0.0, Double::sum);
	}
}
