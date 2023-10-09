/*
	Author: Arun KC
	version: 1.0
	Date: 10/10/2023

	Q) Though Entity class is taken as final class, How can we make ses.load(-,-) method performing lazy loading ??
		STEP1: Take proxy interface having the declaration of getter, setter methods.
		STEP2: Make Entity class as final class and also implement the above Proxy interface.
		STEP3: specify Proxy interface name in the "proxy" attribute of <class> and also enable lazy loading
			<class name="com.kc.entity.Product" table="PRODUCT" lazy="true" proxy="com.kc.entity.IProd">
		STEP4: Do the needful in the client Application[this is client application, have a look for the code]
	This project answer above question...refer notes for more understanding...
	         
*/
package com.kc.test;

import java.util.Arrays;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kc.entity.IProd;
import com.kc.entity.Product;

public class LoadingObjUsingLoadMethodTest {
	public static void main(String[] args) {
		
		//activate HB framework
		Configuration cfg = new Configuration();
		
		//specify hibernate configuration file name and location
		cfg.configure("/com/kc/cfgs/hibernate.cfg.xml");
		
		//Create HB SessionFactory object
		SessionFactory factory= cfg.buildSessionFactory();
		
		//Create Session object
		Session ses= factory.openSession();
		
		//Prepare Entity class object having data
				
		try {
			//We have to refer with Interface reference variable.
			IProd proxy= ses.load(Product.class, 1800);
			System.out.println(proxy.getClass());
			System.out.println(proxy.getClass().getSuperclass());
			System.out.println(Arrays.toString(proxy.getClass().getInterfaces())); //here we will see one of the interface is our's created.
			System.out.println("---------------------------------------------------------------");
			System.out.println("Record is:: "+proxy);
			
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//Closing HB object...
			ses.close();
			factory.close();
			System.out.println("All done.");
		}
		
	}
}
