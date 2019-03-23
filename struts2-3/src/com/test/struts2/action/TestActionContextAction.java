package com.test.struts2.action;

import java.util.Map;

import org.apache.struts2.dispatcher.Parameter;
import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.ActionContext;

public class TestActionContextAction {
    public String execute() {
    	// 0.��ȡ Application ����
    	// ActionContext �� Action �������Ķ��󣬿��Դ��л�ȡ����ǰ Action ��Ҫ��һ����Ϣ
    	ActionContext ac = ActionContext.getContext();
    	// 1.��ȡ application ��Ӧ�� Map ,�����������һ������
    	// ͨ������ Application �����getApplication() ��������ȡ application ����� Map ����
    	Map<String, Object> applicationMap = ac.getApplication();
    	// ��������
    	applicationMap.put("applicationKey", "applicationValue");
    	// ��ȡ����
    	Object date = applicationMap.get("date");
    	System.out.println("date:"+date);
    	// 2.session
    	Map<String,Object> sessionMap = ac.getSession();
    	sessionMap.put("sessionKey", "sessionValue");
    	if(sessionMap instanceof SessionMap) {
    		SessionMap sm = (SessionMap) sessionMap;
    		sm.invalidate();
    		System.out.println("session ʧЧ�ˡ�");
    	}
    	// 3.request
    	// ActionContext �в�û���ṩ getRequest() ��������ȡ request ��Ӧ�� Map
    	// ��Ҫ�ֹ����� get()���������� request�ַ�������ȡ
    	Map<String, Object> requestMap = (Map<String, Object>) ac.get("request");
    	requestMap.put("requestKey", "requestValue");
    	
    	// 4. ��ȡ���������Ӧ��Map,����ȡָ���Ĳ���
    	// ���� ���������������  ֵ�����������ֵ��Ӧ���ַ�������
    	// ע�⣺1.getParameters �ķ���ֵΪ Map<String,Object>,������ Map<String,String[]>
    	//     2.parameters ���Map ֻ�ܶ�������д�룬���д�룬����������Ҳ��������
    	Map<String, Parameter> parameters = ac.getParameters();
    	System.out.println(parameters.get("name"));
    	return "success";
    }
}
