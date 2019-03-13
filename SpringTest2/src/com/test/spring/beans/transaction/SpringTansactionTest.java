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
    
    //���Ի�ȡ��ļ۸�
    @Test
    public void testBookShopDaoFindPriceByIsbn() {
    	System.out.println(bsd.findBookPriceByIsbn(1001));
    }
    
    //���Ը�����Ŀ���һ
    @Test
    public void testBookShopDaoUpdateBookStock() {
    	bsd.updateBookStock(1001);
    }
    
    //���Ը����û����
    @Test
    public void testBookShopDaoUpdateUserAccount() {
    	bsd.updateBookAccount("AA", 100.0);
    }
    
    //����ͨ���������������������ע�����������ݿ������
    @Test
    public void testBookShopService() {
    	bss.purchase("AA", 1001);
    }
    
    //��������Ĵ�����Ϊ
    @Test
    public void testTansactionPropagation() {
    	cashier.checkout("AA", Arrays.asList(1001,1002));
    }
}
