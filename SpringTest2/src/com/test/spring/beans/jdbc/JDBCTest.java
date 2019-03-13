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
	需要这么些包
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
	 * 数据的更新：insert update delete 
	 */
	@Test
	public void testUpdate() {
		String sql = "update employees set last_name = ? where id = ?";
		jdbcTemplate.update(sql, "Jack",5);
	}

	/*
	 * 执行批量更新：批量的insert update delete 
	 * 最后一个参数是 Object[] 的 List 类型：因为修改一条记录需要一个Object的数组，那么多条就需要多个Object数组
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
	 * 从数据库获取一条记录，实际得到对应的一个对象
	 * 注意： 不是调用queryForObject(String sql, Class<Employee> requiredType, Object...args)方法！
	 * 而需要调用queryForObject(String sql, RowMapper<Employee> rowMapper, Object...args) 方法
	 * 1.其中 RowMapper 指定如何去映射结果集的行，常用的实现类为 BeanPropertyRowMapper
	 * 2.使用 SQL 中列的别名完成列名和类的属性名的映射 ， 例如 last_name lastName
	 * 3.不支持级联属性，JdbcTemplate 是一个 JDBC 的小工具，而不是 ORM 框架
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
	 * 查到实体类的集合
	 * 注意： 不是调用queryForList()方法
	 */
	@Test
	public void testQueryForList() {
		String sql = "select id,last_name lastName,email from employees where id > ?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		List<Employee> list = jdbcTemplate.query(sql, rowMapper, 5);
		System.out.println(list);
	}

	/*
	 * 获取单个列的值，或做统计查询
	 * 使用 queryForObject(String sql,Class<Long> requiredType)
	 */
	@Test
	public void testQueryForObject2() {
		String sql = "select count(*) from employees";
		long count = jdbcTemplate.queryForObject(sql, long.class);
		System.out.println(count);
	}

	/*
	    *     测试 EmployeeDao 
	 */
	@Test
	public void testEmployeeDao() {
		System.out.println(employeeDao.getEmployee(2));
	}

	/*
	    *      测试 DepartmentDao --->  JdbcDaoSupport
	 */
	@Test
	public void testDepartmentDao() {
		System.out.println(departMentDao.getDepartment(3));
	}

	/*   
	   *          可以为参数起名字
	 *   1.好处：若有多个参数，则不用再去对应位置，直接对应参数名，便于维护
	 *   2.缺点：较为麻烦  
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
	   *  使用具名参数时，可以使用 update(String sql,SqlParameterSource paramSource) 方法进行更新操作
	 *  1. SQL 语句中的参数名和类的属性一致
	 *  2. 使用 SqlParamterSource 的 BeanPropertySqlParameterSource 实现类作为参数
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
