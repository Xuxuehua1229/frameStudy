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
	
	
}
