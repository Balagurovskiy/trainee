package com.shop.bean.products;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.shop.EntityManagerProvider;

@Configuration
public class ProductConfig {
	
	@Bean
	public ProductRepository productRepository(EntityManagerProvider entityManagerProvider) {
		return new ProductRepository(entityManagerProvider.get());
	}
}
