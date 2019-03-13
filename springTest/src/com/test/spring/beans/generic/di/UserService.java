package com.test.spring.beans.generic.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User>{
   @Autowired
   private UserRepository userRepository;
   
   
   public void getName(User user) {
	   userRepository.save(user);
   }
}
