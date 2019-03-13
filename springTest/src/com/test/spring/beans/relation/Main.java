package com.test.spring.beans.relation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.spring.beans.autowire.Address;
import com.test.spring.beans.autowire.Person;

public class Main {
	public static void main(String[] args) {
       ApplicationContext ac = new ClassPathXmlApplicationContext("beans-relation.xml");
       //Address address = (Address) ac.getBean("address");
       //System.out.println(address);
       System.out.println("==============================================");
       Address address1 = (Address)ac.getBean("address1");
       System.out.println(address1);
       System.out.println("==============================================");
       Address address2 = (Address)ac.getBean("address2");
       System.out.println(address2);
       
       System.out.println("==============================================");
       Person person = (Person)ac.getBean("person");
       System.out.println(person);
	}
}
