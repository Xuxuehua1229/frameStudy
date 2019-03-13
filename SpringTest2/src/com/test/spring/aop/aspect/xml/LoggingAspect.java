package com.test.spring.aop.aspect.xml;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class LoggingAspect {
	
	public void beforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		Object[] objects = joinPoint.getArgs();
		System.out.println("The method " + methodName + " begins with " + Arrays.asList(objects));
	}

	
	public void afterMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		Object[] objects = joinPoint.getArgs();
		System.out.println("The method " + methodName + " ends with " + Arrays.asList(objects));
	}
	
	public void afterReturning(JoinPoint joinPoint,Object result) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " afterReturning with " + result);
	}
	
	
	public void afterThrowing(JoinPoint joinPoint,Exception e) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " afterThrowing with " + e);
	}
	
	public Integer aroundMethod(ProceedingJoinPoint pjp) {
		//执行目标方法
		Integer resultMethod = null;
		String methodName = pjp.getSignature().getName();
		try {
			//前置通知
			System.out.println("The method " + methodName + " begins with " + Arrays.asList(pjp.getArgs()));
			resultMethod = (Integer)pjp.proceed() ;
			//返回通知
			System.out.println("The method " + methodName + " afterReturning  with " + resultMethod);
		} catch (Throwable e) {
			e.printStackTrace();
			//异常通知
			System.out.println("The method " + methodName + " afterThrowing with " + e);
		}
		
		//后置通知
		System.out.println("The method " + methodName + " ends");
		return resultMethod;
	}
}
