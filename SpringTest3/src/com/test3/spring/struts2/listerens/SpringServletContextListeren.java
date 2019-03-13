package com.test3.spring.struts2.listerens;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test3.spring.struts2.beans.Person;

/**
 * Application Lifecycle Listener implementation class SpringServletContextListeren
 *
 */
public class SpringServletContextListeren implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public SpringServletContextListeren() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// ��ȡ Spring �����ļ�������
		ServletContext sc = arg0.getServletContext();
		String config = sc.getInitParameter("configLocation");
        // ���� IOC ����
		ApplicationContext ac = new ClassPathXmlApplicationContext(config);
		// �� IOC �������� ServletContext
        sc.setAttribute("ApplicationContext", ac);
	}
	
}
