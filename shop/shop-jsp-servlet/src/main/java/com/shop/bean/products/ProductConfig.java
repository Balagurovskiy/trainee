package com.shop.bean.products;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {
	
	@Bean
	public ProductRepository productRepository(DataSource dataSource) {
		return new ProductRepository(dataSource);
	}
}
