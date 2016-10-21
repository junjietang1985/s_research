package com.junjie.service.sync;

import java.util.List;

import org.apache.log4j.Logger;

import com.junjie.dao.MacdDao;
import com.junjie.dao.StockDao;
import com.junjie.dao.StockHistoryDao;
import com.junjie.model.Macd;
import com.junjie.model.Stock;
import com.junjie.model.StockHistory;
import com.junjie.utils.enumeration.Orderby;

public class SyncMacdService
{

	Logger log = Logger.getLogger(this.getClass());

	StockDao stockDao;
	MacdDao macdDao;
	StockHistoryDao stockHistoryDao;

	public void sync()
	{
		List<Integer> stockIds = this.stockDao.getStockIdByAllowSyncIsSssz300(true, true);
		log.info(String.format("found %d stocks which are allow synced and belong to sssz300 stocks", stockIds.size()));
		for (Integer stockId : stockIds)
		{
			Stock stock = this.stockDao.getById(stockId);

			Macd lastSyncedMacd = this.macdDao.getMacdByStockCode(stock.getStockCode(), Orderby.DESC, 1).get(0);

			List<StockHistory> stockHistories = this.stockHistoryDao.getFrom(stock.getStockCode(), lastSyncedMacd.getDate(), Orderby.ASC);
			//
		}
	}
}
