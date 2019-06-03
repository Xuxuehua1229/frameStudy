package com.test.springData.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.test.springData.bean.Person;
import com.test.springData.dao.PersonDao;

/**
 * 
 * @author 123
 *
 */
public interface PersonJpaRepository extends 
     JpaRepository<Person, Integer>,JpaSpecificationExecutor<Person>
      ,PersonDao{
 
}
