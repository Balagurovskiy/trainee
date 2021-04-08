package com.shop.bean.customer;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;


public class CustomerRepository {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public CustomerRepository(DataSource dataSource) {
		setDataSource(dataSource);
	}
	public boolean create(String name, double cash, int currencyId){
		String sqlTest = "SELECT COUNT(*) FROM internet_shop.customers WHERE customers.name=?";
		boolean customerExists = jdbcTemplate.queryForObject(sqlTest,
														        new Object[]{name},
														        (rs, rowNum) ->{														        	
														        	return rs.getInt(1) > 0;
														        });
		if (customerExists) {
			return false;
		}
		String sql = "INSERT INTO internet_shop.customers (name, cash, currencyId, pass) VALUES (?, ?, ?, '111')";
		jdbcTemplate.update(sql, new Object[]{name, cash, currencyId});
	    return true;
	}
	
	
	public void updateCash(int customerId, double amount){
		String sql = "UPDATE internet_shop.customers SET cash=? WHERE id=?";
		jdbcTemplate.update(sql, new Object[]{amount, customerId});
	}
	
	
	public Customer getByName(String name){
		String sql = "SELECT * FROM internet_shop.customers JOIN internet_shop.currency ON currencyId = currency.id WHERE customers.name=?";
		return jdbcTemplate.queryForObject(sql,
										        new Object[]{name},
										        new CustomerMapper()
										        );
	}
	
	public Customer getById(int id){
		String sql = "SELECT * FROM internet_shop.customers JOIN internet_shop.currency ON currencyId = currency.id WHERE customers.id=?";
		return jdbcTemplate.queryForObject(sql,
		        new Object[]{id},
		        new CustomerMapper()
		        );
	}
	
	public List<Customer> getAll() {
	    List<Customer> customers = new ArrayList<>();
		String sql = "SELECT * FROM internet_shop.customers JOIN internet_shop.currency ON currencyId = currency.id";
		return jdbcTemplate.query(sql,
		        new Object[]{},
		        new CustomerMapper()
		        );
	}
}
