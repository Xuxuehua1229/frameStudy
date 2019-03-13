package com.test.spring.beans.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

/*
    c3p0-0.9.1.2.jar
	com.springsource.org.aopalliance-1.0.0.jar
	com.springsource.org.aspectj.weaver-1.6.8.RELEASE.jar
	commons-logging-1.2.jar
	mysql-connector-java-5.1.7-bin.jar
	spring-aop-4.3.8.RELEASE.jar
	spring-aspects-4.3.8.RELEASE.jar
	spring-beans-4.3.8.RELEASE.jar
	spring-context-4.3.8.RELEASE.jar
	spring-core-4.3.8.RELEASE.jar
	spring-expression-4.3.8.RELEASE.jar
	spring-jdbc-4.3.8.RELEASE.jar
	spring-tx-4.3.8.RELEASE.jar
	��Ҫ��ôЩ��
 */
public class JDBCTest {
	private ApplicationContext ac = null;
	private JdbcTemplate jdbcTemplate;
	private EmployeeDao employeeDao;
	private DepartMentDao departMentDao;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	{
		ac = new ClassPathXmlApplicationContext("beans-jdbc.xml");
		jdbcTemplate = (JdbcTemplate)ac.getBean("jdbcTemplate");
		employeeDao = (EmployeeDao)ac.getBean("employeeDao");
		departMentDao = (DepartMentDao)ac.getBean(DepartMentDao.class);
		namedParameterJdbcTemplate = (NamedParameterJdbcTemplate)ac.getBean(NamedParameterJdbcTemplate.class);
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testDataSource() throws SQLException {
		DataSource ds = (DataSource)ac.getBean("dataSource");
		System.out.println(ds.getConnection());
	}

	/*
	 * ���ݵĸ��£�insert update delete 
	 */
	@Test
	public void testUpdate() {
		String sql = "update employees set last_name = ? where id = ?";
		jdbcTemplate.update(sql, "Jack",5);
	}

	/*
	 * ִ���������£�������insert update delete 
	 * ���һ�������� Object[] �� List ���ͣ���Ϊ�޸�һ����¼��Ҫһ��Object�����飬��ô��������Ҫ���Object����
	 * 
	 */
	@Test
	public void testBatchUpdate() {
		String sql = "insert into employees(last_name,email,dept_id) values (?,?,?)";
		List<Object[]> batchArgs = new ArrayList<>();
		batchArgs.add(new Object[] {"aa","aa@163.com",4});
		batchArgs.add(new Object[] {"bb","bb@163.com",4});
		batchArgs.add(new Object[] {"cc","cc@163.com",3});
		batchArgs.add(new Object[] {"dd","dd@163.com",2});
		batchArgs.add(new Object[] {"ee","ee@163.com",1});
		jdbcTemplate.batchUpdate(sql, batchArgs);
	}

	/*
	 * �����ݿ��ȡһ����¼��ʵ�ʵõ���Ӧ��һ������
	 * ע�⣺ ���ǵ���queryForObject(String sql, Class<Employee> requiredType, Object...args)������
	 * ����Ҫ����queryForObject(String sql, RowMapper<Employee> rowMapper, Object...args) ����
	 * 1.���� RowMapper ָ�����ȥӳ���������У����õ�ʵ����Ϊ BeanPropertyRowMapper
	 * 2.ʹ�� SQL ���еı�����������������������ӳ�� �� ���� last_name lastName
	 * 3.��֧�ּ������ԣ�JdbcTemplate ��һ�� JDBC ��С���ߣ������� ORM ���
	 */
	@Test
	public void testQueryForObject() {
		String sql = "select id,last_name lastName,email,dept_id as \"department.id\" from employees where id = ?";
		//Employee employee = jdbcTemplate.queryForObject(sql, Employee.class,1);
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		Employee employee = jdbcTemplate.queryForObject(sql, rowMapper, 1);
		System.out.println(employee);
	}

	/*
	 * �鵽ʵ����ļ���
	 * ע�⣺ ���ǵ���queryForList()����
	 */
	@Test
	public void testQueryForList() {
		String sql = "select id,last_name lastName,email from employees where id > ?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		List<Employee> list = jdbcTemplate.query(sql, rowMapper, 5);
		System.out.println(list);
	}

	/*
	 * ��ȡ�����е�ֵ������ͳ�Ʋ�ѯ
	 * ʹ�� queryForObject(String sql,Class<Long> requiredType)
	 */
	@Test
	public void testQueryForObject2() {
		String sql = "select count(*) from employees";
		long count = jdbcTemplate.queryForObject(sql, long.class);
		System.out.println(count);
	}

	/*
	    *     ���� EmployeeDao 
	 */
	@Test
	public void testEmployeeDao() {
		System.out.println(employeeDao.getEmployee(2));
	}

	/*
	    *      ���� DepartmentDao --->  JdbcDaoSupport
	 */
	@Test
	public void testDepartmentDao() {
		System.out.println(departMentDao.getDepartment(3));
	}

	/*   
	   *          ����Ϊ����������
	 *   1.�ô������ж��������������ȥ��Ӧλ�ã�ֱ�Ӷ�Ӧ������������ά��
	 *   2.ȱ�㣺��Ϊ�鷳  
	 */
	@Test
	public void testNameParameterJdbcTemplate() {
		String sql = "INSERT INTO employees (last_name,email,dept_id) VALUES (:ln,:email,:deptId)";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ln", "ff");
		paramMap.put("email", "ff@163.com");
		paramMap.put("deptId", 3);

		namedParameterJdbcTemplate.update(sql, paramMap);
	}

	/*
	   *  ʹ�þ�������ʱ������ʹ�� update(String sql,SqlParameterSource paramSource) �������и��²���
	 *  1. SQL ����еĲ��������������һ��
	 *  2. ʹ�� SqlParamterSource �� BeanPropertySqlParameterSource ʵ������Ϊ����
	 */
	@Test
	public void testNameParameterJdbcTemplate2() {
		String sql = "INSERT INTO employees (last_name,email,dept_id) VALUES(:lastName,:email,:deptId)";
		Employee employee = new Employee();
		employee.setLastName("lisi");	
		employee.setEmail("lisi@163.com");
		employee.setDeptId(4);

		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(employee);
		namedParameterJdbcTemplate.update(sql, paramSource);
	}
}
