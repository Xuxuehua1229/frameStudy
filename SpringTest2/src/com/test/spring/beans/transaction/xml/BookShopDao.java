package com.test.spring.beans.transaction.xml;

public interface BookShopDao {
	
   //ͨ����Ż�ȡ��ĵ���
   public double findBookPriceByIsbn(Integer isbn);
   
   //���¿�棬ʹ��Ŷ�Ӧ�Ŀ�� -1 
   public void updateBookStock(Integer isbn);
   
   //�����û����˺����: ʹ username ��  balance - price
   public void updateBookAccount(String name,Double price);
}
