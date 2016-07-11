package com.junjie.sync;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.junjie.dao.StockDao;
import com.junjie.dao.StockHistoryDao;
import com.junjie.model.Stock;
import com.junjie.model.StockHistory;

public class SyncStockHistoryService
{

	Logger log = Logger.getLogger(this.getClass());

	StockDao stockDao;

	StockHistoryDao stockHistoryDao;

	SyncStockFromYahooFinanceService syncStockFromYahooFinanceService;

	public SyncStockHistoryService()
	{

	}

	@Autowired
	public SyncStockHistoryService(StockDao stockDao, StockHistoryDao stockHistoryDao,
			SyncStockFromYahooFinanceService syncStockFromYahooFinanceService)
	{
		this.stockDao = stockDao;
		this.stockHistoryDao = stockHistoryDao;
		this.syncStockFromYahooFinanceService = syncStockFromYahooFinanceService;
	}

	public void sync()
	{
		List<Integer> stockIds = this.stockDao.getStockIdByAllowSyncIsSssz300(true, true);
		log.info(String.format("found %d stocks which are allow synced and sssz300 stocks", stockIds.size()));

		for (Integer stockId : stockIds)
		{
			Stock stock = this.stockDao.getById(stockId);
			Date lastSyncDate = stockHistoryDao.getLastSyncDate(stock.getStockCode());
			//TODO
			System.out.println(lastSyncDate);
			List<StockHistory> stockHistoryList = this.syncStockFromYahooFinanceService.getStockHistory(stock.getStockCode());
			log.info(String.format("syncing stock history of stock %s for %d days", stock.getStockCode(), stockHistoryList.size()));
			stockHistoryList.stream().filter(sh -> sh.getDate().after(lastSyncDate)).forEach(sh -> this.saveWithLog(sh));
		}
	}

	public void saveWithLog(StockHistory stockHistory)
	{
		log.info(String.format("saving into db: %s", stockHistory));
		this.stockHistoryDao.save(stockHistory);
	}
}
