package com.shop.bean.customer;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.shop.bean.currency.Currency;

public class CustomerMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Currency cash = new Currency(
				resultSet.getInt("currencyId"), 
				resultSet.getString("currency.name"), 
				resultSet.getDouble("currency.koef")
				);
		cash.setAmount(resultSet.getDouble("cash"));
		return new Customer(resultSet.getInt("id"), resultSet.getString("name"), cash);
	}

}
