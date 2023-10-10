/*
	Author @ Arun KC
	
	Purpose: To perform update
	Approach:: Load and update object in Transactional Environment...without calling ses.update(-) method.
	Advantage: We can modify only that part which we want to modify.
	
	NOTE:: For this approach it is recommended to use dynamic-update="true" in hbm file's <class>.
           So that only those column will be participated whose data are modified.
	
*/
package com.kc.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kc.entity.Product;
import com.kc.utility.HibernateUtil;

public class UpdateObjectTest3 {
	public static void main(String[] args) {
		SessionFactory factory= HibernateUtil.getSessionFactory();
		Session ses=HibernateUtil.getSession();
		Transaction tx= null;
		try(factory;ses){
			//Load object
			Product prod1=ses.get(Product.class, 1810);
			if(prod1 ==null)
				System.out.println("No record found to update.");
			else {
				//Begin Transaction
				tx=ses.beginTransaction();
				
				//Now we can modify only what we want to modify.
				//Let's modify price only
				prod1.setPrice(130.0);
				//Not calling ses.update(-) method...then also it is responsibility of hibernate to update it.
				tx.commit();
				System.out.println("Record found and updated.");
			}	
		}
		catch(HibernateException he) {
			if(tx!=null || tx.getStatus()==null ||  tx.getRollbackOnly()==true)
				tx.rollback();
			System.out.println("Error while updating record.");
			he.printStackTrace();
		}
		catch(Exception e) {
			if(tx!=null || tx.getStatus()==null ||  tx.getRollbackOnly()==true)
				tx.rollback();
			System.out.println("Error while updating record.");
			e.printStackTrace();
		}
	}
}
