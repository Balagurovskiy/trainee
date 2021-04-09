package com.shop;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Component;

@Component
public class EntityManagerProvider {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	public EntityManagerProvider() {
		entityManagerFactory = Persistence.createEntityManagerFactory("shop");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	public EntityManager get() {
		return entityManager;
	}
	@PreDestroy
	public void close() {
		entityManager.close();
		entityManagerFactory.close();
	}
}
