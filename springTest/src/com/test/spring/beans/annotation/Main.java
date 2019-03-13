package com.test.spring.beans.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.spring.beans.annotation.controller.UserController;
import com.test.spring.beans.annotation.repository.UserRepository;
import com.test.spring.beans.annotation.service.UserService;

public class Main {
   public static void main(String[] args) {
	   ApplicationContext ac = new ClassPathXmlApplicationContext("beans-annotation.xml");
	   
	   /*TestObject to = (TestObject)ac.getBean("testObject");
	   System.out.println(to);*/
	   
	   UserController uc = (UserController)ac.getBean("userController");
	   System.out.println(uc);
	   uc.execute();
	   
	   /*UserService us = (UserService)ac.getBean("userService");
	   System.out.println(us);
	   
	   UserRepository ur = (UserRepository)ac.getBean("userRepository");
	   System.out.println(ur);*/
	  
   } 
}
