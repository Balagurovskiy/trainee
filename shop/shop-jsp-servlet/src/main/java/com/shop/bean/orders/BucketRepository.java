package com.shop.bean.orders;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class BucketRepository {
 
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public BucketRepository(EntityManager entityManager) {
		setEntityManager(entityManager);
	}
	
	public void create(int customerId, int productId){
		entityManager.getTransaction().begin();
		String sql = "INSERT INTO bucket (customerId, productId, processed) VALUES (?, ?, 0)";
		entityManager.createNativeQuery(sql)
						.setParameter(1, customerId)
						.setParameter(2, productId)
						.executeUpdate();
		entityManager.getTransaction().commit();
	}
	public void process(int customerId){
		entityManager.getTransaction().begin();
		String sql = "UPDATE bucket SET processed=1 WHERE customerId=?";
		entityManager.createNativeQuery(sql)
						.setParameter(1, customerId)
						.executeUpdate();
		entityManager.getTransaction().commit();
	}
	@SuppressWarnings("unchecked")
	public List<Bucket> getAllNotProcessedByCustomerId(int customerId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Bucket> personCriteria = cb.createQuery(Bucket.class);
		Root<Bucket> personRoot = personCriteria.from(Bucket.class);
		personCriteria.select(personRoot);
		personCriteria.where(cb.equal(personRoot.get("customer").get("id"), customerId));
		
		return entityManager.createQuery(personCriteria).getResultList();
	}
	
 
}
