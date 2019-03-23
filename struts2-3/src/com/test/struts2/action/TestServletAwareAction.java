package com.test.struts2.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

/**
 * 通过实现 ServletXxxxAware 接口的方式可以由 Struts2 注入需要的 Servlet 相关的对象
 * ① ServletRequestAware : 注入 HttpServletRequest 对象
 * ② ServletContextAware : 注入 ServletContext 对象 （比较常用）
 * ③ ServletResponseAware : 注入 HttpServletResponse 对象
 * 
 * 
 *
 */
public class TestServletAwareAction implements ServletRequestAware,ServletContextAware,ServletResponseAware{

	@Override
	public void setServletResponse(HttpServletResponse response) {
		System.out.println("response");
	}
    
	private ServletContext context;
	@Override
	public void setServletContext(ServletContext context) {
		System.out.println("context");
		this.context = context;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("request");
	}

	public String execute() {
		System.out.println("setServletContext:" + context);
		return "success";
	}
}
