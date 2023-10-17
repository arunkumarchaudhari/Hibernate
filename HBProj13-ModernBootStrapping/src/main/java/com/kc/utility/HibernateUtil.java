/*
	Author @Arun KC
	Hibernate utility class-> for Re-usability purpose
	Date: 10/10/2023
*/
package com.kc.utility;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory factory;

	static {
		try {
			//Activate Hibernate
			Configuration cfg = new Configuration();
			cfg.configure("com/kc/cfgs/hibernate.cfg.xml");
			cfg.addResource("com/kc/entity/Product.hbm.xml");
			//Create ServiceRegistryBuilder
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
			
			//Create ServiceRegistry
			StandardServiceRegistry registry = builder.applySettings(cfg.getProperties()).build();
			
			
			// build SessionFactory
			factory = cfg.buildSessionFactory(registry);
		}catch (HibernateException he) {
			he.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
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
