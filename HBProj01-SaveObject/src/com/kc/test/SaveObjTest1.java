/*
	Author: Arun KC
	Date: 04/10/2023
	NOTE:: This code will work upto Hibernate v 4.x
	       from 5.x onwards transaction management is mandatory for non-select persistence operation
*/
package com.kc.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kc.entity.Product;

public class SaveObjTest1 {
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
		p.setPid(1803);
		p.setPname("Shivam");
		p.setPrice(1550.90);
		p.setQty(31.0);
		p.setStatus("Available");
		
			//Dave object
			ses.save(p);
			
			//flush the session..
			ses.flush();
			System.out.println("Record Inserted...");
			
			//Closing HB object...
			ses.close();
			factory.close();
		
	}
}
