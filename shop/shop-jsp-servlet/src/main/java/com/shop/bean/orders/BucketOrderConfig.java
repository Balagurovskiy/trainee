package com.shop.bean.orders;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shop.EntityManagerProvider;

@Configuration
public class BucketOrderConfig {
	
	@Bean
	public BucketRepository bucketRepository(EntityManagerProvider entityManagerProvider) {
		return new BucketRepository(entityManagerProvider.get());
	}
	
	@Bean
	public OrdersRepository ordersRepository(EntityManagerProvider entityManagerProvider) {
		return new OrdersRepository(entityManagerProvider.get());
	}
}
