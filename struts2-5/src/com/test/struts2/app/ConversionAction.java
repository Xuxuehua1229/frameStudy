package com.test.struts2.app;


import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.test.struts2.app.model.Customer;

public class ConversionAction extends ActionSupport implements ModelDriven<Customer>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Customer customer;
	public String execute() {
		System.out.println("customer:"+customer);
		return "success";
	}

	@Override
	public Customer getModel() {
		customer = new Customer();
		return customer;
	}
}
