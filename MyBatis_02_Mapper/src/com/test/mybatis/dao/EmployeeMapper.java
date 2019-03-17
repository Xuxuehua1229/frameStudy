package com.test.mybatis.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.test.mybatis.bean.Employee;

public interface EmployeeMapper {
   public Employee getEmpByMap(Map<String, Object> map);
	
   public Employee getEmpByLastNameAndId(@Param("id") Integer id,@Param("lastName") String lastName);
   
   public Employee getEmpById(Integer id);
   
   public void addEmp(Employee employee);
   
   public boolean updateEmp(Employee employee);
   
   public void deleteEmp(Integer id);
}
