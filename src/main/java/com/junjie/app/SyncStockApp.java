package com.junjie.app;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.junjie.dao.StockDao;
import com.junjie.dao.StockHistoryDao;
import com.junjie.model.Stock;
import com.junjie.model.StockHistory;
import com.junjie.sync.SyncStockFromYahooFinanceService;

public class SyncStockApp {

	Logger log = Logger.getLogger(this.getClass());
	
	private void work() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Spring-context.xml");
		StockDao stockDao = (StockDao) context.getBean("stockDao");
		List<Integer> stockIds = stockDao.getStockIdByAllowSyncIsSssz300(true, true);
		log.info(String.format("found %d stocks which are allow synced and sssz300 stocks", stockIds.size()));
		
		SyncStockFromYahooFinanceService syncStockFromYahooFinanceService = (SyncStockFromYahooFinanceService) context.getBean("syncStockFromYahooFinanceService");
		StockHistoryDao stockHistoryDao = (StockHistoryDao) context.getBean("stockHistoryDao");
		
		for(Integer stockId:stockIds){
			Stock stock = stockDao.getById(stockId);
			List<StockHistory> stockHistoryList = syncStockFromYahooFinanceService.getStockHistory(stock.getStockCode());
			log.info(String.format("syncing stock history for stock %s", stock.getStockCode()));
			stockHistoryList.forEach(sh->stockHistoryDao.save(sh));
		}
		
	}
	
	public static void main(String[] args) {
		new SyncStockApp().work();
	}

}
