package com.test.struts2.app.model;

/**
         问题1. Department 是模型，实际录入的 Department . depatName 可以直接写到  s:textfield 的 name 属性中。那 manager属性如何处置？
         Struts2 表单标签的 name 值可以被赋为属性的属性：name = manager.name 、 name = manager.birth
         问题2.manager中有一个 Date 类型的 birth 属性，Struts2 会完成自动的类型转换吗？
                           全局的类型转换器仍然可以正常工作！
 */
public class Department {
	private Integer id;
	private String depatName;
	private Manager manager;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDepatName() {
		return depatName;
	}
	public void setDepatName(String depatName) {
		this.depatName = depatName;
	}
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", depatName=" + depatName + ", manager=" + manager + "]";
	}

}
