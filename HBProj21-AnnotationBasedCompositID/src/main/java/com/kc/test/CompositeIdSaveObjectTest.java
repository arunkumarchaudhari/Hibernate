package com.kc.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kc.entity.ProgrammerProjId;
import com.kc.entity.ProgrammerProjectInfo;
import com.kc.utility.HibernateUtil;

public class CompositeIdSaveObjectTest {
	public static void main(String[] args) {
		SessionFactory  factory=HibernateUtil.getSessionFactory();
		Session ses=HibernateUtil.getSession();
		Transaction tx=null;
		try(factory;ses){
			tx=ses.beginTransaction();
			//prpare composite id value
			ProgrammerProjId id= new ProgrammerProjId(101,1001);
			ProgrammerProjectInfo info = new ProgrammerProjectInfo(id,"arun.kc","oepnFx",10);
			
			//save obj
			ProgrammerProjId idVal = (ProgrammerProjId) ses.save(info);
			tx.commit();
			System.out.println("Object is saved having composit id::"+idVal);
			
		}
		catch(HibernateException he) {
			System.out.println("Error while updating data in DB...");
			he.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("Error while updating data in DB...");
			e.printStackTrace();
		}
		
	}
}
