package com.shop.bean.orders;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shop.EntityManagerProvider;
@Repository
public class BucketRepositoryCriteria {
 
	@Autowired 
	private EntityManagerProvider entityManagerProvider;
	private EntityManager entityManager;
	
	@PostConstruct
	public void init() {
		entityManager = entityManagerProvider.get();
	}
	
	public List<BucketEntity> getAllNotProcessedByCustomerId(int customerId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<BucketEntity> personCriteria = cb.createQuery(BucketEntity.class);
		Root<BucketEntity> personRoot = personCriteria.from(BucketEntity.class);
		personCriteria.select(personRoot);
		personCriteria.where(cb.equal(personRoot.get("customer").get("id"), customerId));
		return entityManager.createQuery(personCriteria).getResultList();
	}
}
