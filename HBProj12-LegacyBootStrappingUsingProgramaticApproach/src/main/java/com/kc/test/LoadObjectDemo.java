package com.kc.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kc.entity.Product;
import com.kc.utility.HibernateUtil;

/*
	Author @ Arun KC
	
*/
public class LoadObjectDemo {
	public static void main(String[] args) {
		//get SessionFactory obj
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//get session object
		Session ses=HibernateUtil.getSession();
		
		try(factory;ses){
			Product prod1 = ses.get(Product.class, 12);
			if(prod1!=null)
				System.out.println("Record Found:: "+prod1);
			else
				System.out.println("Record not found..");
		}
		catch(HibernateException he) {
			System.out.println("Exception occured while Finding record from DB.");
			he.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("Exception occured while Finding record from DB.");
			e.printStackTrace();
		}
	}
}
