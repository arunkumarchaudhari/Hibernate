package com.kc.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kc.entity.Artist;
import com.kc.utility.HibernateUtil;

public class LoadObjectTest {

	public static void main(String[] args) {
		//get SessionFactory 
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session ses=HibernateUtil.getSession();
		try(factory;ses){
			//Load object
			int aid=1818;
			Artist artist1= ses.get(Artist.class, aid);
			if(artist1!=null)
				System.out.println("Record Found:: "+artist1);
			else
				System.out.println("No recourd found with the given Aid:: "+aid);
		}
		catch(HibernateException he) {
			System.out.println("Exception occured while fetching the record from Artist");
			he.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("Exception occured while fetching the record from Artist");
			e.printStackTrace();
		}
	}

}
