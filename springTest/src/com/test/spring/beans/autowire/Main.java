package com.test.spring.beans.autowire;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
       ApplicationContext ac = new ClassPathXmlApplicationContext("beans-autowire.xml");
       Person person = (Person)ac.getBean("person");
       System.out.println(person);
       System.out.println("==================================================");
       Person person1 = (Person)ac.getBean("person1");
       System.out.println(person1);
       System.out.println("==================================================");
       Person person2 = (Person)ac.getBean("person2");
       System.out.println(person2);
	}
}
