package com.junjie.app;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.junjie.service.sync.SyncStockHistoryService;

/**
 * This app syncs all the stocks which are needed (during developing peroiod, it's sssz300 stock and allow sync=true)
 */
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
