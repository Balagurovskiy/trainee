package com.shop.bean.orders;

import java.util.List;

import javax.persistence.EntityManager;

public class OrdersRepository {

	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public OrdersRepository(EntityManager entityManager) {
		setEntityManager(entityManager);
	}
	
	
	public void create(int customerId, int productId){
		String sql = "INSERT INTO orders (customerId, productId, processed_date) VALUES (?, ?, CURDATE())";
		entityManager.createNativeQuery(sql)
						.setParameter(1, customerId)
						.setParameter(2, productId)
						.executeUpdate();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Order> getCustomerHistory(int customerId) {
		String sql = "CALL internet_shop.get_customer_history(?)";
		return entityManager.createNativeQuery(sql)
				.setParameter(1, customerId)
				.getResultList();
	}
}
