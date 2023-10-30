package com.kc.scheduling;

import java.util.TimerTask;

import org.hibernate.Session;

public class L1CacheCleanUpService extends TimerTask{
	private Session ses=null;
	
	public L1CacheCleanUpService(Session ses) {
		System.out.println("L1CacheCleanUpServie.L1CacheCleanUpServie()");
		this.ses=ses;
	}

	@Override
	public void run() {
		System.out.println("L1CacheCleanUpServie.run()");
		ses.clear();
		
	}
	
}
