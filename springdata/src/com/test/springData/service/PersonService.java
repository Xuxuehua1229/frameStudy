package com.test.springData.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.springData.bean.Person;
import com.test.springData.repository.PersonCrudRepository;
import com.test.springData.repository.PersonJpaRepository;
import com.test.springData.repository.PersonRepository;

@Service
public class PersonService {
   
   @Autowired
   private PersonRepository personRepository;
   @Autowired
   private PersonCrudRepository personCrudRepository;
   @Autowired
   private PersonJpaRepository personJpaRepository;
   
   @Transactional
   public void savePersonByCrudRepository(List<Person> personList) {
	   
	   //personCrudRepository.delete(persons);
	   
	   personCrudRepository.save(personList);
   }
   
   @Transactional
   public void savePersonJpaRepository(Person person) {
	  Person person2 = personJpaRepository.saveAndFlush(person);
	  System.out.println(person == person2);
   }
   
   @Transactional
   public void updatePersonEmail(String eamil,Integer id) {
	   personRepository.updatePersonEmail(eamil,id);
   } 
}
