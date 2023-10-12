package com.kc.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kc.entity.Product;
import com.kc.utility.HibernateUtil;

/*
	Author @ Arun KC
	Date:: 13/10/2023
	
*/
public class ObjectToDBTableRowSynchronization {
	public static void main(String[] args) {
		//get SessionFactory obj
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//get session object
		Session ses=HibernateUtil.getSession();
		Transaction tx=null;
		
		try(factory;ses){
			//begin tx
			tx=ses.beginTransaction();
			//load the object
			Product prod1= ses.get(Product.class, 1800);
			//modify the object
			prod1.setPrice(4100.0);
			tx.commit();
			System.out.println("Object updated.");
		}
		catch(HibernateException he) {
			if(tx!=null || tx.getStatus()==null || tx.getRollbackOnly()==true)
				tx.rollback();
			System.out.println("Exception occured while performing Updation.");
			he.printStackTrace();
		}
		catch(Exception e) {
			if(tx!=null || tx.getStatus()==null || tx.getRollbackOnly()==true)
				tx.rollback();
			System.out.println("Exception occured while performing Updation.");
			e.printStackTrace();
		}
	}
}
