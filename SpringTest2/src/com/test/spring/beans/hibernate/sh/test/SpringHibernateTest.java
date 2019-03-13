package com.test.spring.beans.hibernate.sh.test;

import java.sql.SQLException;
import java.util.Arrays;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.spring.beans.hibernate.sh.dao.BookShopDao;
import com.test.spring.beans.hibernate.sh.service.BookShopService;
import com.test.spring.beans.hibernate.sh.service.Cashier;

public class SpringHibernateTest {
	private ApplicationContext ac = null;
	private BookShopService bss;
	private BookShopDao bsd;
	private Cashier cashier;

	{
		ac = new ClassPathXmlApplicationContext("beans-sh.xml");
        bss = (BookShopService)ac.getBean("bookShopService");
        bsd = (BookShopDao)ac.getBean("bookShopDao");
        cashier = (Cashier)ac.getBean("cashier");
	}
    
	@Test
	public void testCashierService() {
		cashier.checkout("AA", Arrays.asList(1001,1002));
	}
	
	@Test
	public void testBookShopService() {
		bss.purchase("AA", 1001);
	}
	
    @Test
	public void testUpdateBookStock() {
		bsd.updateBookStock(1001);
	}
    
    @Test
	public void updateBookAccount() {
		bsd.updateBookAccount("AA",130.0);
	}
	@Test
	public void testFindBookPriceByIsbn() {
		System.out.println(bsd.findBookPriceByIsbn(1002));
	}
	
	@Test
	public void testDataSource() {
		DataSource ds = null;
		try {
		    ds = (DataSource)ac.getBean("dataSource");
			System.out.println(ds.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
