package com.test.spring.beans.transaction;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTansactionTest {
    private ApplicationContext ac = null;
    private BookShopDao bsd;
    private BookShopService bss;
    private Cashier cashier;
    
    {
    	ac = new ClassPathXmlApplicationContext("beans-jdbc.xml");
    	bsd = (BookShopDao)ac.getBean("bookShopDao");
    	bss = (BookShopService)ac.getBean("bookShopService");
    	cashier = (Cashier)ac.getBean("cashier");
    }
    
    //测试获取书的价格
    @Test
    public void testBookShopDaoFindPriceByIsbn() {
    	System.out.println(bsd.findBookPriceByIsbn(1001));
    }
    
    //测试更新书的库存减一
    @Test
    public void testBookShopDaoUpdateBookStock() {
    	bsd.updateBookStock(1001);
    }
    
    //测试更新用户余额
    @Test
    public void testBookShopDaoUpdateUserAccount() {
    	bsd.updateBookAccount("AA", 100.0);
    }
    
    //测试通过配置事务管理器与事务注解来管理数据库的数据
    @Test
    public void testBookShopService() {
    	bss.purchase("AA", 1001);
    }
    
    //测试事务的传播行为
    @Test
    public void testTansactionPropagation() {
    	cashier.checkout("AA", Arrays.asList(1001,1002));
    }
}
