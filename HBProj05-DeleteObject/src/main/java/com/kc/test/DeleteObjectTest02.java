package com.kc.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kc.entity.Product;
import com.kc.utility.HibernateUtil;

/**
  Author @ Arun KC 
  Date:: 12/10/2023 
  Approach 2:
  			Load Record, check it(If exist Delete else give message to the end user).
  
 */
public class DeleteObjectTest02 {
	public static void main(String[] args) {
		// get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		// get Session object
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try (factory; ses) {
			// load object
			Product prod1 = ses.get(Product.class, 1809);

			if (prod1 == null)
				System.out.println("No record available to delete..");
			else {
				// Begin Tx
				tx = ses.beginTransaction();
				ses.delete(prod1);
				tx.commit();
				System.out.println("Record Deleted.");
			}
		} catch (HibernateException he) {
			if (tx != null || tx.getStatus() != null || tx.getRollbackOnly())
				tx.rollback();
			System.out.println("Exception occured while deleting records from table:: ");
			he.printStackTrace();
		}
	}
}
