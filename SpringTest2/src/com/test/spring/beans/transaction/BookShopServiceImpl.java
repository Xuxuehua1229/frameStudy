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
	
	// �������ע��
	//1.ʹ�� propagation ָ������Ĵ�����Ϊ������ǰ�����񷽷�������һ�����񷽷�����ʱ
	//���ʹ������Ĭ��ֵΪ REQUIRED,��ʹ�õ��÷���������   �������ݶ����������£����ݲŻ�ͳһ�޸ģ����򲻻����  
	//���񴫲���ΪĬ��  ���磺���㹻����Ǯ�������պü۸���飬����Ǯ����٣�������Ŀ�����٣�����Ǯֻ�ܹ���һ���飬���򲻳ɹ�����Ǯ�����٣���Ŀ�治����
	//REQUIRES_NEW : �����Լ������񣬵��õ����񷽷������񱻹���
	//���磺���㹻����Ǯ�������պü۸���飬����Ǯ����٣�������Ŀ�����٣�����Ǯֻ�ܹ���һ���飬
	//�����һ����ɹ����ڶ�����ʧ�ܣ�������ʾ���㣬��Ǯ���٣���һ����Ŀ����٣��ڶ���������Ǯ����Ŀ�治��
	//2.ʹ�� isolation ָ������ĸ��뼶����õ�ȡֵΪREAD_COMMITTED 
	//3.Ĭ������� Spring ������ʽ��������е�����ʱ�쳣���лع���Ҳ����ͨ����Ӧ�����Խ������ã�ͨ�������ȥĬ��ֵ����
	//4.ʹ��  readOnly ָ�������Ƿ�Ϊֻ������ʾ�������ֻ��ȡ���ݵ����������ݣ��������԰������ݿ������Ż�����  �������һ��ֻ��ȡ���ݿ�ֵ�ķ�����Ӧ���� readOnly=true
	//5.ʹ�� timeout ָ��ǿ�ƻع�֮ǰ�������ռ�õ�ʱ��
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
		//��ȡ��ĵ���
		double price = bookShopDao.findBookPriceByIsbn(isbn);
		//�������Ŀ��
		bookShopDao.updateBookStock(isbn);
		//�����û����
		bookShopDao.updateBookAccount(username, price);
	}

}
