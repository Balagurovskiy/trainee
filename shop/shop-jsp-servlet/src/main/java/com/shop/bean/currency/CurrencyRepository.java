package com.shop.bean.currency;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class CurrencyRepository {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public CurrencyRepository(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	public void insertCustomer(String name, double koef){
		String sql = "INSERT INTO currency (name, koef) VALUES (?, ?)";
		jdbcTemplate.update(sql, new Object[]{name, koef});
	}
	
}
