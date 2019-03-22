package com.test.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.test.mybatis.bean.Employee;

public interface EmployeeMapperDynamicSQL {
	//查询员工信息（携带了哪个字段查询条件就带上这个字段的值）
    public List<Employee> getEmpsByConditionIf(Employee employee);
    
    public List<Employee> getEmpsByConditionTrim(Employee employee);
    
    public List<Employee> getEmpsByConditionChoose(Employee employee);
    
    public void updateEmp(Employee employee);
    
    public List<Employee> getEmpsByConditionForeach(@Param("ids") List<Integer> ids);
    
    public void addEmps(@Param("emps") List<Employee> emps);
    
    public List<Employee> getEmpTestInnerParameter(Employee employee);
}
