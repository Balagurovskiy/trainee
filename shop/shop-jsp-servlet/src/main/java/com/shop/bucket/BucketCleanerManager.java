package com.shop.bucket;

import org.springframework.transaction.annotation.Transactional;

import com.shop.bean.customer.Customer;

public class BucketCleanerManager {
	private BucketTransactionManager bucketTransactionManager;
	public void setBucketTransactionManager(BucketTransactionManager bucketTransactionManager) {
		this.bucketTransactionManager = bucketTransactionManager;
	}
	@Transactional
	public void clean(Customer customer) {
		bucketTransactionManager.clearBucket(customer);
	}
	@Transactional
	public void cleanById(Customer customer, int bucketId) {
		bucketTransactionManager.deleteFromBucket(customer, bucketId);
	}
}
