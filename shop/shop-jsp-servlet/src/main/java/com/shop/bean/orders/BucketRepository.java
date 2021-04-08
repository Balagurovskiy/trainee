package com.shop.bean.orders;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.shop.bucket.BucketMapper;

public class BucketRepository {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public void create(int customerId, int productId){
		String sql = "INSERT INTO bucket (customerId, productId, processed) VALUES (?, ?, 0)";
		jdbcTemplate.update(sql, new Object[]{customerId, productId});
	}
	
	public List<Order> getAllNotProcessedByCustomerId(int customerId) {
	    List<Order> p = new ArrayList<>();
		String sql = "SELECT * FROM internet_shop.bucket "
						+"JOIN internet_shop.products ON productId = products.id "
					    +"JOIN internet_shop.currency ON products.currencyId = currency.id "
					+"WHERE customerId = ? AND processed = 0";
		return jdbcTemplate.query(sql,
		        new Object[]{customerId},
		        new BucketMapper()
		        );
	}
	
	public void clearProcessedOrders(int customerId){
		String sql = "DELETE FROM internet_shop.bucket  WHERE processed=1 AND customerId=?";
		jdbcTemplate.update(sql, new Object[]{customerId});
	}
	public void removeOrderById(int orderId){
		String sql = "DELETE FROM internet_shop.bucket  WHERE id=?";
		jdbcTemplate.update(sql, new Object[]{orderId});
	}
	public void clearUnProcessedOrders(int customerId){
		String sql = "DELETE FROM internet_shop.bucket  WHERE processed=0 AND customerId=?";
		jdbcTemplate.update(sql, new Object[]{customerId});
	}
	public void setToProcessedByCustomerId(int customerId){
		String sql = "UPDATE internet_shop.bucket SET processed=1 WHERE customerId=?";
		jdbcTemplate.update(sql, new Object[]{customerId});
	}
}
