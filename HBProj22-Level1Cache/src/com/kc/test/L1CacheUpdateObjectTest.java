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

public class L1CacheUpdateObjectTest {

	public static void main(String[] args) {
		// get SessionFactory
		SessionFactory factory=HibernateUtil.getSessionFactory();
		//get Session
		Session ses= HibernateUtil.getSession();
		Transaction tx=null;
		try(factory;ses){
			
			Product p=ses.get(Product.class, 4);
			if(p==null)
				System.out.println("Record not found");
			else {
				//begin tx
				tx=ses.beginTransaction();
				p.setPrice(53999.0);//modification 1->reflects to L1 cache object
				p.setQty(3.0); //modification 2 ->reflects to L1 cache object
				tx.commit(); //generate single sql query reflecting both modification
				System.out.println("Record updated");
			}
		}
		catch(HibernateException he) {
			System.out.println("Error while updating record L1CacheSelectTest.main()");
			he.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("Error while updating record L1CacheSelectTest.main()");
			e.printStackTrace();
		}
		

	}

}
