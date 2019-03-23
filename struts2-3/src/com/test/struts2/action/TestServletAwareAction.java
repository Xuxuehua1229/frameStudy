package com.test.struts2.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

/**
 * ͨ��ʵ�� ServletXxxxAware �ӿڵķ�ʽ������ Struts2 ע����Ҫ�� Servlet ��صĶ���
 * �� ServletRequestAware : ע�� HttpServletRequest ����
 * �� ServletContextAware : ע�� ServletContext ���� ���Ƚϳ��ã�
 * �� ServletResponseAware : ע�� HttpServletResponse ����
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
