package com.shop.bucket;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.shop.bean.currency.Currency;
import com.shop.bean.orders.Order;
import com.shop.bean.products.Product;

public class BucketMapper implements RowMapper<Order> {

	@Override
	public Order mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Currency curr = new Currency(
				resultSet.getInt("currency.id"), 
				resultSet.getString("currency.name"), 
				resultSet.getDouble("currency.koef")
				);
    	curr.setAmount(resultSet.getDouble("products.price"));
    	Product product = new Product(
    			resultSet.getInt("products.id"), 
    			resultSet.getString("products.name"), 
    			curr, 
    			resultSet.getInt("products.eatable")
    			);
    	Order order = new Order(
    			resultSet.getInt("bucket.id"), 
    			product, 
    			null, 
    			null, 
    			resultSet.getInt("bucket.processed")
    			);
		return order;
	}

}
