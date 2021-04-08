package com.shop.bucket;

import org.springframework.transaction.annotation.Transactional;

import com.shop.bean.orders.BucketRepository;

public class BucketCleanerManager {
	private BucketRepository bucketRepository;
	public void setBucketRepository(BucketRepository bucketRepository) {
		this.bucketRepository = bucketRepository;
	}
	@Transactional
	public void clean(int customerId) {
    	bucketRepository.clearUnProcessedOrders(customerId);
	}
	@Transactional
	public void cleanById(int orderId) {
    	bucketRepository.removeOrderById(orderId);
	}
}
