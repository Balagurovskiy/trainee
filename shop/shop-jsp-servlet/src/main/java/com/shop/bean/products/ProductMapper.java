package com.shop.bean.products;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.shop.bean.currency.Currency;

public class ProductMapper implements RowMapper<Product> {

	@Override
	public Product mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Currency cash = new Currency(
				resultSet.getInt("currencyId"), 
				resultSet.getString("currency.name"), 
				resultSet.getDouble("currency.koef")
				);
    	cash.setAmount(resultSet.getDouble("price"));
    	return new Product(
	    			resultSet.getInt("id"), 
	    			resultSet.getString("name"), 
	    			cash, 
	    			resultSet.getInt("eatable")
    			);
	}

}
