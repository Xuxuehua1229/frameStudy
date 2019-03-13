package com.test.spring.beans.transaction.xml.service.impl;

import com.test.spring.beans.transaction.xml.BookShopDao;
import com.test.spring.beans.transaction.xml.service.BookShopService;

public class BookShopServiceImpl implements BookShopService{
	
    private BookShopDao bookShopDao;
    public void setBookShopDao(BookShopDao bookShopDao) {
		this.bookShopDao = bookShopDao;
	}
	
	@Override
	public void purchase(String username, Integer isbn) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//��ȡ��ĵ���
		double price = bookShopDao.findBookPriceByIsbn(isbn);
		//�������Ŀ��
		bookShopDao.updateBookStock(isbn);
		//�����û����
		bookShopDao.updateBookAccount(username, price);
	}

}
