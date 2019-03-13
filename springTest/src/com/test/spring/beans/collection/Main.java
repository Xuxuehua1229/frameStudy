package com.test.spring.beans.collection;

import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
       ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
       Person person3 = (Person)context.getBean("person3");
       System.out.println(person3);
       
       System.out.println("==============================================");
       NewPerson person = (NewPerson)context.getBean("newPerson");
       System.out.println(person);
       
       System.out.println("==============================================");
       DataSource properties = (DataSource)context.getBean("dataSource");
       System.out.println(properties);
       
       System.out.println("==================person2============================");
       Person person2 = (Person)context.getBean("person4");
       System.out.println(person2);
       
       System.out.println("=====================person4=========================");
       Person person4 = (Person)context.getBean("person5");
       System.out.println(person4);
       
       System.out.println("====================person5==========================");
       Person person5 = (Person)context.getBean("person6");
       System.out.println(person5);
	}
}
