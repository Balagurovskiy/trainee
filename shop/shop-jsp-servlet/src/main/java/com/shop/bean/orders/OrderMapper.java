package com.shop.bean.orders;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.shop.bean.currency.Currency;
import com.shop.bean.products.Product;

public class OrderMapper implements RowMapper<Order> {

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
    	return new Order(
	    			resultSet.getInt("orders.id"), 
	    			product, 
	    			null, 
	    			resultSet.getDate("orders.processed_date"), 
	    			1
    			);
	}

}
