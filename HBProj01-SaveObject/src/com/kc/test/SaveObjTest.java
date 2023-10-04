package com.kc.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.kc.entity.Product;

public class SaveObjTest {
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
		p.setPid(1801);
		p.setPname("Ganga");
		p.setPrice(1650.90);
		p.setQty(21.0);
		p.setStatus("Available");
		
		Transaction tx= null;
		boolean flag=false;
		try {
			tx=ses.beginTransaction();
			ses.save(p);
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
