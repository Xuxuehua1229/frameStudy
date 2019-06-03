package com.test.springData.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import com.test.springData.bean.Person;
import com.test.springData.dao.PersonDao;

public class PersonJpaRepositoryImpl implements PersonDao{
	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public void test() {
		Person person = entityManager.find(Person.class, 11);
		System.out.println("------>" + person);
	}

}
