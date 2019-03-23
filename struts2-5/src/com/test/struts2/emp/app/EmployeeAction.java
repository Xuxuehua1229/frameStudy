package com.test.struts2.emp.app;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class EmployeeAction implements RequestAware,ModelDriven<Employee>,Preparable{
	private Dao dao = new Dao();
	private Employee employee;
	private Integer employeeId;
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String list() {
        request.put("emps", dao.getEmployees());
		return "list";
	}
	
	public String delete() {
		dao.delete(employeeId);
		// ���صĽ������Ϊ �� redirectAction http://localhost:8081/struts2-5/emp-list.action
		// Ҳ������chain:ʵ���� chain ��û��Ҫ�ġ���Ϊ����Ҫ����һ�� Action �б�����ǰ Action ��״̬
		// ���У���ʹ�� chain,�򵽴�Ŀ��ҳ��󣬵�ַ����ʾ����Ȼ��ɾ�����Ǹ����ӡ�ˢ��ʱ���ظ��ύ  
		// chain http://localhost:8081/struts2-5/emp-delete.action?employeeId=1003
		return "success";
	}
	
	public String save() {
		System.out.println("employee:"+employee.hashCode());
		System.out.println("valueStack:" + ActionContext.getContext().getValueStack().peek().hashCode());
		// 1.��ȡ�������:ͨ���������Եķ�ʽ
		dao.save(employee);
		// 2.���� dao ���淽��
		// 3.ͨ�� redirectAction �ķ�ʽ��Ӧ����� emp-list
		return "success";
	}
	
	public void prepareSave() {
		employee = new Employee();
	}
	
	public String edit() {
		//1.��ȡ����� employeeId
		//2.���� employeeId ��ȡ Employee ����
		//��ʽһ
		//Employee emp = dao.get(employee.getEmployeeId());
		
		//3.��ջ����������Է�װ��
		// Ŀǰ�� employee ����ֻ�� employeeId ���ԣ����������Զ�Ϊ null
		/*
		 * Struts2 �����ԣ���ֵջջ����ʼ����ƥ������ԣ����ҵ�����ӵ� value �����С�
		 */
		/*employee.setFirstName(emp.getFirstName());
		employee.setLastName(emp.getLastName());
		employee.setEmail(emp.getEmail());*/
		
		// ��ʽ��. ���ܽ��б����ԣ���Ϊ������д��ֵ�� employee �����Ѿ�������ջ��������
		/*System.out.println(employee);
		System.out.println(dao.get(employee.getEmployeeId()));
		employee = dao.get(employee.getEmployeeId());*/
		
		// ��ʽ��.�ֹ��İѴ����ݿ��л�ȡ�� Employee ����ŵ�ֵջ�е�ջ��������ʱֵջջ�����ڶ��������Ϊ Employee ���󣬲�������
		//ActionContext.getContext().getValueStack().push(dao.get(employee.getEmployeeId()));
		return "edit";
	}
	
	public void prepareEdit() {
		employee = dao.get(employeeId);
	}
	
	public String update() {
		dao.update(employee);
		return "success";
	}
	
	public void prepareUpdate() {
		employee = new Employee();
	}
	
	private Map<String, Object> request;
	@Override
	public void setRequest(Map<String, Object> request) {
        this.request = request;
	}

	@Override
	public Employee getModel() {
		//�ж��� Create ����  Edit
		//��Ϊ Create,�� employee = new Employee();
		//��Ϊ Edit,�� employee = dao.get(employee.getEmployeeId());
		//�жϱ�׼Ϊ�Ƿ��� employeeId ���������������иò���������Ϊ Edit ����û�иò���������Ϊ Create
		//��ͨ�� employeeId ���жϣ�����Ҫ�� modelDriven ������֮ǰ��ִ��һ�� params ������
		//���������ͨ��ʹ�� paramsPrepareParams ������ʵ�֡�
		//��Ҫ�� struts.xml �ļ�������ʹ�� paramsPrepareParams ��ΪĬ�ϵ�������ջ
		/*if(employeeId == null)
			employee = new Employee();
		else
			employee = dao.get(employeeId);*/
		return employee;
	}
	
	@Override
	public void prepare() throws Exception {
		System.out.println("prepare....");
	}

}
