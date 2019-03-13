package com.test.spring.beans.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("cashier")
public class CashierImpl implements Cashier {
	@Autowired
    private BookShopService bookShopService;
	
	//添加事务注解
	@Transactional
	@Override
	public void checkout(String username, List<Integer> isbn) {
		for (Integer isbnStr : isbn) {
			bookShopService.purchase(username, isbnStr);
		}
	}

}
