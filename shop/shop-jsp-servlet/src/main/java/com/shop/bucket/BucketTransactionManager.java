package com.shop.bucket;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shop.bean.currency.Currency;
import com.shop.bean.currency.CurrencyEntity;
import com.shop.bean.customer.CustomerRepository;
import com.shop.bean.orders.BucketEntity;
import com.shop.bean.orders.BucketRepository;
import com.shop.bean.orders.BucketRepositoryCriteria;
import com.shop.bean.orders.Order;
import com.shop.bean.products.Product;

public class BucketTransactionManager {

	private CustomerRepository customerRepository;
	private BucketRepository bucketRepository;
	private BucketRepositoryCriteria bucketRepositoryCriteria;
	
	public void setBucketRepositoryCriteria(BucketRepositoryCriteria bucketRepositoryCriteria) {
		this.bucketRepositoryCriteria = bucketRepositoryCriteria;
	}
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

	@Transactional(readOnly = true)
	public List<BucketEntity> getListCriteria(int customerId) {
		return bucketRepositoryCriteria.getAllNotProcessedByCustomerId(customerId);
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
	public double totalPriceEntity(List<BucketEntity> products, double koef) {
		return products.stream()
						.map(BucketEntity::getProduct)
						.map(p -> p.getCurrency().convertTo(p.getPrice(), koef))
						.reduce(0.0, Double::sum);
	}
	public double totalPrice(List<Order> products, Currency tagetCurrency) {
		return products.stream()
						.map(Order::getProduct)
						.map(Product::getPrice)
						.map(curr -> curr.convertTo(tagetCurrency.getKoef()))
						.reduce(0.0, Double::sum);
	}
}
