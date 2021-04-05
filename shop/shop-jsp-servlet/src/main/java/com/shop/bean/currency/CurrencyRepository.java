package com.shop.bean.currency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CurrencyRepository {
	
	private Connection connection;
	
	public CurrencyRepository(Connection connection) {
		this.connection = connection;
	}
	
	public void insertCustomer(String name, double koef){
		String sql = "INSERT INTO currency (name, koef) VALUES (?, ?)";
	    try (PreparedStatement statement= connection.prepareStatement(sql)){
		    statement.setString(1, name);
		    statement.setDouble(2, koef);
		    statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
