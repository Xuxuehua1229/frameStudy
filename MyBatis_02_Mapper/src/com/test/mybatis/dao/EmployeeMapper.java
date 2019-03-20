package com.test.mybatis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import com.test.mybatis.bean.Employee;

public interface EmployeeMapper {
   //����mybatis��װ���map��ʱ��ʹ���ĸ�������Ϊmap��key
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
