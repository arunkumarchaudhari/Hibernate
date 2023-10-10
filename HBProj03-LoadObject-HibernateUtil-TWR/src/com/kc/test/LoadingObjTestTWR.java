/*
	Author: Arun KC
	version: 1.1
	Date: 10/10/2023
	Reason:: Using Hibernate util class.        
*/
package com.kc.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.kc.entity.Product;
import com.kc.utility.HibernateUtil;

public class LoadingObjTestTWR {
	public static void main(String[] args) {
		
		//get sessionFactory Object
		SessionFactory factory=HibernateUtil.getFactory();
		
		try(factory){
			Session ses=HibernateUtil.getSession();
			try(ses){
				try {
					//Load object (ses.get(-,-))
					Product prod= ses.get(Product.class, 1800);
					if(prod==null)
						System.out.println("Record not found...");
					else {
						System.out.println("Record Found details are::"+prod);
						System.out.println("Product name:: "+prod.getPname());
						System.out.println("Product Id:: "+prod.getPid());
						System.out.println("Quantity:: "+prod.getQty());
						System.out.println("Price:: "+ prod.getPrice());
						System.out.println("Status:: "+prod.getStatus());
					}
				}
				catch(HibernateException e) {
					e.printStackTrace();
				}
				finally {
					//Closing HB object...
					HibernateUtil.closeSession(ses);
					HibernateUtil.closeSessionFactory();
				}
			}
		}
		
		
		
	}
}
