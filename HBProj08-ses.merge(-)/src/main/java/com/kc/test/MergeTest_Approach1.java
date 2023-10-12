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
	Approach 1: Alternate to ses.saveOrUpdate(-) method
	Have a look in Product.hbm.xml file[extra logic added...recall why?]
	
*/
public class MergeTest_Approach1 {
	public static void main(String[] args) {
		//get SessionFactory obj
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//get session object
		Session ses=HibernateUtil.getSession();
		Transaction tx=null;
		
		try(factory;ses){
			//begin tx
			tx=ses.beginTransaction();
			//prepare obj
			Product prod1= new Product();
			prod1.setPid(1809);
			prod1.setPrice(61499.0);
			prod1.setQty(11.0);
			prod1.setPname("Mac book pro m1");
			prod1.setStatus("Not Available");
			
			//using ses.merge(-) 
			ses.merge(prod1);
			tx.commit();
			System.out.println("Object is save or updated.");
		}
		catch(HibernateException he) {
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
