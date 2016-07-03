package com.junjie.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.junjie.model.StockHistory;

public class StockHistoryDao extends JdbcDaoSupport {

	private static String TABLE_NAME = "stock_history";

	public int save(StockHistory stockHistory) {
		String sql = "INSERT INTO "
				+ TABLE_NAME
				+ "(stock_code, date, open, high, low, close, volume, adjClose) VALUES (?,?, ?, ?, ?, ?, ?, ?)";
		return getJdbcTemplate().update(
				sql,
				new Object[] { stockHistory.getStockCode(),
						stockHistory.getDate(), stockHistory.getOpen(),
						stockHistory.getHigh(), stockHistory.getLow(),
						stockHistory.getClose(), stockHistory.getVolume(),
						stockHistory.getAdjClose() });
	}
}
