package com.test.springData.repository;

import org.springframework.data.repository.CrudRepository;

import com.test.springData.bean.Person;

/**
    *   ����
 * org.springframework.beans.factory.NoSuchBeanDefinitionException: 
 * No bean named 'transactionManager' available: No matching PlatformTransactionManager bean found for 
 * qualifier 'transactionManager' - neither qualifier match nor bean name match!
 * 
    *    ����������� application.xml �����ļ���  ������� transaction-manager-ref="transactionManager" ����
 *       <jpa:repositories base-package="com.test.springData"
         entity-manager-factory-ref="entityManagerFactory"
         transaction-manager-ref="transactionManager">
         </jpa:repositories>
 *  https://blog.csdn.net/qq_24172609/article/details/86023486 �ο��ٶ�
 */
public interface PersonCrudRepository extends CrudRepository<Person, Integer>{

}
