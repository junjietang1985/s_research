package com.junjie.app;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.junjie.service.sync.SyncMovingAverageService;

public class SyncStockMaApp
{

	Logger log = Logger.getLogger(this.getClass());

	private void work()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-context.xml");
		SyncMovingAverageService syncMovingAverageService = (SyncMovingAverageService) context.getBean("syncMovingAverageService");
//		syncMovingAverageService.sync();

	}

	public static void main(String[] args)
	{
		new SyncStockMaApp().work();
	}

}
