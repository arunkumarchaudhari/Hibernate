package com.kc.test;

import java.util.Timer;

/*
	Date: 30/10/2023
	Author @Arun.kc
	NOTE:: This will use L1CacheClearUpService(defined by me) to to clear L1 cache
	       Now no need to write ses.clear() or ses.evict() method also.
	       
*/

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.kc.entity.Product;
import com.kc.scheduling.L1CacheCleanUpService;
import com.kc.utility.HibernateUtil;

public class L1CacheTimerSelectTest {

	public static void main(String[] args) {
		// get SessionFactory
		SessionFactory factory=HibernateUtil.getSessionFactory();
		//get Session
		Session ses= HibernateUtil.getSession();
		Timer timer = new Timer();
		//Below method is like for first time 1 sec[initial delay 1 sec] and every 2 second L1 cache will be cleared.
		//As after every 2 second L1CacheCleanUpService method's run method will be called and we have defined logic to clear L1 cache in that method.
		//We have taken initial delay 1 second, because if within 1 second our application executes entire part, we can't see the rest.
		// as already execution is over. For that reason I have kept initial delay as 1 second.
		timer.schedule(new L1CacheCleanUpService(ses),1000,2000);
		
		try(factory;ses){
			//load obj
//			As this is first time so collects from DB and puts in L1 cache and gives to Application
			Product p1= ses.get(Product.class, 3);
			System.out.println(p1);
			Thread.sleep(5000);
			System.out.println("=============================");
			//Accessing same record:: collets from L1 cache and gives to Application [does not hit db this time]
			Product p2= ses.get(Product.class, 3);
			System.out.println(p2);
			System.out.println("=======================================");
			Thread.sleep(2000);
//			ses.evict(p2); // to remove product object with id value 3 from L1 cache
//			ses.clear(); //Now even if we won't call clear, L1CacheClearNupService will be executed as per the scheduled above.
			
			Product p3= ses.get(Product.class, 3); // this time this will hit the db again, as object with id value 3 is already removed from cache
			System.out.println(p3);
			
		}
		catch(HibernateException he) {
			System.out.println("Error while loaidng record L1CacheSelectTest.main()");
			he.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("Error while loading record L1CacheSelectTest.main()");
			e.printStackTrace();
		}
		

	}

}
