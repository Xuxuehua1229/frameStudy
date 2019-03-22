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
    
    /**
          *  两级缓存：
          *  一级缓存：（本地缓存）：sqlSession 缓存 一级缓存是一直开启的;sqlSession级别的一个 map中
          *         与数据库同一次会话期间查到的数据会放在本地缓存中
          *         以后如果需要获取相同的数据，直接从缓存中拿，没必要再去查询数据库；
          *         一级缓存失效的情况（没有使用到当前一级缓存的情况，效果就是，还需要再向数据库发出查询）：
     *        ① sqlSession 不同
     *        ② sqlSession 相同,查询条件不一样(当前一级缓存中还没有这个数据)
     *        ③ sqlSession 相同，两次查询之间执行了增删改操作(这次增删改可能对当前数据有影响)
     *        ④ sqlSession 相同，手动清除当前一级缓存（缓存清空）
          *  二级缓存（全局缓存）：基于 namespace 级别的缓存；一个 namespace 对应一个二级缓存
          *        工作机制：
     *        ①  一个会话，查询一条数据，这个数据就会被放在当前会话的一级缓存中
     *        ②  如果会话关闭；一级缓存中的数据会被保存到二级缓存中；新的会话查询信息，就可以参照二级缓存中
     *        ③  sqlSession ==== EmployeeMapper ====  Employee
     *                           DepartmentMapper ===== Department
          *                       不同 namespace 查出的数据会放到自己对应的缓存中（map）
          *                       效果：数据会从二级缓存中获取
          *                                  查出的数据会默认放在一级缓存中，只有在会话（一级缓存）提交或关闭之后，一级缓存中的数据才会转移到二级缓存中
          *         使用:
     *        ① 开启全局二级缓存配置（<setting name="cacheEnabled" value="true"></setting>）
     *        ② 去 mapper.xml 配置中使用 二级缓存
     *             <cache></cache>
     *        ③ pojo 需要实现序列化接口
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
    		
    		//第二次查询是从缓存中拿到的，并没有发出新的sql请求
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
			//① sqlSession 不同
			SqlSession sqlSession2 = getSqlSessionFactory().openSession();
			EmployeeMapper em2 = sqlSession2.getMapper(EmployeeMapper.class);
			Employee employee3 = em2.getEmpById(1);
			System.out.println(employee1 == employee3);
			System.out.println("=============================================");
			//② sqlSession 相同,查询条件不一样
			Employee employee4 = em.getEmpById(2);
			System.out.println(employee1 == employee4);
			System.out.println("=============================================");
			//③ sqlSession 相同，两次查询之间执行了增删改操作
			//em.addEmp(new Employee(null, "ff", "ff@qq.com", "0",new Department(1)));
			//System.out.println("增加成功！！！");
			//Employee employee5 = em.getEmpById(1);
			//System.out.println(employee1 == employee5);
			System.out.println("=============================================");
			//④ sqlSession 相同，手动清除当前一级缓存（缓存清空）
			sqlSession.clearCache();
			Employee employee6 = em.getEmpById(1);
			System.out.println(employee1 == employee6);
		} finally {
			sqlSession.close();
		}
    }
}
