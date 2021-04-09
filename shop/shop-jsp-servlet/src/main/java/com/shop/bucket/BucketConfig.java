package com.shop.bucket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shop.bean.customer.CustomerRepository;
import com.shop.bean.orders.BucketRepository;
import com.shop.bucket.actions.BucketAction;
import com.shop.bucket.actions.BucketCleaner;
import com.shop.bucket.actions.BucketDelete;
import com.shop.bucket.actions.BucketOrderSubmiter;

@Configuration
public class BucketConfig {
	@Bean
	public BucketLoaderService bucketLoaderService(BucketTransactionManager bucketTransactionManager) {
		BucketLoaderService bls = new BucketLoaderService();
		bls.setBucketTransactionManager(bucketTransactionManager);
		return bls;
	}
	
	@Bean
	public BucketTransactionManager bucketTransactionManager(CustomerRepository customerRepository,
																BucketRepository bucketRepository) {
		BucketTransactionManager btm = new BucketTransactionManager();
		btm.setBucketRepository(bucketRepository);
		btm.setCustomerRepository(customerRepository);
		return btm;
	}
	@Bean
	public BucketCleanerManager bucketCleanerManager(BucketRepository bucketRepository) {
		BucketCleanerManager bcm = new BucketCleanerManager();
		bcm.setBucketRepository(bucketRepository);
		return bcm;
	}
	@Bean
	public BucketAction bucketCleaner(BucketCleanerManager bucketCleanerManager) {
		BucketCleaner bc = new BucketCleaner("clean");
		bc.setBucketCleanerManager(bucketCleanerManager);
		return bc;
	}
	@Bean
	public BucketAction bucketDelete(BucketCleanerManager bucketCleanerManager,
										BucketLoaderService bucketLoaderService) {
		BucketDelete bd = new BucketDelete("delete");
		bd.setBucketCleanerManager(bucketCleanerManager);
		bd.setBucketLoaderService(bucketLoaderService);
		return bd;
	}
	@Bean
	public BucketAction bucketOrderSubmiter(BucketTransactionManager bucketTransactionManager) {
		BucketOrderSubmiter bos = new BucketOrderSubmiter("buy");
		bos.setBucketTransactionManager(bucketTransactionManager);
		return bos;
	}
}
