/*
	Author: Arun KC
	version: 1.0
	Date: 09/10/2023
	NOTE:: if we comment all code of try->means we wont use prod anywhere, then it won't hit to db
		   as we are using ses.load(-,-) method. To see this, run this code by commenting all try block code...or simply
		   don't use prod object anywhere throughout the Project. 
	         
*/
package com.kc.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.kc.entity.Product;

public class LoadingObjTest2 {
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
		Product prod= ses.load(Product.class, 1800);		
		try {
			/*
			 * Comment this part to see the difference between ses.load(-,-) and ses.get(-,-);
			 */
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
			System.out.println("All done.");
		}
		
	}
}
