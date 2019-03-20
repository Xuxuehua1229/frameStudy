package com.test.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import com.test.mybatis.bean.Department;
import com.test.mybatis.bean.Employee;
import com.test.mybatis.dao.DepartmentMapper;
import com.test.mybatis.dao.EmployeeMapper;
import com.test.mybatis.dao.EmployeeMapperAnnotation;
import com.test.mybatis.dao.EmployeeMapperPlus;

/**
 * 1. 接口式编程：
    *                     原生：                                    Dao     =====》  DaoImpl
 *       mybatis         Mapper  =====》  xxMapper.xml 
 * 2.  SqlSession 代表和数据库的一次会话，用完之后必须关闭
 * 3.  SqlSession 和 connection 一样都是非线程安全的。每次使用时都应该重新获取新的对象
 * 4.  mapper 接口没有实现类，但是 mybatis 会为这个接口生成一个对象(将接口和xml进行绑定)
 *        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
 * 5.  两个重要的配置文件：
 *        mybatis 的全局配置文件：包括数据库的连接池信息，事务管理器信息等........系统运行环境
 *        sql映射文件：保存了每个sql语句映射的信息       （将sql语句抽取出来）
 *        
 * @author 123
 *
 */
public class EmployeeTest {
	
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
    	String resource = "mybatis-config.xml";
    	InputStream inputStream = Resources.getResourceAsStream(resource);
    	SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    	return sessionFactory;
    }
    
	@Test
	void test() throws IOException {
		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession();
			Employee employee = session.selectOne("com.test.mybatis.EmployeeMapper.selectEmployee", 1);
		    System.out.println(employee);
		} finally {
			session.close();
		}
	}
    
	@Test
	public void test2() throws IOException {
		SqlSession sqlSession = null;
		try {
			sqlSession = getSqlSessionFactory().openSession();
			//会为接口自动创建一个代理对象，代理对象取执行增删改查方法
			EmployeeMapper em = sqlSession.getMapper(EmployeeMapper.class);
			Employee employee = em.getEmpById(1);
			System.out.println(employee);
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void test3() throws IOException {
		SqlSession sqlSession = null;
		try {
			sqlSession = getSqlSessionFactory().openSession();
			EmployeeMapperAnnotation ea = sqlSession.getMapper(EmployeeMapperAnnotation.class);
			Employee employee = ea.getEmpById(1);
			System.out.println(employee);
		}finally {
			sqlSession.close();
		}
	}
	
	/*
	 * 1.测试添加
	 *   ①  mybatis 允许增删改查直接定义以下类型返回值（Integer、Long、Boolean）
	 *   ②  需要手动的提交数据   
	 *        getSqlSessionFactory().openSession()     手动提交   sqlSession.commit();
	 *        getSqlSessionFactory().openSession(true) 自动提交
	 */
	@Test
	public void testInsertMapper() throws IOException {
		SqlSession sqlSession = null;
		try {
			//1、获取到的sqlSession 不会自动提交数据
			sqlSession = getSqlSessionFactory().openSession();
			EmployeeMapper em = sqlSession.getMapper(EmployeeMapper.class);
			Employee employee = new Employee(null, "ee", "ee@qq.com", "1");
			em.addEmp(employee);
			System.out.println(employee.getId());
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
	
	/*
	 * 2.测试修改
	 */
	@Test
	public void testUpdateMapper() throws IOException {
		SqlSession sqlSession = null;
		try {
			//1、获取到的sqlSession 不会自动提交数据
			sqlSession = getSqlSessionFactory().openSession();
			EmployeeMapper em = sqlSession.getMapper(EmployeeMapper.class);
		    
			boolean result = em.updateEmp(new Employee(5, "aa", "jerry@qq.com", "0"));
			System.out.println(result);
			
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
	
	/*
	 * 3.测试删除
	 */
	@Test
	public void testDeleteMapper() throws IOException {
		SqlSession sqlSession = null;
		try {
			//1、获取到的sqlSession 不会自动提交数据
			sqlSession = getSqlSessionFactory().openSession();
			EmployeeMapper em = sqlSession.getMapper(EmployeeMapper.class);
		    
			em.deleteEmp(5);
			
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void testParametes1() throws IOException {
		SqlSession sqlSession = null;
		try {
			//1、获取到的sqlSession 不会自动提交数据
			sqlSession = getSqlSessionFactory().openSession();
			EmployeeMapper em = sqlSession.getMapper(EmployeeMapper.class);
		    
			Employee employee = em.getEmpByLastNameAndId(1, "aa");
			System.out.println(employee);
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void testParametes2() throws IOException {
		SqlSession sqlSession = null;
		try {
			//1、获取到的sqlSession 不会自动提交数据
			sqlSession = getSqlSessionFactory().openSession();
			EmployeeMapper em = sqlSession.getMapper(EmployeeMapper.class);
		    
			Map<String, Object> map = new HashMap<>();
			map.put("id", 1);
			map.put("lastName", "aa");
			map.put("tableName", "employee");
			Employee employee = em.getEmpByMap(map);
			System.out.println(employee);
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void testLastNameLike() throws IOException {
		SqlSession sqlSession = null;
		try {
			//1、获取到的sqlSession 不会自动提交数据
			sqlSession = getSqlSessionFactory().openSession();
			EmployeeMapper em = sqlSession.getMapper(EmployeeMapper.class);
		    
			List<Employee> employees = em.getEmpsByLastNameLike("%e%");
			for (Employee employee : employees) {
				System.out.println(employee);
			}
			System.out.println("=========================");
			Map<String, Object> empMap = em.getEmpByIdReturnMap(1);
			System.out.println(empMap);
			System.out.println("=========================");
			Map<Object, Employee> empsMap = em.getEmpsByLastNameLikeReturnMap("%e%");
			System.out.println(empsMap);
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void testMapperPlus() throws IOException {
		SqlSession sqlSession = null;
		try {
			sqlSession = getSqlSessionFactory().openSession();
			
			EmployeeMapperPlus emp = sqlSession.getMapper(EmployeeMapperPlus.class);
			/*Employee employee = emp.getEmpById(1);
			System.out.println(employee);*/
			/*Employee employee = emp.getEmpAndDept(3);
			System.out.println(employee);*/
			Employee employee = emp.getEmpByIdStep(1);
			System.out.println(employee);
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void testDeptMapperPlus() throws IOException {
		SqlSession sqlSession = null;
		try {
			sqlSession = getSqlSessionFactory().openSession();
			DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
			Department department = departmentMapper.getDeptByIdPlus(1);
			System.out.println(department);
			System.out.println(department.getEmps().size());
			
			System.out.println("=================================================");
			
			Department dept = departmentMapper.getDeptByIdStep(1);
			System.out.println(dept);
		} finally {
			sqlSession.close();
		}
	}
}
