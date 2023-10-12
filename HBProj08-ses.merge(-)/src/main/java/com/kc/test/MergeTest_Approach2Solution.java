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
	Solution:: Instead of update method use merge method.. see line 40
	
*/
public class MergeTest_Approach2Solution {
	public static void main(String[] args) {
		//get SessionFactory obj
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//get session object
		Session ses=HibernateUtil.getSession();
		Transaction tx=null;
		
		try(factory;ses){
			//begin tx
			tx=ses.beginTransaction();
			//Load object
			Product prod1=ses.get(Product.class, 1800);
			if(prod1 ==null)
				System.out.println("Record not found");
			else 
				System.out.println("Object Found:: "+prod1);
			
			Product prod2= new Product();
			prod2.setPid(1800);
			prod2.setPrice(121212.21);
			prod2.setStatus("Not available");
			prod2.setQty(12.0);
			ses.merge(prod2);
			tx.commit();
			System.out.println("Record is updated.");
			
		}
		catch(HibernateException he) {
			he.printStackTrace();
			if(tx!=null || tx.getStatus()==null || tx.getRollbackOnly()==true)
				tx.rollback();
			System.out.println("Exception occured while performing saveOrUpdate.");
			he.printStackTrace();
		}
		catch(Exception e) {
			if(tx!=null || tx.getStatus()==null || tx.getRollbackOnly()==true)
				tx.rollback();
			System.out.println("Exception occured while performing saveOrUpdate.");
			e.printStackTrace();
		}
	}
}
