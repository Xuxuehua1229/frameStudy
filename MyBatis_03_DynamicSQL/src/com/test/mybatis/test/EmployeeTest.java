package com.test.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
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
import com.test.mybatis.dao.EmployeeMapperDynamicSQL;
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
    public void testDynamicSql1() throws IOException {
    	SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession();
			EmployeeMapperDynamicSQL eds = session.getMapper(EmployeeMapperDynamicSQL.class);
			//Employee employee = new Employee(null,"bb",null,null);  -- select * from employee where and last_Name like '%bb%'
			//测试 if
			Employee employee = new Employee(1,"jeryy", null,null);
			List<Employee> employees = eds.getEmpsByConditionIf(employee);
			System.out.println(employees.size());
			for (Employee emp : employees) {
				System.out.println(emp);
			}
			
			//查询的时候如果某些条件没带可能 sql 拼接会有问题
		    //1:给 where 后加上 1=1，后面的条件都 and xxx
			//2:mybatis 使用 where 标签来将所有的查询条件包括在内。mybatis 就会将 where 标签中拼装的sql,多出来的 and 或者 or 去掉
			  // where 只会去掉第一个多出来的 and 或者 or
			System.out.println("=========================测试 Trim=========================");
			//测试 Trim
			List<Employee> emps1 = eds.getEmpsByConditionTrim(employee);
			System.out.println(employees.size());
			for (Employee emp : emps1) {
				System.out.println(emp);
			}
			
			System.out.println("=========================测试 choose=========================");
			//测试 choose
			List<Employee> emps2 = eds.getEmpsByConditionChoose(employee);
			System.out.println(employees.size());
			for (Employee emp : emps2) {
				System.out.println(emp);
			}
			
			System.out.println("=========================测试 set=========================");
			System.out.println();
			//测试 set
			//eds.updateEmp(employee);
			//session.commit();
			
			
			System.out.println("=========================测试 Foreach=========================");
			//测试 Foreach
			List<Employee> empsForeach = eds.getEmpsByConditionForeach(Arrays.asList(1,2,3,4));
			for (Employee empForeach : empsForeach) {
				System.out.println(empForeach);
			}
		} finally {
			session.close();
		}
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
