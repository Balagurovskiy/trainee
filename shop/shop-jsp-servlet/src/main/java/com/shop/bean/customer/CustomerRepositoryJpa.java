package com.shop.bean.customer;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shop.EntityManagerProvider;

@Repository
public class CustomerRepositoryJpa {
	
	@Autowired 
	private EntityManagerProvider entityManagerProvider;
	
	private EntityManager entityManager;
	
	@PostConstruct
	public void init() {
		entityManager = entityManagerProvider.get();
	}
 	
	public List<CustomerEntity> findAll() {
		return entityManager.createQuery("SELECT c FROM CustomerEntity c").getResultList();
	}
	
	public int count() {
		return (int) entityManager.createQuery("SELECT COUNT(c) FROM CustomerEntity c").getSingleResult();
	}
	
	public void create(String name, double cash, int currencyId) {
		String sql = "INSERT INTO internet_shop.customers (name, cash, currencyId, pass) VALUES (?, ?, ?, '111')";
		entityManager.createNativeQuery(sql)
	    .setParameter(1, name)
	    .setParameter(2, cash)
	    .setParameter(3, currencyId)
	    .executeUpdate();
	}
	
	public CustomerEntity getByName(String name){
		String query = "SELECT c FROM CustomerEntity c WHERE LOWER(a.name) = LOWER(:name)";
		return (CustomerEntity) entityManager.createQuery(query)
												.setParameter("name", name)
												.getSingleResult();
	}
}
