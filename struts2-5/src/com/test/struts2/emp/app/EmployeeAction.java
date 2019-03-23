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
		// 返回的结果类型为 ： redirectAction http://localhost:8081/struts2-5/emp-list.action
		// 也可以是chain:实际上 chain 是没必要的。因为不需要在下一个 Action 中保留当前 Action 的状态
		// 还有，若使用 chain,则到达目标页面后，地址栏显示的依然是删除的那个链接。刷新时会重复提交  
		// chain http://localhost:8081/struts2-5/emp-delete.action?employeeId=1003
		return "success";
	}
	
	public String save() {
		System.out.println("employee:"+employee.hashCode());
		System.out.println("valueStack:" + ActionContext.getContext().getValueStack().peek().hashCode());
		// 1.获取请求参数:通过定义属性的方式
		dao.save(employee);
		// 2.调用 dao 保存方法
		// 3.通过 redirectAction 的方式响应结果给 emp-list
		return "success";
	}
	
	public void prepareSave() {
		employee = new Employee();
	}
	
	public String edit() {
		//1.获取传入的 employeeId
		//2.根据 employeeId 获取 Employee 对象
		//方式一
		//Employee emp = dao.get(employee.getEmployeeId());
		
		//3.把栈顶对象的属性封装好
		// 目前的 employee 对象只有 employeeId 属性，其它的属性都为 null
		/*
		 * Struts2 表单回显：从值栈栈顶开始查找匹配的属性，若找到就添加到 value 属性中。
		 */
		/*employee.setFirstName(emp.getFirstName());
		employee.setLastName(emp.getLastName());
		employee.setEmail(emp.getEmail());*/
		
		// 方式二. 不能进行表单回显，因为经过重写赋值的 employee 对象已经不再是栈顶对象了
		/*System.out.println(employee);
		System.out.println(dao.get(employee.getEmployeeId()));
		employee = dao.get(employee.getEmployeeId());*/
		
		// 方式三.手工的把从数据库中获取的 Employee 对象放到值栈中的栈顶，但此时值栈栈顶及第二个对象均为 Employee 对象，不够完美
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
		//判断是 Create 还是  Edit
		//若为 Create,则 employee = new Employee();
		//若为 Edit,则 employee = dao.get(employee.getEmployeeId());
		//判断标准为是否有 employeeId 这个请求参数。若有该参数，则视为 Edit ，若没有该参数，则视为 Create
		//若通过 employeeId 来判断，则需要在 modelDriven 拦截器之前先执行一个 params 拦截器
		//而这个可以通过使用 paramsPrepareParams 拦截器实现。
		//需要在 struts.xml 文件中配置使用 paramsPrepareParams 作为默认的拦截器栈
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
