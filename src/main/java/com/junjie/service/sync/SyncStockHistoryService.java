package com.junjie.service.sync;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.junjie.dao.StockDao;
import com.junjie.dao.StockHistoryDao;
import com.junjie.model.Stock;
import com.junjie.model.StockHistory;
import com.junjie.utils.YahooFinanceUtils;

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

	public void sync(boolean isForceSync)
	{
		List<Integer> stockIds = this.stockDao.getStockIdByAllowSyncIsSssz300(true, true);
		log.info(String.format("found %d stocks which are allow synced and belong to sssz300 stocks", stockIds.size()));

		for (Integer stockId : stockIds)
		{
			Stock stock = this.stockDao.getById(stockId);
			if (isForceSync)
			{
				log.info(String.format("force syncing stock: %d", stockId));
				String targetUrl = YahooFinanceUtils.getDownloadFullHistoricalPricesUrl(stock.getStockCode());
				List<StockHistory> stockHistoryList = this.syncStockFromYahooFinanceService.getStockHistory(stock.getStockCode(), targetUrl);
				stockHistoryList.forEach(sh -> this.saveWithLog(sh));
			}
			else
			{
				Date lastSyncDate = stockHistoryDao.getLastSyncDate(stock.getStockCode());
				//TODO
				System.out.println("last sync date" + lastSyncDate);
				LocalDate from = lastSyncDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(1);
				LocalDate to = LocalDate.now().minusDays(1);
				if (from.isBefore(to))
				{
					String targetUrl = YahooFinanceUtils.getDownloadPartialHistoricalPricesUrl(stock.getStockCode(), from, LocalDate.now());
					List<StockHistory> stockHistoryList = this.syncStockFromYahooFinanceService.getStockHistory(stock.getStockCode(), targetUrl);
					log.info(String.format("syncing stock history of stock %s for %d days (may include days without trading)", stock.getStockCode(),
						stockHistoryList.size()));
					// filter the date before last sync date
					// filter the date containing invalid date (e.g., holidays & not be able to trade)
					stockHistoryList.stream().filter(sh -> sh.getDate().after(lastSyncDate)).filter(sh -> sh.isValid())
						.forEach(sh -> this.saveWithLog(sh));
				}
				else
				{
					log.info(String.format("stock %s is already up-to-date, nothing to sync", stock.getStockCode()));
				}
			}
		}
	}
	public void saveWithLog(StockHistory stockHistory)
	{
		log.info(String.format("saving into db: %s", stockHistory));
		this.stockHistoryDao.save(stockHistory);
	}
}
