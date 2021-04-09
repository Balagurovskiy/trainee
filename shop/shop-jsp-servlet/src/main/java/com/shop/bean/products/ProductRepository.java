package com.shop.bean.products;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;


public class ProductRepository {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public ProductRepository(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	public void set(String name, double price, int currencyId, int eatable){
 
		String sql = "INSERT INTO products (name, price, currencyId, eatable) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, new Object[]{name, price, currencyId, eatable});
	}
	
	public List<Product> getAll() {
		String sql = "SELECT * FROM internet_shop.products JOIN internet_shop.currency ON currencyId = currency.id";
		return jdbcTemplate.query(sql,
		        new Object[]{},
		        new ProductMapper()
		        );
	}
}
