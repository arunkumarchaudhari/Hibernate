package com.kc.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kc.entity.Product;
import com.kc.utility.HibernateUtil;

/*
	Author @ Arun KC
	Date:: 12/10/2023
	Approach 1: directly calling ses.saveOrUpdate(-)
	In this approach 3 possible variants of queries will be generated.
			1) select query + insert query [ if record is not available]
			2) select query + update query [ if record is available and all data is not matching with the record]
			3) select query [if record is available and data is matching with object data->simply no change in data]

*/
public class SaveOrUpdateObjectTest1 {
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
			prod1.setPid(1810);
			prod1.setPrice(72000.0);
			prod1.setQty(12.0);
			prod1.setPname("Mac book pro m2");
			prod1.setStatus("Available");
			
			//save or update object
			ses.saveOrUpdate(prod1);
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
