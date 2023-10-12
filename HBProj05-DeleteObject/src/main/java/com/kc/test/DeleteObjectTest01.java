package com.kc.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kc.entity.Product;
import com.kc.utility.HibernateUtil;

/**
  Author @ Arun KC
  Date:: 12/10/2023
  Approach 1: 
  			Delete object directly ( use ses.delete(-) method directly)
 
 */
public class DeleteObjectTest01 {
	public static void main(String[] args) {
		//get SessionFactory object
		SessionFactory factory =HibernateUtil.getSessionFactory();
		//get Session object
		Session ses= HibernateUtil.getSession();
		Transaction tx=null;
		try(factory;ses){
			//Prepare object
			Product p =new Product();
			p.setPid(1810);
			
			//Begin Tx
			tx=ses.beginTransaction();
			ses.delete(p);
			tx.commit();
			System.out.println("Object Deleted.");
			
		}
		catch(HibernateException he) {
			if(tx!=null || tx.getStatus()!=null || tx.getRollbackOnly())
				tx.rollback();
			System.out.println("Exception occured while deleting records from table:: ");
			he.printStackTrace();
		}
	}
}
