package com.test.spring.beans.hibernate.sh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.spring.beans.hibernate.sh.service.BookShopService;
import com.test.spring.beans.hibernate.sh.service.Cashier;

@Service("cashier")
public class CashierImpl implements Cashier {
	@Autowired
	private BookShopService bookShopService;
	
	@Override
	public void checkout(String username, List<Integer> isbn) {
		for (Integer i : isbn) {
            bookShopService.purchase(username, i);
		}
	}

}
