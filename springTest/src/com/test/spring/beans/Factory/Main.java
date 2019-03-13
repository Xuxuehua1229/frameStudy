package com.test.spring.beans.Factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
   public static void main(String[] args) {
      ApplicationContext ac = new ClassPathXmlApplicationContext("beans-factory.xml");
      Car car = (Car)ac.getBean("car1");
      System.out.println(car);
      System.out.println("---------------------------------------");
      Car car2 = (Car)ac.getBean("car2");
      System.out.println(car2);
   }
} 
