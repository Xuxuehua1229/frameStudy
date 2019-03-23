package com.test.struts2.action;

import java.util.Map;

import org.apache.struts2.dispatcher.Parameter;
import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.ActionContext;

public class TestActionContextAction {
    public String execute() {
    	// 0.获取 Application 对象
    	// ActionContext 是 Action 的上下文对象，可以从中获取到当前 Action 需要的一切信息
    	ActionContext ac = ActionContext.getContext();
    	// 1.获取 application 对应的 Map ,并向其中添加一个属性
    	// 通过调用 Application 对象的getApplication() 方法来获取 application 对象的 Map 对象
    	Map<String, Object> applicationMap = ac.getApplication();
    	// 设置属性
    	applicationMap.put("applicationKey", "applicationValue");
    	// 获取属性
    	Object date = applicationMap.get("date");
    	System.out.println("date:"+date);
    	// 2.session
    	Map<String,Object> sessionMap = ac.getSession();
    	sessionMap.put("sessionKey", "sessionValue");
    	if(sessionMap instanceof SessionMap) {
    		SessionMap sm = (SessionMap) sessionMap;
    		sm.invalidate();
    		System.out.println("session 失效了。");
    	}
    	// 3.request
    	// ActionContext 中并没有提供 getRequest() 方法来获取 request 对应的 Map
    	// 需要手工调用 get()方法，传入 request字符串来获取
    	Map<String, Object> requestMap = (Map<String, Object>) ac.get("request");
    	requestMap.put("requestKey", "requestValue");
    	
    	// 4. 获取请求参数对应的Map,并获取指定的参数
    	// 键： 是请求参数的名称  值：请求参数的值对应的字符串数组
    	// 注意：1.getParameters 的返回值为 Map<String,Object>,而不是 Map<String,String[]>
    	//     2.parameters 这个Map 只能读，不能写入，如果写入，但不出错，但也不起作用
    	Map<String, Parameter> parameters = ac.getParameters();
    	System.out.println(parameters.get("name"));
    	return "success";
    }
}
