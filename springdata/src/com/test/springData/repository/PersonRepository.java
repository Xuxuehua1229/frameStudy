package com.test.springData.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
/*import org.springframework.data.repository.RepositoryDefinition;*/
import org.springframework.data.repository.query.Param;

import com.test.springData.bean.Person;

/**
 * 1.Repository 是一个空接口，即是一个标记接口
 * 2.若我们定义的接口继承了 Repository ,则该接口会被 IOC 容器识别为一个 Repository Bean.
    *        纳入到 IOC 容器中，进而可以在该接口中定义一个满足一定规范的方法。
 * 3.实际上也可以通过一个注解 @RepositoryDefinition 注解来替代继承 Repository 接口
 *
 *
 * 4.在 Repository 子接口中声明方法
 *    ① 不是随便声明的，而需要符合一定的规范
 *    ② 查询方法以 find|read|get 开头
 *    ③ 涉及条件查询时，条件的属性用条件关键字连接
 *    ④ 要注意的是：条件属性以首字母大写
 *    ⑤ 支持属性的级联查询。若当前类的属性有符合条件的属性，则优先使用，而不使用级联属性
    *               若需要使用级联属性，则属性之间使用 _ 进行连接
 */
/*@RepositoryDefinition(domainClass=Person.class,idClass=Integer.class)*/
public interface PersonRepository extends Repository<Person, Integer>{
	
   Person getByLastName(String lastName);
   
   //WHERE lastName  LIKE ?% AND id < ?
   List<Person> getByLastNameStartingWithAndIdLessThan(String lastName,Integer id);
   
   //WHERE lastName  LIKE %? AND id < ?
   List<Person> getByLastNameEndingWithAndIdLessThan(String lastName,Integer id);
   
   //WHERE email in (?,?,?) or birth < ?
   List<Person> getByEmailInAndBirthLessThan(List<String> emails ,Date birth);
   
   //WHERE a.id > ?
   List<Person> getByAddress_IdGreaterThan(Integer id);
   
   //查询 id 值最大的 person
   //使用 @Query 注解可以自定义 JPQL 语句以实现更灵活的查询
   @Query("select p from Person p where p.id =(select max(p2) from Person p2)")
   Person getMaxIdPerson();
   
   //为 @Query 注解传递参数的方式 1：使用占位符
   @Query("SELECT p FROM Person p where p.lastName = ?1 and p.email = ?2")
   List<Person> testQueryAnnotationParams1(String lastName,String email);
   
   //为 @Query 注解传递参数的方式 2：使用参数命名法
   @Query("SELECT p FROM Person p where p.lastName = :lastName and p.email = :email")
   List<Person> getQueryAnnotationParams2(@Param("email") String email,@Param("lastName") String lastName);

   //SpringData 允许在占位符上添加 %
   @Query("SELECT p FROM Person p where p.lastName like %?1% OR p.email like %?2%" )
   List<Person> testQueryAnnotationLikeParam1(String lastName,String email);
   
   @Query("SELECT p FROM Person p where p.lastName like %:lastName% OR p.email like %:email%" )
   List<Person> testQueryAnnotationLikeParam2(@Param("lastName") String lastName,@Param("email")String email);

   //设置 nativeQuery = true 即可以使用原生的  SQL 查询
   @Query(value="select count(id) from jpa_persons",nativeQuery=true)
   long getTotalCount();
   
   
   //可以通过自定义 JPQL 完成 UPDATE 和 DELETE 操作，注意： JPQL 不支持使用 INSERT
   //在 @Query 注解中编写 JPQL 语句，但必须使用 @Modifying 进行修饰，以通知 SpringData,这是一个 UPDATE 和 DELETE 操作
   //UPDATE 和 DELETE 操作需要使用事务，此时需要定义 Service 层。在 service 层的方法上添加事务操作
   //默认情况下，SpringData 的每个方法上有事务，但都是一个只读事务，它们不能完成修改操作！
   @Modifying
   @Query("UPDATE Person p SET p.email = :email WHERE p.id = :id")
   void updatePersonEmail(@Param("email") String email,@Param("id") Integer id);
}

