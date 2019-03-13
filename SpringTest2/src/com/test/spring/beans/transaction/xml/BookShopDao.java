package com.test.spring.beans.transaction.xml;

public interface BookShopDao {
	
   //通过书号获取书的单价
   public double findBookPriceByIsbn(Integer isbn);
   
   //更新库存，使书号对应的库存 -1 
   public void updateBookStock(Integer isbn);
   
   //更新用户的账号余额: 使 username 的  balance - price
   public void updateBookAccount(String name,Double price);
}
