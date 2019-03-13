package com.test.spring.aop.aspect;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
   public static void main(String[] args) {
	  ApplicationContext ac = new ClassPathXmlApplicationContext("beans-aspect.xml");
	  ArithmeticCalculator cal = (ArithmeticCalculator)ac.getBean("arithmeticCalculator");
	  
	  System.out.println(cal.getClass().getName());//是否是代理对象
	  int addNum = cal.add(10, 200);
	  System.out.println(addNum);
	  
	  int divNum = cal.div(1000, 10);
	  System.out.println(divNum);
   }
}
