package com.test.spring.beans.hibernate.sh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.spring.beans.hibernate.sh.dao.BookShopDao;
import com.test.spring.beans.hibernate.sh.service.BookShopService;

@Transactional
@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {

	@Autowired
	private BookShopDao bookShopDao;
	
	/**
	 *  Spring Hibernate 事务的流程
	 *  1. 在方法开始之前
	 *     ① 获取Session
	 *     ② 把  Session 和  当前的线程进行绑定，这样就可以在 DAO 中使用 SessionFactory 的 getCurrentSession() 方法来获取Session
	 *     ③ 开启事务
	 *  2. 在方法正常结束，即没有出现异常，则
	 *     ① 提交事务
	 *     ② 使和当前的线程绑定的 Session 进行解绑
	 *     ③ 关闭事务
	 *  3. 若方法出现异常，则：
	 *     ① 回滚事务
	 *     ② 使和当前的线程绑定的 Session 进行解绑
	 *     ③ 关闭事务
	 */
	@Override
	public void purchase(String username, Integer isbn) {
		double price = bookShopDao.findBookPriceByIsbn(isbn);
		bookShopDao.updateBookStock(isbn);
		bookShopDao.updateBookAccount(username, price);
	}

}
