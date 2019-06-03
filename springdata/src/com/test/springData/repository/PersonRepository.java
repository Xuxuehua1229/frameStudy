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
 * 1.Repository ��һ���սӿڣ�����һ����ǽӿ�
 * 2.�����Ƕ���Ľӿڼ̳��� Repository ,��ýӿڻᱻ IOC ����ʶ��Ϊһ�� Repository Bean.
    *        ���뵽 IOC �����У����������ڸýӿ��ж���һ������һ���淶�ķ�����
 * 3.ʵ����Ҳ����ͨ��һ��ע�� @RepositoryDefinition ע��������̳� Repository �ӿ�
 *
 *
 * 4.�� Repository �ӽӿ�����������
 *    �� ������������ģ�����Ҫ����һ���Ĺ淶
 *    �� ��ѯ������ find|read|get ��ͷ
 *    �� �漰������ѯʱ�������������������ؼ�������
 *    �� Ҫע����ǣ���������������ĸ��д
 *    �� ֧�����Եļ�����ѯ������ǰ��������з������������ԣ�������ʹ�ã�����ʹ�ü�������
    *               ����Ҫʹ�ü������ԣ�������֮��ʹ�� _ ��������
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
   
   //��ѯ id ֵ���� person
   //ʹ�� @Query ע������Զ��� JPQL �����ʵ�ָ����Ĳ�ѯ
   @Query("select p from Person p where p.id =(select max(p2) from Person p2)")
   Person getMaxIdPerson();
   
   //Ϊ @Query ע�⴫�ݲ����ķ�ʽ 1��ʹ��ռλ��
   @Query("SELECT p FROM Person p where p.lastName = ?1 and p.email = ?2")
   List<Person> testQueryAnnotationParams1(String lastName,String email);
   
   //Ϊ @Query ע�⴫�ݲ����ķ�ʽ 2��ʹ�ò���������
   @Query("SELECT p FROM Person p where p.lastName = :lastName and p.email = :email")
   List<Person> getQueryAnnotationParams2(@Param("email") String email,@Param("lastName") String lastName);

   //SpringData ������ռλ������� %
   @Query("SELECT p FROM Person p where p.lastName like %?1% OR p.email like %?2%" )
   List<Person> testQueryAnnotationLikeParam1(String lastName,String email);
   
   @Query("SELECT p FROM Person p where p.lastName like %:lastName% OR p.email like %:email%" )
   List<Person> testQueryAnnotationLikeParam2(@Param("lastName") String lastName,@Param("email")String email);

   //���� nativeQuery = true ������ʹ��ԭ����  SQL ��ѯ
   @Query(value="select count(id) from jpa_persons",nativeQuery=true)
   long getTotalCount();
   
   
   //����ͨ���Զ��� JPQL ��� UPDATE �� DELETE ������ע�⣺ JPQL ��֧��ʹ�� INSERT
   //�� @Query ע���б�д JPQL ��䣬������ʹ�� @Modifying �������Σ���֪ͨ SpringData,����һ�� UPDATE �� DELETE ����
   //UPDATE �� DELETE ������Ҫʹ�����񣬴�ʱ��Ҫ���� Service �㡣�� service ��ķ���������������
   //Ĭ������£�SpringData ��ÿ�������������񣬵�����һ��ֻ���������ǲ�������޸Ĳ�����
   @Modifying
   @Query("UPDATE Person p SET p.email = :email WHERE p.id = :id")
   void updatePersonEmail(@Param("email") String email,@Param("id") Integer id);
}

