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
			Artist artist1= new Artist(7,"Dhoni","India",7777777l,"Cricketer");
			Artist artist2= new Artist(18,"Kohli","India",18181818L,"Cricketer");
			Artist artist3= new Artist(101,"Hrithik","India",12121212l,"Actor");
			ses.save(artist1);
			ses.save(artist2);
			ses.save(artist3);
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
