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
   * 切面的优先级设置：可以使用  @Order 注解指导切面的优先级，值越小优先级越高
 */
/*@Order(2) 
@Aspect
@Component*/
public class LoggingAspect {
	/*  
	    *       前置通知：com.test.spring.aop.aspect.ArithmeticCalculator 接口的实现类里实现每个方法之前执行的代码  
	 * 
	 */
	@Before("execution(public int com.test.spring.aop.aspect.ArithmeticCalculator.*(..))")
	public void beforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		Object[] objects = joinPoint.getArgs();
		System.out.println("The method " + methodName + " begins with " + Arrays.asList(objects));
	}

	/*
	    *     后置通知： 在目标方法执行之后执行的代码，无论是否出现异常
	 */
	@After("execution(public int com.test.spring.aop.aspect.ArithmeticCalculator.*(..))")
	public void afterMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		Object[] objects = joinPoint.getArgs();
		System.out.println("The method " + methodName + " ends with " + Arrays.asList(objects));
	}
	
	/*
	    *      返回通知：在目标方法正常结束后执行的代码
	    *      返回通知可以访问到正常执行之后的返回值的
	 */
	@AfterReturning(value="execution(public int com.test.spring.aop.aspect.ArithmeticCalculator.*(..))",returning="result")
	public void afterReturning(JoinPoint joinPoint,Object result) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " afterReturning with " + result);
	}
	
	/*
	   *     在目标方法出现异常时会执行的代码
	   *     可以访问到异常对象，且可以指定出在特定异常时再执行通知代码
	 */
	@AfterThrowing(value="execution(public int com.test.spring.aop.aspect.ArithmeticCalculator.*(..))",throwing="e")
	public void afterThrowing(JoinPoint joinPoint,Exception e) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " afterThrowing with " + e);
	}
	/*
	   *    环绕通知：环绕通知需要携带 ProceedingJoinPoint 类型的参数
	   *     环绕通知类似于动态代理的全过程， ProceedingJoinPoint 类型的参数可以决定是否执行目标方法
	   *     且环绕通知必须有返回值，返回值即目标方法的返回值
	 */
	/*@Around("execution(public int com.test.spring.aop.aspect.ArithmeticCalculator.*(..))")
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
	}*/
}
