package com.shop.productlist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.shop.bean.orders.BucketRepository;
import com.shop.bean.products.Product;
import com.shop.bean.products.ProductRepository;

public class ProductListService {

	private BucketRepository bucketRepository;
	
	public void setBucketRepository(BucketRepository bucketRepository) {
		this.bucketRepository = bucketRepository;
	}
	
	public List<Product> getList() {
		 List<Product> products = new ArrayList<>(20);
	    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internet_shop", "root", "1111")) {
	    	products = new ProductRepository(connection).getAll();
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}
	    return products;
	}
	
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
	public int getBucketSize(int customerId) {
		if ( customerId < 1) {
			throw new IllegalArgumentException();
		}
		return bucketRepository.getAllNotProcessedByCustomerId(customerId).size();
	}
}
