/*
	Author: Arun KC
	version: 1.2
	Date: 10/10/2023
	Reason:: Using Hibernate util class. With Try-with-resource.
*/
package com.kc.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.kc.entity.Product;
import com.kc.utility.HibernateUtil;

public class LoadingObjTestTWR2 {
	public static void main(String[] args) {
		try (SessionFactory factory=HibernateUtil.getFactory();
				Session ses=HibernateUtil.getSession();) {
			
			Product prod1= ses.get(Product.class, 1803);
			if(prod1 !=null)
				System.out.println("Record found:: "+prod1);
			else
				System.out.println("Record not found....");
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
}
