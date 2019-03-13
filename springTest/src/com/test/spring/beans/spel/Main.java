package com.test.spring.beans.spel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
       ApplicationContext ac = new ClassPathXmlApplicationContext("beans-spel.xml");
       Address address = (Address)ac.getBean("address");
       System.out.println(address);
       
       Car car = (Car)ac.getBean("car");
       System.out.println(car);
       
       Person person = (Person)ac.getBean("person");
       System.out.println(person);
	}
}
