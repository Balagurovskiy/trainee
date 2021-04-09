package com.shop.productlist;

import java.util.List;
import java.util.Objects;

import org.springframework.transaction.annotation.Transactional;

import com.shop.bean.orders.BucketRepository;
import com.shop.bean.products.Product;
import com.shop.bean.products.ProductRepository;

public class ProductListService {

	private BucketRepository bucketRepository;
	private ProductRepository productRepository;
	
	public void setBucketRepository(BucketRepository bucketRepository) {
		this.bucketRepository = bucketRepository;
	}
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	@Transactional(readOnly = true)
	public List<Product> getList() {
    	return productRepository.getAll();
	}
	@Transactional
	public void putToTheBucket(int customerId, String productIdstr) {
		if (Objects.isNull(productIdstr) || customerId < 1) {
			throw new IllegalArgumentException();
		}
		if (productIdstr.matches("\\d+") ) {
	    	bucketRepository.create(customerId, Integer.valueOf(productIdstr));
		} else {
			throw new IllegalArgumentException();
		}
	}
	@Transactional(readOnly = true)
	public int getBucketSize(int customerId) {
		if ( customerId < 1) {
			throw new IllegalArgumentException();
		}
		return bucketRepository.getAllNotProcessedByCustomerId(customerId).size();
	}
}
