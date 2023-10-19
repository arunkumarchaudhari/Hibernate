package com.kc.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kc.entity.Artist;
import com.kc.utility.HibernateUtil;

public class SaveObjectTest01 {

	public static void main(String[] args) {
		//get SessionFactory 
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session ses=HibernateUtil.getSession();
		Transaction tx=null;
		try(factory;ses){
			//Begin Tx
			tx=ses.beginTransaction();
			//Prepare object
			Artist artist1= new Artist();
			artist1.setName("Vijay");
			artist1.setAddress("Hyderabad");
			artist1.setCategory("Actor");
			artist1.setAid(1011);
			ses.save(artist1);
			tx.commit();
			System.out.println("Record inserted...");
		}
		catch(HibernateException he) {
			System.out.println("Record not inserted...");
			he.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("Record not inserted...");
			e.printStackTrace();
		}
	}

}
