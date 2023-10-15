/*
	Author: Arun KC
	version: 1.0
	NOTE:: We have used <property name="hbm2ddl.auto">create|update|validate|create-drop </property> in Hibernate cfg file
		   We can control length also. Have a look in Product.hbm.cfg files for more reference
*/
package com.kc.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kc.entity.Product;
import com.kc.utility.HibernateUtil;

public class SaveObjTest {
	public static void main(String[] args) {
		
		//SessionFactory object
		SessionFactory factory=HibernateUtil.getSessionFactory();
		
		//Create Session object
		Session ses= HibernateUtil.getSession();	
		Transaction tx= null;
		
		try(factory;ses) {
			//Prepare Entity class object having data
			Product p =new Product();
//			p.setPid(17111); // No need as we are using generator class increment in hbm file
			p.setPname("Vivo S1");
			p.setPrice(42000.00);
			p.setQty(2.0);
			p.setStatus("Available");
			tx=ses.beginTransaction();
			ses.save(p);
			tx.commit();
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
