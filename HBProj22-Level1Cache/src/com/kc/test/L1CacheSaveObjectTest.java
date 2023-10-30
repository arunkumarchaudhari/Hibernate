package com.kc.test;

/*
	Date: 30/10/2023
	Author @Arun.kc
	NOTE:: If we don't get record/id not present in db
	       then it will hit db again...
	       But if we get record from db, and we call it again, it will read from
	       L1 cache only...won't hit db again. 
*/

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kc.entity.Product;
import com.kc.utility.HibernateUtil;

public class L1CacheSaveObjectTest {

	public static void main(String[] args) {
		// get SessionFactory
		SessionFactory factory=HibernateUtil.getSessionFactory();
		//get Session
		Session ses= HibernateUtil.getSession();
		Transaction tx=null;
		try(factory;ses){
			//begin tx
			tx=ses.beginTransaction();
			
			Product p =new Product();
			p.setPid(4);
			p.setPname("Nothing");
			p.setPrice(43999.0);
			p.setQty(2.0);
			p.setStatus("Available"); //keeps p obj in L1 cache
			int idVal=(int) ses.save(p);
			System.out.println("Generated id value::"+idVal);
			tx.commit();//generates the insert query by collecting P obj from L1 cache
			System.out.println("Record inserted");
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
