package com.test.struts2.action;

public class TestResultAction {
	private Integer number;
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public String execute() {
		String result = "";
        //����������� number ��ֵ�����ض�Ӧ�� JSP ҳ��
		//1.�� number ��3������������ result_success.jsp ҳ��
		if(number % 4 == 0) 
		    result = "result_success";
		//2.��number ����3��1������ result_login.jspҳ��
		else if(number % 4 == 1)
			result = "result_login";
		//3.��number ����3��2������ result_index.jspҳ��
		else if(number % 4 == 2)
			result = "result_index";
		else 
			result = "test";
		return result;
	}
}
