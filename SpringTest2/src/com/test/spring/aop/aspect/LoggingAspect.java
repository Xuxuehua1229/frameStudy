package com.test.spring.aop.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
/*import org.aspectj.lang.ProceedingJoinPoint;*/
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
/*import org.aspectj.lang.annotation.Around;*/
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/*
   * ��������ȼ����ã�����ʹ��  @Order ע��ָ����������ȼ���ֵԽС���ȼ�Խ��
 */
/*@Order(2) 
@Aspect
@Component*/
public class LoggingAspect {
	/*  
	    *       ǰ��֪ͨ��com.test.spring.aop.aspect.ArithmeticCalculator �ӿڵ�ʵ������ʵ��ÿ������֮ǰִ�еĴ���  
	 * 
	 */
	@Before("execution(public int com.test.spring.aop.aspect.ArithmeticCalculator.*(..))")
	public void beforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		Object[] objects = joinPoint.getArgs();
		System.out.println("The method " + methodName + " begins with " + Arrays.asList(objects));
	}

	/*
	    *     ����֪ͨ�� ��Ŀ�귽��ִ��֮��ִ�еĴ��룬�����Ƿ�����쳣
	 */
	@After("execution(public int com.test.spring.aop.aspect.ArithmeticCalculator.*(..))")
	public void afterMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		Object[] objects = joinPoint.getArgs();
		System.out.println("The method " + methodName + " ends with " + Arrays.asList(objects));
	}
	
	/*
	    *      ����֪ͨ����Ŀ�귽������������ִ�еĴ���
	    *      ����֪ͨ���Է��ʵ�����ִ��֮��ķ���ֵ��
	 */
	@AfterReturning(value="execution(public int com.test.spring.aop.aspect.ArithmeticCalculator.*(..))",returning="result")
	public void afterReturning(JoinPoint joinPoint,Object result) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " afterReturning with " + result);
	}
	
	/*
	   *     ��Ŀ�귽�������쳣ʱ��ִ�еĴ���
	   *     ���Է��ʵ��쳣�����ҿ���ָ�������ض��쳣ʱ��ִ��֪ͨ����
	 */
	@AfterThrowing(value="execution(public int com.test.spring.aop.aspect.ArithmeticCalculator.*(..))",throwing="e")
	public void afterThrowing(JoinPoint joinPoint,Exception e) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " afterThrowing with " + e);
	}
	/*
	   *    ����֪ͨ������֪ͨ��ҪЯ�� ProceedingJoinPoint ���͵Ĳ���
	   *     ����֪ͨ�����ڶ�̬�����ȫ���̣� ProceedingJoinPoint ���͵Ĳ������Ծ����Ƿ�ִ��Ŀ�귽��
	   *     �һ���֪ͨ�����з���ֵ������ֵ��Ŀ�귽���ķ���ֵ
	 */
	/*@Around("execution(public int com.test.spring.aop.aspect.ArithmeticCalculator.*(..))")
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
	}*/
}
