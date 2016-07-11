package com.junjie.app;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.junjie.sync.SyncStockHistoryService;

public class SyncStockHistoryApp
{

	Logger log = Logger.getLogger(this.getClass());

	private void work()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-context.xml");
		SyncStockHistoryService syncStockHistoryService = (SyncStockHistoryService) context.getBean("syncStockHistoryService");
		syncStockHistoryService.sync();

	}

	public static void main(String[] args)
	{
		new SyncStockHistoryApp().work();
	}

}
