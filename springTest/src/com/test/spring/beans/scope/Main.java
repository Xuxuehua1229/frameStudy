package com.test.spring.beans.scope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.spring.beans.autowire.Car;

public class Main {
	public static void main(String[] args) {
       ApplicationContext act = new ClassPathXmlApplicationContext("beans-scope.xml");
       Car car = (Car)act.getBean("car");
       Car car2 = (Car)act.getBean("car");
       System.out.println(car == car2);
       
       
	}
}
