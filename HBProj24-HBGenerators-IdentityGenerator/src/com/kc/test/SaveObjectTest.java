package com.kc.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kc.entity.Product;
import com.kc.utility.HibernateUtil;


public class SaveObjectTest {
	public static void main(String[] args) {
		SessionFactory factory=HibernateUtil.getSessionFactory();
		Session ses=HibernateUtil.getSession();
		Transaction tx=null;
		try(factory;ses){
			tx=ses.beginTransaction();
			Product p=new Product();
//			p.setPid(1800);
			p.setPname("Mac book Air m2");
			p.setPrice(43299.0);
			p.setQty(2.0);
			p.setStatus("Not available");
			ses.save(p);
			tx.commit();
			System.out.println("Record Inserted.");
			
		}
		catch(HibernateException he) {
			System.out.println("Exception in SaveObjectTest.main()");
			he.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("Exception in SaveObjectTest.main()");
			e.printStackTrace();
			
		}
	}
}
