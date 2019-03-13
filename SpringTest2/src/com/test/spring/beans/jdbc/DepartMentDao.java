package com.test.spring.beans.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

/*
 * 不推荐使用 JdbcDaoSupport , 而推荐直接使用 JdbcTemplate 作为 DAO 类的成员变量
 */
@Repository
public class DepartMentDao extends JdbcDaoSupport{
	
   @Autowired
   public void setDataSource2(DataSource dataSource) {
	   setDataSource(dataSource);
   }
   
   public Department getDepartment(Integer id) {
	   String sql = "select id,name from department where id = ?";
	   RowMapper<Department> rowMapper = new BeanPropertyRowMapper<>(Department.class);
	   return getJdbcTemplate().queryForObject(sql, rowMapper, id);
   } 
}
