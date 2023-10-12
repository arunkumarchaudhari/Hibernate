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
	Signature of ses.refresh(-) :: public void refresh(Object obj)
	
*/
public class DBTableRowToObjectSynchronization {
	public static void main(String[] args) {
		//get SessionFactory obj
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//get session object
		Session ses=HibernateUtil.getSession();
		Transaction tx=null;
		
		try(factory;ses){
			//load the object
			Product prod1= ses.get(Product.class, 1800);
			System.out.println("Object Data:: "+prod1);
			System.out.println("==================================================================");
			System.out.println("Modify 1800 record from the DB table during the sleep time...");
			
			Thread.sleep(40000);
			ses.refresh(prod1); //re-reads state of the record into the given object by generating select SQL query 
			System.out.println("Object Data:: "+prod1);
			
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
