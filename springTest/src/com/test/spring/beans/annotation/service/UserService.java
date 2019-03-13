package com.test.spring.beans.annotation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.test.spring.beans.annotation.repository.UserRepository;

@Service
public class UserService {
	//方式一
	@Autowired
	@Qualifier("userJdbcRepositoryImpl")
	private UserRepository userRepository;
	
	/*
	 * 方式二
	@Autowired
    @Qualifier("userJdbcRepositoryImpl")
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}*/
	
	/*
	 * 方式三
	@Autowired
	public void setUserRepository(@Qualifier("userJdbcRepositoryImpl") UserRepository userRepository) {
		this.userRepository = userRepository;
	}*/
	
    public void add() {
    	System.out.println("UserService add....");
    	userRepository.save();
    }
}
