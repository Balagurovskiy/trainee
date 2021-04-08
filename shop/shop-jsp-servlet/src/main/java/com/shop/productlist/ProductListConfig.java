package com.shop.productlist;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shop.bean.orders.BucketRepository;
import com.shop.bean.products.ProductRepository;

@Configuration
public class ProductListConfig {

	@Bean
	public ProductListService productListService(BucketRepository bucketRepository,
													ProductRepository productRepository) {
		ProductListService pls = new ProductListService();
		pls.setBucketRepository(bucketRepository);
		pls.setProductRepository(productRepository);
		return pls;
	}
}
