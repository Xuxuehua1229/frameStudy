package com.test.mybatis.dao;

import java.util.List;

import com.test.mybatis.bean.Employee;

public interface EmployeeMapperPlus {
	
   public Employee getEmpById(Integer id);
   
   public Employee getEmpAndDept(Integer id);
   
   public Employee getEmpByIdStep(Integer id);
   
   public List<Employee> getEmpsByIdDeptId(Integer id);
}
