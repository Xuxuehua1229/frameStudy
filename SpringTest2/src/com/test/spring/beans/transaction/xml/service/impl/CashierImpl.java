package com.test.spring.beans.transaction.xml.service.impl;

import java.util.List;

import com.test.spring.beans.transaction.xml.service.BookShopService;
import com.test.spring.beans.transaction.xml.service.Cashier;

public class CashierImpl implements Cashier {
    private BookShopService bookShopService;
    public void setBookShopService(BookShopService bookShopService) {
		this.bookShopService = bookShopService;
	}
		
	@Override
	public void checkout(String username, List<Integer> isbn) {
		for (Integer isbnStr : isbn) {
			bookShopService.purchase(username, isbnStr);
		}
	}

}
