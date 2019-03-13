package com.test.spring.beans.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("bookShopDao")
public class BookShopDaoImpl implements BookShopDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public double findBookPriceByIsbn(Integer isbn) {
	    String sql = "SELECT price FROM book WHERE isbn = ?";
	    return jdbcTemplate.queryForObject(sql, double.class,isbn);
	}

	@Override
	public void updateBookStock(Integer isbn) {
		//检查书的库存是否足够，若不够，则抛出异常
		String sql1 = "SELECT stock FROM book_stock WHERE isbn = ?";
		Integer stockCount = jdbcTemplate.queryForObject(sql1, Integer.class, isbn);
		if(stockCount == 0) {
			throw new BookStockException("库存不足");
		}
        String sql2 = "UPDATE book_stock SET stock = stock - 1 WHERE isbn = ?";
        jdbcTemplate.update(sql2, isbn);
	}

	@Override
	public void updateBookAccount(String name, Double price) {
		//验证金额是否足够，若不足，则抛出异常
		String sql1 = "SELECT balance FROM account WHERE username = ?";
		double palance = jdbcTemplate.queryForObject(sql1, double.class, name);
		if(palance < price) {
			throw new UserAccountException("余额不足");
		}
        String sql2 = "UPDATE account SET balance = balance - ? WHERE username = ?";
        jdbcTemplate.update(sql2, price,name);
	}
}
