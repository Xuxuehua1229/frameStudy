package com.test.mybatis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import com.test.mybatis.bean.Employee;

public interface EmployeeMapper {
   //告诉mybatis封装这个map的时候使用哪个属性作为map的key
   @MapKey("lastName")
   public Map<Object, Employee> getEmpsByLastNameLikeReturnMap(String lastName);
   
   public Map<String, Object> getEmpByIdReturnMap(Integer id);
   
   public List<Employee> getEmpsByLastNameLike(String lastName);
   
   public Employee getEmpByMap(Map<String, Object> map);
	
   public Employee getEmpByLastNameAndId(@Param("id") Integer id,@Param("lastName") String lastName);
   
   public Employee getEmpById(Integer id);
   
   public void addEmp(Employee employee);
   
   public boolean updateEmp(Employee employee);
   
   public void deleteEmp(Integer id);
}
