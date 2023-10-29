package com.kc.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kc.entity.ProgrammerProjId;
import com.kc.entity.ProgrammerProjectInfo;
import com.kc.utility.HibernateUtil;

public class CompositeIdLoadObjectTest {
	public static void main(String[] args) {
		SessionFactory  factory=HibernateUtil.getSessionFactory();
		Session ses=HibernateUtil.getSession();
		Transaction tx=null;
		try(factory;ses){
			tx=ses.beginTransaction();
			//prpare composite id value
			ProgrammerProjId id= new ProgrammerProjId(101,1001);
			//Load obj
			ProgrammerProjectInfo info = ses.get(ProgrammerProjectInfo.class, id);
			if(info==null)
				System.out.println("Record not Found.");
			else
				System.out.println(info);
			
		}
		catch(HibernateException he) {
			System.out.println("Error while fetching data in DB...");
			he.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("Error while fetching data in DB...");
			e.printStackTrace();
		}
		
	}
}
