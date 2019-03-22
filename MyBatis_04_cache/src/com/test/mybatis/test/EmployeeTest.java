package com.test.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
    
    /**
          *  �������棺
          *  һ�����棺�����ػ��棩��sqlSession ���� һ��������һֱ������;sqlSession�����һ�� map��
          *         �����ݿ�ͬһ�λỰ�ڼ�鵽�����ݻ���ڱ��ػ�����
          *         �Ժ������Ҫ��ȡ��ͬ�����ݣ�ֱ�Ӵӻ������ã�û��Ҫ��ȥ��ѯ���ݿ⣻
          *         һ������ʧЧ�������û��ʹ�õ���ǰһ������������Ч�����ǣ�����Ҫ�������ݿⷢ����ѯ����
     *        �� sqlSession ��ͬ
     *        �� sqlSession ��ͬ,��ѯ������һ��(��ǰһ�������л�û���������)
     *        �� sqlSession ��ͬ�����β�ѯ֮��ִ������ɾ�Ĳ���(�����ɾ�Ŀ��ܶԵ�ǰ������Ӱ��)
     *        �� sqlSession ��ͬ���ֶ������ǰһ�����棨������գ�
          *  �������棨ȫ�ֻ��棩������ namespace ����Ļ��棻һ�� namespace ��Ӧһ����������
          *        �������ƣ�
     *        ��  һ���Ự����ѯһ�����ݣ�������ݾͻᱻ���ڵ�ǰ�Ự��һ��������
     *        ��  ����Ự�رգ�һ�������е����ݻᱻ���浽���������У��µĻỰ��ѯ��Ϣ���Ϳ��Բ��ն���������
     *        ��  sqlSession ==== EmployeeMapper ====  Employee
     *                           DepartmentMapper ===== Department
          *                       ��ͬ namespace ��������ݻ�ŵ��Լ���Ӧ�Ļ����У�map��
          *                       Ч�������ݻ�Ӷ��������л�ȡ
          *                                  ��������ݻ�Ĭ�Ϸ���һ�������У�ֻ���ڻỰ��һ�����棩�ύ��ر�֮��һ�������е����ݲŻ�ת�Ƶ�����������
          *         ʹ��:
     *        �� ����ȫ�ֶ����������ã�<setting name="cacheEnabled" value="true"></setting>��
     *        �� ȥ mapper.xml ������ʹ�� ��������
     *             <cache></cache>
     *        �� pojo ��Ҫʵ�����л��ӿ�
     * @throws IOException 
     */
    @Test
    public void testSeconedLevelCache() throws IOException {
    	SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
    	SqlSession sqlSession = sqlSessionFactory.openSession();
    	SqlSession sqlSession2 = sqlSessionFactory.openSession();
    	try {
    		EmployeeMapper em1 = sqlSession.getMapper(EmployeeMapper.class);
    		EmployeeMapper em2 = sqlSession2.getMapper(EmployeeMapper.class);
    		
    		Employee e1 = em1.getEmpById(1);
    		System.out.println(e1);
    		sqlSession.clearCache();
    		
    		//�ڶ��β�ѯ�Ǵӻ������õ��ģ���û�з����µ�sql����
    		Employee e2 = em2.getEmpById(1);
    		System.out.println(e1 == e2);
    		sqlSession2.clearCache();
		} finally {
			
		}
    }
    
    @Test
    public void testFirstLevelCache() throws IOException {
    	SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
    	SqlSession sqlSession = sqlSessionFactory.openSession();
    	try {
			EmployeeMapper em = sqlSession.getMapper(EmployeeMapper.class);
			Employee employee1 = em.getEmpById(1);
			System.out.println(employee1);
			System.out.println("=============================================");
			Employee employee2 = em.getEmpById(1);
			System.out.println(employee1 == employee2);
			System.out.println("=============================================");
			//�� sqlSession ��ͬ
			SqlSession sqlSession2 = getSqlSessionFactory().openSession();
			EmployeeMapper em2 = sqlSession2.getMapper(EmployeeMapper.class);
			Employee employee3 = em2.getEmpById(1);
			System.out.println(employee1 == employee3);
			System.out.println("=============================================");
			//�� sqlSession ��ͬ,��ѯ������һ��
			Employee employee4 = em.getEmpById(2);
			System.out.println(employee1 == employee4);
			System.out.println("=============================================");
			//�� sqlSession ��ͬ�����β�ѯ֮��ִ������ɾ�Ĳ���
			//em.addEmp(new Employee(null, "ff", "ff@qq.com", "0",new Department(1)));
			//System.out.println("���ӳɹ�������");
			//Employee employee5 = em.getEmpById(1);
			//System.out.println(employee1 == employee5);
			System.out.println("=============================================");
			//�� sqlSession ��ͬ���ֶ������ǰһ�����棨������գ�
			sqlSession.clearCache();
			Employee employee6 = em.getEmpById(1);
			System.out.println(employee1 == employee6);
		} finally {
			sqlSession.close();
		}
    }
}
