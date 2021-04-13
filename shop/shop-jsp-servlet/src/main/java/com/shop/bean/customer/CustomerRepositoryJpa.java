package com.shop.bean.customer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import com.shop.bean.currency.CurrencyEntity;

public class CustomerRepositoryJpa implements CustomerRepository{
	
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public CustomerRepositoryJpa(EntityManager entityManager) {
		setEntityManager(entityManager);
	}
	
	@SuppressWarnings("unchecked")
	public List<Customer> findAll() {
		return  entityManager
						.createQuery("SELECT c FROM Customer c")
						.getResultList();
	}
	
	public long count(String name) {
		return (long) entityManager.createQuery("SELECT COUNT(c) FROM Customer c WHERE c.name = :name").setParameter("name", name).getSingleResult();
	}
	
	public void create(String name, double cash, int currencyId) {
		entityManager.getTransaction().begin();
		Customer customerEntity = new Customer();
		customerEntity.setBucket(new ArrayList<>());
		customerEntity.setOrders(new ArrayList<>());
		customerEntity.setCash(cash);
		customerEntity.setName(name);
		customerEntity.setCurrency(entityManager.find(CurrencyEntity.class, currencyId));
		customerEntity.setPass("password");
		entityManager.persist(customerEntity);
		entityManager.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public Customer getByName(String name){
		String query = "SELECT c FROM Customer c WHERE c.name=(:name)";
		return (Customer) entityManager.createQuery(query)
											.setParameter("name", name)
											.getResultList()
											.stream()
												.findFirst()
												.orElse(null);
	}

	public Customer getById(int id) {
		return (Customer) entityManager.find(Customer.class, id);
	}
	public Customer getAndRefresh(Customer customer) {
		entityManager.refresh(customer);
		return (Customer) entityManager.find(Customer.class, customer.getId());
	}
	
	public EntityManager callManager() {
		return entityManager;
	}

	public void updateCash(Customer customer, double amount) {
		entityManager.persist(customer);;
		entityManager.getTransaction().begin();
		customer.setCash(amount);
		entityManager.getTransaction().commit();
	}
}
