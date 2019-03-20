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
 * 1. �ӿ�ʽ��̣�
    *                     ԭ����                                    Dao     =====��  DaoImpl
 *       mybatis         Mapper  =====��  xxMapper.xml 
 * 2.  SqlSession ��������ݿ��һ�λỰ������֮�����ر�
 * 3.  SqlSession �� connection һ�����Ƿ��̰߳�ȫ�ġ�ÿ��ʹ��ʱ��Ӧ�����»�ȡ�µĶ���
 * 4.  mapper �ӿ�û��ʵ���࣬���� mybatis ��Ϊ����ӿ�����һ������(���ӿں�xml���а�)
 *        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
 * 5.  ������Ҫ�������ļ���
 *        mybatis ��ȫ�������ļ����������ݿ�����ӳ���Ϣ�������������Ϣ��........ϵͳ���л���
 *        sqlӳ���ļ���������ÿ��sql���ӳ�����Ϣ       ����sql����ȡ������
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
			//��Ϊ�ӿ��Զ�����һ��������󣬴������ȡִ����ɾ�Ĳ鷽��
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
	 * 1.�������
	 *   ��  mybatis ������ɾ�Ĳ�ֱ�Ӷ����������ͷ���ֵ��Integer��Long��Boolean��
	 *   ��  ��Ҫ�ֶ����ύ����   
	 *        getSqlSessionFactory().openSession()     �ֶ��ύ   sqlSession.commit();
	 *        getSqlSessionFactory().openSession(true) �Զ��ύ
	 */
	@Test
	public void testInsertMapper() throws IOException {
		SqlSession sqlSession = null;
		try {
			//1����ȡ����sqlSession �����Զ��ύ����
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
	 * 2.�����޸�
	 */
	@Test
	public void testUpdateMapper() throws IOException {
		SqlSession sqlSession = null;
		try {
			//1����ȡ����sqlSession �����Զ��ύ����
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
	 * 3.����ɾ��
	 */
	@Test
	public void testDeleteMapper() throws IOException {
		SqlSession sqlSession = null;
		try {
			//1����ȡ����sqlSession �����Զ��ύ����
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
			//1����ȡ����sqlSession �����Զ��ύ����
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
			//1����ȡ����sqlSession �����Զ��ύ����
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
			//1����ȡ����sqlSession �����Զ��ύ����
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
