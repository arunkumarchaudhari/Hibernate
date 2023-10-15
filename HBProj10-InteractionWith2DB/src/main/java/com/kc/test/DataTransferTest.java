package com.kc.test;
/*
	Author @ Arun KC
	Requirement::
		Write a Application that transfers a record of oracle db table(Product) to mysql DB table(product)

*/
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kc.entity.Product;
import com.kc.utility.HibernateUtil_MySql;
import com.kc.utility.HibernateUtil_Oracle;


public class DataTransferTest {
	public static void main(String[] args) {
		//get SessionFactory obj,ses obj for oracle
		SessionFactory oraFactory = HibernateUtil_Oracle.getSessionFactory();
		Session oraSes=HibernateUtil_Oracle.getSession();
		
		//get SessionFactory obj,ses obj for mysql
		SessionFactory mysqlFactory = HibernateUtil_MySql.getSessionFactory();
		Session mysqlSes=HibernateUtil_MySql.getSession();
		
		Transaction tx=null;
		
		try(oraFactory;oraSes;mysqlFactory;mysqlSes){
			//load obj from oracle
			Product prod1= oraSes.get(Product.class, 4);
			
			if(prod1!=null) {
				//begin tx
				tx=mysqlSes.beginTransaction();
				mysqlSes.save(prod1);
				tx.commit();
				System.out.println("Record Transfered successfully...");
			}
			else
				System.out.println("Record not availabe to transfer...");
			
		}
		catch(HibernateException he) {
			he.printStackTrace();
			if(tx!=null || tx.getStatus()==null || tx.getRollbackOnly()==true)
				tx.rollback();
			System.out.println("Exception occured while Transering record to mySql.");
			he.printStackTrace();
		}
		catch(Exception e) {
			if(tx!=null || tx.getStatus()==null || tx.getRollbackOnly()==true)
				tx.rollback();
			System.out.println("Exception occured while Transering record to mySql.");
			e.printStackTrace();
		}
	}
}
