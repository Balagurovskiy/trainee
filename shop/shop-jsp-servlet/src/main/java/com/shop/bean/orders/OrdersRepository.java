package com.shop.bean.orders;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class OrdersRepository {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public OrdersRepository(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	public void create(int customerId, int productId){
		String sql = "INSERT INTO orders (customerId, productId, processed_date) VALUES (?, ?, CURDATE())";
		jdbcTemplate.update(sql, new Object[]{customerId, productId});
	}
	public List<Order> getCustomerHistory(int customerId) {
		String sql = "CALL internet_shop.get_customer_history(?)";
		return jdbcTemplate.query(sql,
			        new Object[]{customerId},
			        new OrderMapper()
		        );
	}
	
}
