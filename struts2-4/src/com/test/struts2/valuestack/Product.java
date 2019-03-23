package com.test.struts2.valuestack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

public class Product implements SessionAware,RequestAware{
	private Integer productId;
	private String productName;
	private String productDesc;
	private double productPrice;

	public Product() {
		System.out.println("Product's constructor...");
	}

	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productDesc=" + productDesc
				+ ", productPrice=" + productPrice + "]";
	}

	private List<Person> persons = new ArrayList<>();
    
	public List<Person> getPersons() {
		return persons;
	}
	
	public String testTag() {
		this.productId = 1001;
		this.productName = "aaaa";
		this.productDesc = "sfddf";
		this.productPrice = 1000.0;

		persons.add(new Person("zhangsan1",21));
		persons.add(new Person("lisi1",34));
		persons.add(new Person("wanwu1",25));
		persons.add(new Person("lisa1",11));
		persons.add(new Person("hahaha1",8));

		return "success";	
	}

	public String save() {
		System.out.println("Save : " + this);
		// 1.获取值栈
		ValueStack vs = ActionContext.getContext().getValueStack();

		// 2.创建 Test 对象，并为其赋值
		Test test = new Test();
		test.setProductDesc("AGGGGGG");
		test.setProductName("valueStack");

		// 3.把 Test 对象压入到值栈的栈顶
		vs.push(test);

		session.put("product", this);
		request.put("test", test);

		int i = 10 / 0;

		return "success";
	}

	private Map<String, Object> session;
	private Map<String,Object> request;

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
