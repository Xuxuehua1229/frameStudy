package com.test.struts2.form.actions;

import java.util.Arrays;

import com.opensymphony.xwork2.ActionContext;

public class UserAction {
	private Integer userId;
	private String userName;
	private String password;
	private String desc;
	private boolean married;
	private String gender;
	private String[] city;
	private String age;
	
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String[] getCity() {
		return city;
	}
	public void setCity(String[] city) {
		this.city = city;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public boolean isMarried() {
		return married;
	}
	public void setMarried(boolean married) {
		this.married = married;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

    public String save() {
    	System.out.println("save:"+this);
    	
    	UserAction ua = new UserAction();
    	ua.setUserId(1233);
    	ua.setUserName("bbbb");
    	ua.setDesc("kkkkkkk");
    	ua.setPassword("33333");
    	
    	//ActionContext.getContext().getValueStack().push(ua);
    	return "input";
    }
	@Override
	public String toString() {
		return "UserAction [userId=" + userId + ", userName=" + userName + ", password=" + password + ", desc=" + desc
				+ ", married=" + married + ", gender=" + gender + ", city=" + Arrays.toString(city) + ", age=" + age
				+ "]";
	}
}
