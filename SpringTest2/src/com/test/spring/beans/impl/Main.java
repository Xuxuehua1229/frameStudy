package com.test.spring.beans.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
   public static void main(String[] args) {
	  ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	  
	  ArithmeticCalculator cal = (ArithmeticCalculator)ac.getBean(ArithmeticCalculator.class);
	  
	  int add = cal.add(3, 7);
	  System.out.println(add);
	  
	  int div = cal.div(10, 0);
	  System.out.println(div);
   }
}
