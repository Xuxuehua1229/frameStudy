package com.test.springData.test;


import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;

import com.test.springData.bean.Person;
import com.test.springData.repository.PersonCrudRepository;
import com.test.springData.repository.PersonJpaRepository;
import com.test.springData.repository.PersonPagingAndSortingRepository;
import com.test.springData.repository.PersonRepository;
import com.test.springData.service.PersonService;

class SpringDataTest {
    private ApplicationContext ac = null;
    private PersonRepository pr = null;
    private PersonService personService;
    private PersonCrudRepository pcr = null;
    private PersonPagingAndSortingRepository ppr = null;
    private PersonJpaRepository pjr = null;
    
    {
    	ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    	pr = ac.getBean(PersonRepository.class);
    	pcr = ac.getBean(PersonCrudRepository.class);
    	personService = ac.getBean(PersonService.class);
    	ppr = ac.getBean(PersonPagingAndSortingRepository.class);
    	pjr = ac.getBean(PersonJpaRepository.class);
    }
    
    @Test
    public void testCustomRepositoryMethod() {
    	pjr.test();
    }
    
    /**
                  实现带查询条件的分页
                  调用 JpaSpecificationExecutor 的 Page<T> findAll(Specification<T> spec,Pageable pageable)
      Specification:封装了 JPA Criteria 查询的条件
      Pageable:封装了请求分页的数据：例如： pageNo,pageSize,Sort
     */
    @Test
    public void testJpaSpecificationExecutor() {
    	int pageNo = 3-1;
    	int pageSize = 5;
    	PageRequest pageRequest = new PageRequest(pageNo, pageSize);
    	
    	//通常使用 Specification 的匿名内部类
    	Specification<Person> specification = new Specification<Person>() {
			/**
			 * @param *root: 代表查询的实体类
			 * @param  query:可以从中得到 root 对象，即告知 
			 * Jpa Criteria 查询要查询的哪一个实体类，还可以用来添加查询条件，还可以结合
			 * EntityManager 对象得到最终查询的 TypedQuery 对象
			 * @param *cb: CriteriaBuilder 对象，用于创建 Criteria 相关对象的工厂，当然可以从中获取到 Predicate 对象
			 * @param *Predicate 
			 */
			@Override
			public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Path path = root.get("id");
				Predicate predicate = cb.gt(path, 5);
				return predicate;
			}
		};
		
		Page<Person> page = pjr.findAll(specification, pageRequest);
		System.out.println("总记录数:" + page.getTotalElements());
    	System.out.println("当前第几页:" + (page.getNumber()+1));
    	System.out.println("总页数:" + page.getTotalPages());
    	System.out.println("当前页数的list:" + page.getContent());
    	System.out.println("当前页数的记录数:" + page.getNumberOfElements());
    }
    
    @Test
    public void testJpaPepository() {
    	Person person = new Person();
    	person.setLastName("bhr1");
    	person.setBirth(new Date());
    	person.setEmail("bhr@qq.com");
    	person.setId(27);
    	personService.savePersonJpaRepository(person);
    	//Person person = pjr.findOne(1);
    	//System.out.println(person);
    }
    
    @Test
    public void testPagingAndSortingRepository() {
    	Integer pageNo = 6-1;
    	Integer pageSize = 5;
    	
    	//排序相关的，Sort 封装了排序的信息
    	//Order 具体针对于某一个属性进行升序还是降序
    	Order orders1 = new Order(Direction.DESC, "id");
    	Order orders2 = new Order(Direction.ASC, "email");
    	Sort sort = new Sort(orders1,orders2);
    	PageRequest pageRequest = new PageRequest(pageNo, pageSize, sort);
    	
    	//Pageable 接口通常使用的其 PageRequest 实现类，其中封装了需要分页的信息
    	PageRequest pageable = new PageRequest(pageNo, pageSize);
    	Page<Person> page = ppr.findAll(pageable);
    	System.out.println("总记录数:" + page.getTotalElements());
    	System.out.println("当前第几页:" + (page.getNumber()+1));
    	System.out.println("总页数:" + page.getTotalPages());
    	System.out.println("当前页数的list:" + page.getContent());
    	System.out.println("当前页数的记录数:" + page.getNumberOfElements());
    }
    
    @Test
    public void testCrudRepository() {
        Iterable<Person> persons = pcr.findAll();
 	    Iterator<Person> ips = persons.iterator();
 	    while (ips.hasNext()) {
 		    Person person = ips.next();
 		    System.out.println(person.getId() + "," + person.getLastName());
 	    }
    	List<Person> personList = new ArrayList<>();
    	for (int i = 'a'; i <= 'z'; i++) {
			Person person = new Person();
			person.setId((i+1));
			person.setLastName((char)i + "" + (char)i);
			person.setBirth(new Date());
			person.setEmail((char)i + "" + (char)i + "@qq.com");
			
			personList.add(person);
		}
    	
    	personService.savePersonByCrudRepository(personList);
    }
    
    @Test
    public void testModifyPerson() {
    	//pr.updatePersonEmail("aaaa@qq.com", 1);
    	personService.updatePersonEmail("aaaa@qq.com", 1);
    }
    
    
    @Test
    public void getNativeQuery() {
    	long count = pr.getTotalCount();
    	System.out.println(count);
    }
    
    @Test
    public void testQueryAnnotationLikeParam1() {
    	/*List<Person> list = pr.testQueryAnnotationLikeParam("%a%", "%aa%");
    	System.out.println(list);*/
    	
    	List<Person> list = pr.testQueryAnnotationLikeParam1("a", "aa");
    	System.out.println(list);
    }
    
    @Test
    public void testQueryAnnotationLikeParam2() {
    	List<Person> list = pr.testQueryAnnotationLikeParam1("a", "aa");
    	System.out.println(list);
    }
    
    @Test
    public void testQueryAnnotationParams1() {
    	List<Person> list = pr.testQueryAnnotationParams1("aa", "aa@qq.com");
    	System.out.println(list);
    }
    
    @Test
    public void testQueryAnnotationParams2() {
    	List<Person> list = pr.getQueryAnnotationParams2("aa@qq.com","aa");
    	System.out.println(list);
    }
    
    @Test
    public void testQueryAnnotation() {
    	Person person = pr.getMaxIdPerson();
    	System.out.println(person);
    }
    
    @Test
    public void testKeyWord() throws ParseException {
    	//List<Person> persons1 = pr.getByLastNameStartingWithAndIdLessThan("a", 6);
    	//System.out.println(persons1);
    	
    	List<Person> persons2 = pr.getByLastNameEndingWithAndIdLessThan("e", 7);
    	System.out.println(persons2);
    	
    	
    	String date = "1996-03-09 14:26:39";
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:dd:ss");
    	Date birth = dateFormat.parse(date);
    	List<Person> persons3 = pr.getByEmailInAndBirthLessThan(
    			Arrays.asList("aa@qq.com","cc@qq.com","aee@qq.com")
    			, birth);
    	System.out.println(persons3.size());
    	System.out.println(persons3);
    }
    
    @Test
    public void testKeyWords2() {
    	List<Person> persons = pr.getByAddress_IdGreaterThan(1);
    	System.out.println(persons);
    }
    
    @Test
    public void testHelloWorldSpringData() {
    	Person person = pr.getByLastName("aa");
    	System.out.println(person);
    }
    
    @Test
    public void testJpa() {
    	
    }
    
	@Test
	public void testDataSource() throws SQLException {
		DataSource dataSource = ac.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}
}
