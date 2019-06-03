package com.test.springData.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.test.springData.bean.Person;

/**
 * 
 * @author 123
 *
 */
public interface PersonPagingAndSortingRepository extends PagingAndSortingRepository<Person, Integer>{

}
