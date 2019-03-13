package com.test.spring.beans.impl;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//�����������Ϊһ�����棺��Ҫ�Ѹ�����뵽IOC�����У�������Ϊһ������
@Aspect
@Component
public class LoggingAspect {
   //�����÷�����һ��ǰ��֪ͨ����Ŀ�귽����ʼ֮ǰִ�� 
   //public int com.test.spring.aop.impl.ArithmeticCalculator.add(int, int) ����һ������������·��
   @Before("execution(* com.test.spring.aop.impl.*.*(int, int))")
   public void beforeMethod(JoinPoint joinPoint) {
	   String methodName = joinPoint.getSignature().getName();
	   List<Object> args = Arrays.asList(joinPoint.getArgs());
	   System.out.println("The method " + methodName + "begins with " + args);
   }
   
   //����֪ͨ����ִ��Ŀ�귽���������Ƿ����쳣��ִ�е�֪ͨ
   //ע�⣺�ں���֪ͨ�л����ܷ���Ŀ�귽��ִ�еĽ���� Ҫͨ������֪ͨ�ſ��Է���
   @After("execution(* com.test.spring.aop.impl.*.*(int, int))")
   public void afterMethod(JoinPoint joinPoint) {
	   String methodName = joinPoint.getSignature().getName();
	   List<Object> args = Arrays.asList(joinPoint.getArgs());
	   System.out.println("The method " + methodName + "ends with " + args);
   }
}
