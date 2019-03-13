package com.test.spring.beans.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService{
	
	@Autowired
    private BookShopDao bookShopDao;
	
	// 添加事务注解
	//1.使用 propagation 指定事务的传播行为，即当前的事务方法被另外一个事务方法调用时
	//如何使用事务，默认值为 REQUIRED,即使用调用方法的事务   多条数据多满足的情况下，数据才会统一修改，否则不会更改  
	//事务传播行为默认  例如：用足够的余钱买两本刚好价格的书，即余钱会减少，两本书的库存会减少；若余钱只能够买一本书，则购买不成功，余钱不减少，书的库存不减少
	//REQUIRES_NEW : 事务自己的事务，调用的事务方法的事务被挂起
	//例如：用足够的余钱买两本刚好价格的书，即余钱会减少，两本书的库存会减少；若余钱只能够买一本书，
	//则购买第一本书成功，第二本书失败，余额会显示余额不足，余钱减少，第一本书的库存减少，第二本不够余钱的书的库存不减
	//2.使用 isolation 指定事务的隔离级别，最常用的取值为READ_COMMITTED 
	//3.默认情况下 Spring 的申明式事务对所有的运行时异常进行回滚，也可以通过对应的属性进行设置，通常情况下去默认值即可
	//4.使用  readOnly 指定事务是否为只读，表示这个事务只读取数据但不更新数据，这样可以帮助数据库引擎优化事务  若真的是一个只读取数据库值的方法，应设置 readOnly=true
	//5.使用 timeout 指定强制回滚之前事务可以占用的时间
	/*@Transactional(propagation=Propagation.REQUIRES_NEW,
			       isolation=Isolation.READ_COMMITTED,noRollbackFor=UserAccountException.class)*/
	@Transactional(propagation=Propagation.REQUIRES_NEW,
		       isolation=Isolation.READ_COMMITTED,
		       readOnly=false,
		       timeout=3)
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
