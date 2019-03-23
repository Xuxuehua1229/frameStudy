package com.test.struts2.action;

import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

public class TestAwareAction implements ApplicationAware,SessionAware,RequestAware,ParameterAware{
    public String execute() {
    	application.put("applicationKey2", "applicationValue2");
    	session.put("sessionKey2", "sessionValue2");
    	return "success";
    }
    
    Map<String, Object> application;
    private Map<String, Object> session;
   
	@Override
	public void setApplication(Map<String, Object> arg0) {
		this.application = arg0;
	}

	@Override
	public void setParameters(Map<String, String[]> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}
}
