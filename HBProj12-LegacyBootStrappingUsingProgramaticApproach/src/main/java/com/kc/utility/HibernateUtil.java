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
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory factory;

	static {
		try {
			
			Configuration cfg = new Configuration();
//			cfg.configure(); //Even if we don't call now not a issue.
			cfg.setProperty("hibernate.connection.driver_class", "oracle.jdbc.driver.OracleDriver");
			cfg.setProperty("hibernate.connection.url", "jdbc:oracle:thin:@localhost:1521:orcl");
			cfg.setProperty("hibernate.connection.username", "system");
			cfg.setProperty("hibernate.connection.password", "Arunkc9900");
			cfg.setProperty("hibernate.show_sql", "true");
			cfg.setProperty("hibernate.format_sql", "true");
			cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle12cDialect");
			
			cfg.addFile("src/main/java/com/kc/entity/Product.hbm.xml");
			// build SessionFactory
			factory = cfg.buildSessionFactory();
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
