/*
	Author @Arun KC
	Hibernate utility class-> for Re-usability purpose
	Date: 10/10/2023
*/
package com.kc.utility;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory factory;

	static {
		try {
			// Configuration object
			Configuration cfg = new Configuration();
			cfg.configure("/com/kc/cfgs/hibernate.cfg.xml");

			// build SessionFactory
			factory = cfg.buildSessionFactory();
		} catch (HibernateException he) {
			he.printStackTrace();
		}
	}

	public static SessionFactory getSessionFactory() {
		if(factory !=null)
			return factory;
		else
			throw new RuntimeException("Exception occured while creating SessionFacotory Ojbect");
	}
	//Logic to open the session
	public static Session getSession() {
		Session ses=null;
		if(factory!=null)
			ses=factory.openSession();
		return ses;
	}
	
	public static void closeSession(Session ses) {
		if(ses!=null)
			ses.close();
	}
	
	public static void closeSessionFactory() {
		if(factory!=null)
			factory.close();
	}
	
	
	
}
