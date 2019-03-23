package com.test.struts2.action;

public class TestResultAction {
	private Integer number;
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public String execute() {
		String result = "";
        //根据请求参数 number 的值，返回对应的 JSP 页面
		//1.若 number 是3的整数，返回 result_success.jsp 页面
		if(number % 4 == 0) 
		    result = "result_success";
		//2.若number 除以3余1，返回 result_login.jsp页面
		else if(number % 4 == 1)
			result = "result_login";
		//3.若number 除以3余2，返回 result_index.jsp页面
		else if(number % 4 == 2)
			result = "result_index";
		else 
			result = "test";
		return result;
	}
}
