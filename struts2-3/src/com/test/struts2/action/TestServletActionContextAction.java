package com.test.struts2.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class TestServletActionContextAction {
   public String execute() {
	   /**
	    * ServletActionContext: 可以从中获取到当前 Action 对象需要的一切 Servlet API 相关的对象
	    * 常用的方法：
	    * ① 获取 HttpServletRequest: ServletActionContext.getRequest();
	    * ② 获取 HttpSession: ServletActionContext.getRequest().getSession();
	    * ③ 获取 ServletContext: ServletActionContext.getServletContext();
	    */
	   HttpServletRequest request = ServletActionContext.getRequest();
	   HttpSession session = ServletActionContext.getRequest().getSession();
	   ServletContext servletContext = ServletActionContext.getServletContext();
	   System.out.println("execute.....");
	   return "success";
   }
}
