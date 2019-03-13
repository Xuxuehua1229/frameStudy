package com.test.spring.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
    	/*1.创建Spring的IOC容器对象
    	ApplicationContext 代表IOC容器
    	ClassPathXmlApplicationContext: 是ApplicationContext 接口实现类,从类路径下加载配置文件  
    	FileSystemXmlApplicationContext:从文件系统中加载配置文件
    	ConfigurableApplicationContext:是ApplicationContext的子接口，以及FileSystemXmlApplicationContext
    	和ClassPathXmlApplicationContext是ConfigurableApplicationContext实现类。新增了两个方法：refresh()、
    	close():具有启动、刷新和关闭上下文的能力
    	*/
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		/*2.从IOC容器中获取Bean实例
		  利用id定位到IOC容器中的bean*/
		//HelloWorld helloWorld = (HelloWorld)ctx.getBean("helloWorld");
		/*利用类型返回IOC容器中的bean，但要求IOC容器中只能有一个该类型的bean
		 HelloWorld helloWorld = ctx.getBean(HelloWorld.class);*/
		//3.调用hello方法
		//helloWorld.hello();
		
		Car car1 = (Car) ctx.getBean("car1");
		System.out.println(car1);
		car1 = (Car)ctx.getBean("car2");
		System.out.println(car1);
		
		Person person = (Person)ctx.getBean("person");
		System.out.println(person);
		
		Person person1 = (Person)ctx.getBean("person1");
		System.out.println(person1);
		
		
		
		
		
		
		
	}
}
