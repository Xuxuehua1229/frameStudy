package com.test.spring.beans;

public class HelloWorld {
    private String name;
    
    public void setName(String name) {
		this.name = name;
	}
    
    public void hello() {
    	System.out.println("Hellow:"+name);
    }
}
