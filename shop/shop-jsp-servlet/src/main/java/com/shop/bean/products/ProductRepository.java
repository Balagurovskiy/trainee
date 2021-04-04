package com.shop.bean.products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop.bean.currency.Currency;

public class ProductRepository {
	
	private Connection connection;
	
	public ProductRepository(Connection connection) {
		this.connection = connection;
	}
	
	public void set(String name, double price, int currencyId, int eatable){
 
		String sql = "INSERT INTO products (name, price, currencyId, eatable) VALUES (?, ?, ?, ?)";
	    try (PreparedStatement statement= connection.prepareStatement(sql)){
		    statement.setString(1, name);
		    statement.setDouble(2, price);
		    statement.setInt(3, currencyId);
		    statement.setInt(4, eatable);
		    statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<Product> getAll() {
	    List<Product> products = new ArrayList<>();
		String sql = "SELECT * FROM internet_shop.products JOIN internet_shop.currency ON currencyId = currency.id";
	    try (PreparedStatement statement= connection.prepareStatement(sql)){

		    ResultSet resultSet = statement.executeQuery();
		    
		    while (resultSet.next()) {
		    	Currency cash = new Currency(resultSet.getInt("currencyId"), resultSet.getString("currency.name"), resultSet.getDouble("currency.koef"));
		    	cash.setAmount(resultSet.getDouble("price"));
		    	products.add(new Product(resultSet.getInt("id"), resultSet.getString("name"), cash, resultSet.getInt("eatable")));
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return products;
	}
}
