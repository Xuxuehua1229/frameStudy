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
		//获取书的单价
		double price = bookShopDao.findBookPriceByIsbn(isbn);
		//更新数的库存
		bookShopDao.updateBookStock(isbn);
		//更新用户余额
		bookShopDao.updateBookAccount(username, price);
	}

}
