package com.test.struts2.app;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.test.struts2.app.model.Department;

public class TestComplextPropertyAction extends ActionSupport implements ModelDriven<Department>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Department department;
	@Override
	public Department getModel() {
		department = new Department();
		return department;
	}

	public String execute() {
		System.out.println(department);
		return SUCCESS;
	}
	
}
