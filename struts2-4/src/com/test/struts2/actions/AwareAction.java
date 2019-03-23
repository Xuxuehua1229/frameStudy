package com.test.struts2.actions;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

public class AwareAction implements SessionAware,RequestAware{
    private Map<String, Object> session = null;
    private Map<String, Object> request = null;
    
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}

	public String execute() {
		System.out.println("AwareAction_execute...");
		session.put("sessionKey", "sessionValue");
		request.put("requestKey", "sessionValue");
		return "success";
	}
}
