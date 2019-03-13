package com.test.spring.aop.aspect.xml;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
public class VlidationAspect {
	
   public void validateArgs(JoinPoint joinPoint) {
	   System.out.println("validateArgs : " + Arrays.asList(joinPoint.getArgs()));
   }
}
