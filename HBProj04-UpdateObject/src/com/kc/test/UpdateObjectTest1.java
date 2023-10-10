/*
	Author @ Arun KC
	Below Application is using ses.update(-) method
	Do not use this method if partially update is required, because if we won't provide
	any value then it will take null or 0.
	
	Good only for complete update of record.
	
*/
package com.kc.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kc.entity.Product;
import com.kc.utility.HibernateUtil;

public class UpdateObjectTest1 {
	public static void main(String[] args) {
		SessionFactory factory= HibernateUtil.getSessionFactory();
		Session ses=HibernateUtil.getSession();
		Transaction tx= null;
		try(factory;ses){
			//prepare entity object
			Product prod1= new Product();
			prod1.setPid(1815);
			prod1.setPname("HP Mouse v1");
			prod1.setQty(4.0);
			prod1.setPrice(1200.0);
			prod1.setStatus("Available");
			
			//Begin Transaction
			tx=ses.beginTransaction();
			ses.update(prod1);
			tx.commit();
			System.out.println("Record updated.");
		}
		catch(HibernateException he) {
			if(tx!=null || tx.getStatus()==null ||  tx.getRollbackOnly()==true)
				tx.rollback();
			System.out.println("Error while updating record.");
			he.printStackTrace();
		}
		catch(Exception e) {
			if(tx!=null || tx.getStatus()==null ||  tx.getRollbackOnly()==true)
				tx.rollback();
			System.out.println("Error while updating record.");
			e.printStackTrace();
		}
	}
}
