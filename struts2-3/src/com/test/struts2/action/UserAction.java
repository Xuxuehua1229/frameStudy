package com.test.struts2.action;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

public class UserAction implements SessionAware,ApplicationAware{
	private String username;

	public void setUsername(String username) {
		this.username = username;
	}
	
    public String logout() {
    	// 1.在线人数 -1： 获取在线人数，若数量还 >0,则 -1
    	Integer count = (Integer) application.get("count");
    	if(count != null && count > 0) {
    		count--;
    		application.put("count", count);
    	}
    	// 2.session 失效：强转为 SessionMap, 调用 invalidate 方法
    	((SessionMap)session).invalidate();
    	return "logout-success";
    }
	
	public String execute() {
		// 把用户信息存入  session 域中
		// 1.获取 session, 通过实现 SessionAware 接口
		// 2.获取登录信息
		// 3.把用户信息存入 Session 域中
		session.put("username", username);
		// 在线人数 +1
		// 1. 获取当前的在线人数，从 application 中获取
		Integer count = (Integer)application.get("count");
		if(count == null) {
           count = 0;
		}
		// 2. 使当前的在线人数 +1
		count++;
		application.put("count", count);
		return "login-success";
	}

	private Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	private Map<String, Object> application;
	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;		
	}
}
