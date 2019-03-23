package com.test.struts2.app.model;

import java.util.Date;

public class Customer {
	private int age;
	private Date birth;
	
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public Date getBirth() {
		return birth;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getAge() {
		return age;
	}
	@Override
	public String toString() {
		return "Customer [age=" + age + ", birth=" + birth + "]";
	}
}
