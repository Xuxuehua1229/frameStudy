package com.test.spring.beans.spel;

public class Car {
	private String brand;
	private double price;
	private double typePerimeter;//ÂÖÌ¥µÄÖÜ³¤
	
	public Car() {
		System.out.println("Car's Coustructor");
	}
	public Car(String brand, double price) {
		super();
		this.brand = brand;
		this.price = price;
	}
	public double getTypePerimeter() {
		return typePerimeter;
	}
	public void setTypePerimeter(double typePerimeter) {
		this.typePerimeter = typePerimeter;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Car [brand=" + brand + ", price=" + price + ", typePerimeter=" + typePerimeter + "]";
	}
}
