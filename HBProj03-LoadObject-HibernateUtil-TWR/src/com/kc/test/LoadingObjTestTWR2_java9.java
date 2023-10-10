/*
	Author: Arun KC
	version: 1.3
	Date: 10/10/2023
	Reason:: From java9 onwards, the variable which we are using inside
	         try with resource can be declared outside.
			 
*/
package com.kc.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.kc.entity.Product;
import com.kc.utility.HibernateUtil;

public class LoadingObjTestTWR2_java9 {
	public static void main(String[] args) {
		SessionFactory factory=HibernateUtil.getFactory();
		
		try(factory){
			Session ses=HibernateUtil.getSession();
			try(ses){
				Product prod1= ses.get(Product.class, 1803);
				if(prod1 !=null)
					System.out.println("Record found:: "+prod1);
				else
					System.out.println("Record not found....");
			}
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
}
