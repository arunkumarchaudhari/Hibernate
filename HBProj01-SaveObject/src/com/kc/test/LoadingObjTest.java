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

public class LoadingObjTest {
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
		Product prod= ses.get(Product.class, 1800);		
		try {
			if(prod==null)
				System.out.println("Record not found...");
			else
				System.out.println("Record Found:: "+prod);
		}
		catch(HibernateException e) {
			e.printStackTrace();
		}
		finally {
			//Closing HB object...
			ses.close();
			factory.close();
		}
		
	}
}
