package com.test.struts2.app.model;

/**
         ����1. Department ��ģ�ͣ�ʵ��¼��� Department . depatName ����ֱ��д��  s:textfield �� name �����С��� manager������δ��ã�
         Struts2 ����ǩ�� name ֵ���Ա���Ϊ���Ե����ԣ�name = manager.name �� name = manager.birth
         ����2.manager����һ�� Date ���͵� birth ���ԣ�Struts2 ������Զ�������ת����
                           ȫ�ֵ�����ת������Ȼ��������������
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
