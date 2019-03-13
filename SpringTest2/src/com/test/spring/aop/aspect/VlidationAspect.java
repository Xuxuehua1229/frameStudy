package com.test.spring.aop.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Aspect
@Component
public class VlidationAspect {
	
   //如果不是一个包里的还要加上包名 如：com.test.spring.aop.aspect.DeclareLoggingAspect.declareJoinPointExpression()
   @Before("DeclareLoggingAspect.declareJoinPointExpression()")
   public void validateArgs(JoinPoint joinPoint) {
	   System.out.println("validateArgs : " + Arrays.asList(joinPoint.getArgs()));
   }
}
