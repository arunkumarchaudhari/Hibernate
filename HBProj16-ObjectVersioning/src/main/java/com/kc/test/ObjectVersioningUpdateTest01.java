package com.kc.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kc.entity.MobilePhoneUser;
import com.kc.utility.HibernateUtil;

public class ObjectVersioningUpdateTest01 {
	public static void main(String[] args) {
		SessionFactory  factory=HibernateUtil.getSessionFactory();
		Session ses=HibernateUtil.getSession();
		Transaction tx=null;
		try(factory;ses){
			tx=ses.beginTransaction();
			//Load object
			MobilePhoneUser user= ses.get(MobilePhoneUser.class, 1l);
			if(user==null)
				System.out.println("No record found...");
			else {
				//update object
				user.setCircle("Kolkata");
				ses.update(user);
				tx.commit();
				System.out.println("Record updated for :: "+user.getUpdationsCount()+" times");
			}
			
		}
		catch(HibernateException he) {
			System.out.println("Error while updating data in DB...");
			he.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("Error while updating data in DB...");
			e.printStackTrace();
		}
		
	}
}
