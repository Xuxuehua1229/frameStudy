package com.test.struts2.app;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

public class Employee implements RequestAware{
	private Dao dao = new Dao();
	private String name;
	private String password;
	private String gender;
	private String department;
	private List<String> roles;
	private String desc;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String input() {
		System.out.println("input....");
        request.put("departments", dao.getDepartments());
        request.put("roles", dao.getRoles());
		return "emp-input";
	}

	public String details() {
		System.out.println("details : " + this);
		return "emp-details";
	}
	
	private Map<String, Object> request;
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", password=" + password + ", gender=" + gender + ", department=" + department
				+ ", roles=" + roles + ", desc=" + desc + "]";
	}

	
}
