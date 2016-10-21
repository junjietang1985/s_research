package com.junjie.app;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.junjie.service.sync.SyncMacdService;

public class SyncStockMacdApp
{

	Logger log = Logger.getLogger(this.getClass());

	private void work()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-context.xml");
		SyncMacdService syncMacdService = (SyncMacdService) context.getBean("syncMacdService");
		syncMacdService.sync();
	}

	public static void main(String[] args)
	{
		new SyncStockMacdApp().work();
	}

}
