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
		//ִ��Ŀ�귽��
		Integer resultMethod = null;
		String methodName = pjp.getSignature().getName();
		try {
			//ǰ��֪ͨ
			System.out.println("The method " + methodName + " begins with " + Arrays.asList(pjp.getArgs()));
			resultMethod = (Integer)pjp.proceed() ;
			//����֪ͨ
			System.out.println("The method " + methodName + " afterReturning  with " + resultMethod);
		} catch (Throwable e) {
			e.printStackTrace();
			//�쳣֪ͨ
			System.out.println("The method " + methodName + " afterThrowing with " + e);
		}
		
		//����֪ͨ
		System.out.println("The method " + methodName + " ends");
		return resultMethod;
	}
}
