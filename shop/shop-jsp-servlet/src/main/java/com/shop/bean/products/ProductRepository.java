package com.shop.bean.products;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.shop.bean.country.CountryEntity;
import com.shop.bean.currency.CurrencyEntity;

public class ProductRepository {
	
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public ProductRepository(EntityManager entityManager) {
		setEntityManager(entityManager);
	}
	
	private void put(Product p, String name, double price, int currencyId, int countryId) {
		p.setName(name);
		p.setPrice(price);
		p.setCurrency(entityManager.find(CurrencyEntity.class, currencyId));
		p.setCountry(entityManager.find(CountryEntity.class, countryId));
	}
	
	@Transactional
	public void putFood(String name, double price, int currencyId, int countryId){
		Food product = new Food();
		put(product, name, price, currencyId, countryId);
		entityManager.persist(product);
	}
	@Transactional
	public void putStuff(String name, double price, int currencyId, int countryId){
		Stuff product = new Stuff();
		put(product, name, price, currencyId, countryId);
		entityManager.persist(product);
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> getAll() {
		return  entityManager
				.createQuery("SELECT p FROM Product p")
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> getAllFood() {
		return  entityManager
				.createQuery("SELECT f FROM Food f")
				.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Product> getAllStuff() {
		return  entityManager
				.createQuery("SELECT s FROM Stuff s")
				.getResultList();
	}
}
