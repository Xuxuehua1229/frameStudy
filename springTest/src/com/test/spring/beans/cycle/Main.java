package com.test.spring.beans.cycle;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
       ClassPathXmlApplicationContext cxc = new ClassPathXmlApplicationContext("beans-cycle.xml");
       Car car = (Car)cxc.getBean("car");
       System.out.println(car);
       cxc.close();
	}
}
