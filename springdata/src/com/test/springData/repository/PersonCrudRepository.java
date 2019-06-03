package com.test.springData.repository;

import org.springframework.data.repository.CrudRepository;

import com.test.springData.bean.Person;

/**
    *   报错：
 * org.springframework.beans.factory.NoSuchBeanDefinitionException: 
 * No bean named 'transactionManager' available: No matching PlatformTransactionManager bean found for 
 * qualifier 'transactionManager' - neither qualifier match nor bean name match!
 * 
    *    解决方案：在 application.xml 配置文件里  加上这个 transaction-manager-ref="transactionManager" 配置
 *       <jpa:repositories base-package="com.test.springData"
         entity-manager-factory-ref="entityManagerFactory"
         transaction-manager-ref="transactionManager">
         </jpa:repositories>
 *  https://blog.csdn.net/qq_24172609/article/details/86023486 参考百度
 */
public interface PersonCrudRepository extends CrudRepository<Person, Integer>{

}
