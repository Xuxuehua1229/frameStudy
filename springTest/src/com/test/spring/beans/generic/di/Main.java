package com.test.spring.beans.generic.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
       ApplicationContext ac = new ClassPathXmlApplicationContext("beans-generic-di.xml");
       UserService us = (UserService)ac.getBean("userService");
       us.add();
       us.getName(new User("уехЩ"));
	}
}
