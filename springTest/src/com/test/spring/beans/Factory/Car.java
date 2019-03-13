package com.test.spring.beans.Factory;

public class Car {
	private String brand;
	private double price;
	
	public Car(String brand, double price) {
		super();
		this.brand = brand;
		this.price = price;
	}

	public Car() {
		System.out.println("Car's Constructor");
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		System.out.println("set Brand.....");
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Car [brand=" + brand + ", price=" + price + "]";
	}

}
