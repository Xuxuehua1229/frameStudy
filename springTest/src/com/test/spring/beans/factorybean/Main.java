package com.test.spring.beans.factorybean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
   public static void main(String[] args) {
	  ApplicationContext ac = new ClassPathXmlApplicationContext("beans-factorybean.xml");
	  Car car = (Car)ac.getBean("car");
	  System.out.println(car);
   }
}
