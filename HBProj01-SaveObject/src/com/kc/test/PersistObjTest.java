/*
	Author: Arun KC
	version: 1.0
	Date: 07/10/2023
	Reason:: Persist method test.
	         persist method does not support any generator-> so remove it from hProduct.hbm.xml file 
	         
*/
package com.kc.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.kc.entity.Product;

public class PersistObjTest {
	public static void main(String[] args) {
		
		//activate HB framework
		Configuration cfg = new Configuration();
		
		//specify hibernate configuration file name and location
		cfg.configure("/com/kc/cfgs/hibernate.cfg.xml");
		
		//Create HB SessionFactory object
		SessionFactory factory= cfg.buildSessionFactory();
		
		//Create Session object
		Session ses= factory.openSession();
		
		//Prepare Entity class object having data
		Product p =new Product();
		p.setPid(1810);
		p.setPname("Kanak");
		p.setPrice(12500.00);
		p.setQty(110.0);
		p.setStatus("Available");
		
		Transaction tx= null;
		boolean flag=false;
		try {
			tx=ses.beginTransaction();
			
			ses.persist(p);
			flag=true;
		}
		catch(HibernateException e) {
			e.printStackTrace();
			flag=false;
		}
		finally {
			if(flag) {
				tx.commit();
				System.out.println("Record Inserted...");
			}
			else {
				tx.rollback();
				System.out.println("Record not inserted...");
			}
			//Closing HB object...
			ses.close();
			factory.close();
		}
		
	}
}
