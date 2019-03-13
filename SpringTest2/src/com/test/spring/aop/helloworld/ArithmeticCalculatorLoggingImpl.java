package com.test.spring.aop.helloworld;

public class ArithmeticCalculatorLoggingImpl implements ArithmeticCalculator{

	@Override
	public int add(int i, int j) {
		System.out.println("ATGUIGU -> The method add begins with[" + i + "," + j +"]");
		int result = i + j;
		System.out.println("ATGUIGU -> The method add ends with " + result);
		return result;
	}

	@Override
	public int sub(int i, int j) {
		System.out.println("ATGUIGU -> The method sub begins with[" + i + "," + j +"]");
        int result = i - j;
        System.out.println("ATGUIGU -> The method sub ends with " + result);
        return result;
	}

	@Override
	public int mul(int i, int j) {
		System.out.println("ATGUIGU -> The method mul begins with[" + i + "," + j +"]");
		int result = i * j;
		System.out.println("ATGUIGU -> The method mul ends with " + result);
		return result;
	}

	@Override
	public int div(int i, int j) {
		System.out.println("ATGUIGU ->The method div begins with[" + i + "," + j +"]");
		int result = i / j;
		System.out.println("ATGUIGU ->The method div ends with " + result);
		return result;
	}

}
