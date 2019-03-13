package com.test.spring.beans.hibernate.sh.dao.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.orm.hibernate5.HibernateTemplate;*/
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.test.spring.beans.hibernate.sh.dao.BookShopDao;
import com.test.spring.beans.hibernate.sh.exceptions.BookStockException;
import com.test.spring.beans.hibernate.sh.exceptions.UserAccountException;

@Repository("bookShopDao")
@Transactional
public class BookShopDaoImpl implements BookShopDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	//���Ƽ�ʹ��  HibernateTemplate ��  HibernateDaoSupport
	//��Ϊ�����ᵼ�� Dao �� Spring �� API ������ϣ�����ֲ�в����ֲ�ԣ� ��ʱû��  spring ���� �� �� hibernate ������Ҳ����ʹ�ã�
	//private HibernateTemplate hibernateTemplate;
	
	//��ȡ�͵�ǰ�̰߳󶨵�  Session
    private Session getSession() {
    	return sessionFactory.getCurrentSession();
    }
	
    @SuppressWarnings({"unchecked","deprecation"})
	@Override
	public double findBookPriceByIsbn(Integer isbn) {
		String hql = "SELECT b.price FROM Book b where b.isbn = ?";
		Query<Double> query = getSession().createQuery(hql).setInteger(0, isbn);
		return query.uniqueResult();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void updateBookStock(Integer isbn) {
		//��֤��Ŀ���Ƿ��㹻
		String hql2 = "SELECT b.stock FROM Book b WHERE b.isbn = ?";
		Integer stock = (Integer)getSession().createQuery(hql2).setInteger(0, isbn).uniqueResult();
        if(stock == 0) {
        	throw new BookStockException("��治����");
        }
		String hql = "UPDATE Book b SET b.stock = b.stock - 1 where b.isbn = ?";
        getSession().createQuery(hql).setInteger(0, isbn).executeUpdate();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void updateBookAccount(String name, Double price) {
        //��֤����Ƿ��㹻
		String hql = "SELECT a.balance FROM Account a WHERE a.username = ?";
		
		double balance = (Double)getSession().createQuery(hql).setString(0, name).uniqueResult();
		if(price > balance) {
			throw new UserAccountException("���㣡");
		}
		String hql2 = "UPDATE Account a SET a.balance = a.balance - ? WHERE a.username = ?";
	    getSession().createQuery(hql2).setDouble(0, price).setString(1, name).executeUpdate();
	}

}
