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
	 *  Spring Hibernate ���������
	 *  1. �ڷ�����ʼ֮ǰ
	 *     �� ��ȡSession
	 *     �� ��  Session ��  ��ǰ���߳̽��а󶨣������Ϳ����� DAO ��ʹ�� SessionFactory �� getCurrentSession() ��������ȡSession
	 *     �� ��������
	 *  2. �ڷ���������������û�г����쳣����
	 *     �� �ύ����
	 *     �� ʹ�͵�ǰ���̰߳󶨵� Session ���н��
	 *     �� �ر�����
	 *  3. �����������쳣����
	 *     �� �ع�����
	 *     �� ʹ�͵�ǰ���̰߳󶨵� Session ���н��
	 *     �� �ر�����
	 */
	@Override
	public void purchase(String username, Integer isbn) {
		double price = bookShopDao.findBookPriceByIsbn(isbn);
		bookShopDao.updateBookStock(isbn);
		bookShopDao.updateBookAccount(username, price);
	}

}
