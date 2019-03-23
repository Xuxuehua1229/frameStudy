package com.test.struts2.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class TestServletActionContextAction {
   public String execute() {
	   /**
	    * ServletActionContext: ���Դ��л�ȡ����ǰ Action ������Ҫ��һ�� Servlet API ��صĶ���
	    * ���õķ�����
	    * �� ��ȡ HttpServletRequest: ServletActionContext.getRequest();
	    * �� ��ȡ HttpSession: ServletActionContext.getRequest().getSession();
	    * �� ��ȡ ServletContext: ServletActionContext.getServletContext();
	    */
	   HttpServletRequest request = ServletActionContext.getRequest();
	   HttpSession session = ServletActionContext.getRequest().getSession();
	   ServletContext servletContext = ServletActionContext.getServletContext();
	   System.out.println("execute.....");
	   return "success";
   }
}
