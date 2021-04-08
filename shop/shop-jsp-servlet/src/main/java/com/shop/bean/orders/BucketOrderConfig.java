package com.shop.bean.orders;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BucketOrderConfig {
	
	@Bean
	public BucketRepository bucketRepository(DataSource dataSource) {
		return new BucketRepository(dataSource);
	}
	
	@Bean
	public OrdersRepository ordersRepository(DataSource dataSource) {
		return new OrdersRepository(dataSource);
	}
}
