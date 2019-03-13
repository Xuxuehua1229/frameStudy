package com.test.spring.beans.spel;

public class Person {
	private String name;
	private String age;
	private Address address;
	private Car car;
	private String city;//引用Address里的city
	private String info;//根据car的price确定info的内容：如果car 的 price > 300000 : 金领  否则为白领
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", address=" + address + ", car=" + car + ", city=" + city
				+ ", info=" + info + "]";
	}
}
