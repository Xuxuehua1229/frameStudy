package com.test.mybatis.dao;

import com.test.mybatis.bean.Employee;

public interface EmployeeMapper {
   public Employee getEmpById(Integer id);
}
