package com.kc.test;

/*
	Date: 30/10/2023
	Author @Arun.kc
	NOTE:: If we don't get record/id not present in db
	       then it will hit db again...
	       But if we get record from db, and we call it again, it will read from
	       L1 cache only...won't hit db again. 
	To remove particular obj from cache we use ses.evict(obj) method
	To empty all object from L1 cahce we use ses.clear();       
*/

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.kc.entity.Product;
import com.kc.utility.HibernateUtil;

public class L1CacheSelectTest {

	public static void main(String[] args) {
		// get SessionFactory
		SessionFactory factory=HibernateUtil.getSessionFactory();
		//get Session
		Session ses= HibernateUtil.getSession();
		
		try(factory;ses){
			//load obj
//			As this is first time so collects from DB and puts in L1 cache and gives to Application
			Product p1= ses.get(Product.class, 3);
			System.out.println(p1);
			System.out.println("=============================");
			//Accessing same record:: collets from L1 cache and gives to Application [does not hit db this time]
			Product p2= ses.get(Product.class, 3);
			System.out.println(p2);
			System.out.println("=======================================");
			
//			ses.evict(p2); // to remove product object with id value 3 from L1 cache
			ses.clear();
			
			Product p3= ses.get(Product.class, 3); // this time this will hit the db again, as object with id value 3 is already removed from cache
			System.out.println(p3);
			
		}
		catch(HibernateException he) {
			System.out.println("Error while loaidng record L1CacheSelectTest.main()");
			he.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("Error while loading record L1CacheSelectTest.main()");
			e.printStackTrace();
		}
		

	}

}
