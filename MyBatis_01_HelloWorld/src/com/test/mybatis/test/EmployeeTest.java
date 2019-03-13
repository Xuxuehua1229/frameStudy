package com.test.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import com.test.mybatis.bean.Employee;
import com.test.mybatis.dao.EmployeeMapper;

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
	
	
}
