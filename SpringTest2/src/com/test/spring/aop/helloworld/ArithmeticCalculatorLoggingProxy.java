package com.test.spring.aop.helloworld;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ArithmeticCalculatorLoggingProxy {
	private ArithmeticCalculator target;
	
	public ArithmeticCalculatorLoggingProxy(ArithmeticCalculator target) {
		super();
		this.target = target;
	}
	
	public ArithmeticCalculator getLoggingProxy() {
		ArithmeticCalculator proxy = null;
		
		//�����������һ����������������
		ClassLoader loader = target.getClass().getClassLoader();
        //�����������ͣ�����������Щ����
		Class[] interfaces = new Class[] {ArithmeticCalculator.class};  
        //�����ô���������еķ���ʱ����ִ�еĴ���
		InvocationHandler h = new InvocationHandler() {
            /**
             *  proxy: ���ڷ��ص��Ǹ��������һ������£��� invoke �����ж���ʹ�øö���
             *  method: ���ڱ����õķ���
             *  args: ���÷���ʱ������Ĳ���
             */
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				//System.out.println(proxy.toString()); ������ѭ��
				String methodName = method.getName();
				//��־
				System.out.println("Test -> The method = " + methodName + " begin with " + Arrays.asList(args));
				//ִ�з���
				Object result = null;
				try {
					//ǰ��֪ͨ
					result = method.invoke(target, args);
					//����֪ͨ�����Է��ʵ������ķ���ֵ
				} catch (Exception e) {
					//�쳣֪ͨ�����Է��ʵ��������쳣
				}
				
				//����֪ͨ����Ϊ�������ܻ�����쳣�����Է��ʲ��������ķ���ֵ
				
				//��־
				System.out.println("Test -> The method " + methodName + " ends with " + result);
				return result;
			}
		};
		proxy = (ArithmeticCalculator)Proxy.newProxyInstance(loader, interfaces, h);
		return proxy;
	}

} 
