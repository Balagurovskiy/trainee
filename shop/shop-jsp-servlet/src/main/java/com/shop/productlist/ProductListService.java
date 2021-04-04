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
		    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internet_shop", "root", "1111")) {
		    	new BucketRepository(connection).create(customerId, Integer.valueOf(productIdstr));
			} catch (SQLException ex) {
			    ex.printStackTrace();
			}
		} else {
			throw new IllegalArgumentException();
		}
	}
	public int getBucketSize(int customerId) {
		int size = 0;
		if ( customerId < 1) {
			throw new IllegalArgumentException();
		}
	    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internet_shop", "root", "1111")) {
	    	size = new BucketRepository(connection).getAllNotProcessedByCustomerId(customerId).size();
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}
		return size;
	}
}
