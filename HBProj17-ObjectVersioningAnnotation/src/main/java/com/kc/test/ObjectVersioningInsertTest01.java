package com.kc.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kc.entity.MobilePhoneUser;
import com.kc.utility.HibernateUtil;

public class ObjectVersioningInsertTest01 {
	public static void main(String[] args) {
		SessionFactory  factory=HibernateUtil.getSessionFactory();
		Session ses=HibernateUtil.getSession();
		Transaction tx=null;
		try(factory;ses){
			tx=ses.beginTransaction();
			//prepare obj
			MobilePhoneUser user1= new MobilePhoneUser(9811709900l, true, "Nepal", "ding ding");
			MobilePhoneUser user2= new MobilePhoneUser(9031933305l, false, "Bhubaneswar", "jaldi waha se hato");
			//save obj
			ses.save(user1);
			ses.save(user2);
			//commit tx
			tx.commit();
			System.out.println("Data inserted into DB...");
			
		}
		catch(HibernateException he) {
			System.out.println("Error while inserting data in DB...");
			he.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("Error while inserting data in DB...");
			e.printStackTrace();
		}
		
	}
}
