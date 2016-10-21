package com.junjie.dao;

import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.junjie.model.StockHistory;
import com.junjie.utils.DaoUtils;
import com.junjie.utils.enumeration.Orderby;

public class StockHistoryDao extends JdbcDaoSupport
{

	private static String TABLE_NAME = "stock_history";

	public int save(StockHistory stockHistory)
	{
		String sql = "INSERT INTO " + TABLE_NAME + " (stock_code, date, open, high, low, close, volume, adjClose) VALUES (?,?, ?, ?, ?, ?, ?, ?)";
		return getJdbcTemplate().update(
			sql,
			new Object[] { stockHistory.getStockCode(), stockHistory.getDate(), stockHistory.getOpen(), stockHistory.getHigh(),
							stockHistory.getLow(), stockHistory.getClose(), stockHistory.getVolume(), stockHistory.getAdjClose() });
	}

	public Date getLastSyncDate(String stockCode)
	{
		String sql = "SELECT date FROM " + TABLE_NAME + " WHERE stock_code = " + DaoUtils.formatQuatation(stockCode) + " ORDER BY date DESC "
				+ " LIMIT 1";
		return getJdbcTemplate().queryForObject(sql, Date.class);
	}

	public List<StockHistory> getFrom(String stockCode, Date from, Orderby dateOrder)
	{
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE stock_code = " + DaoUtils.formatQuatation(stockCode) + " AND date >= "
				+ DaoUtils.formatForDB(from);
		sql += dateOrder == null ? "" : " ORDER BY date " + dateOrder;
		return getJdbcTemplate().queryForList(sql, StockHistory.class);
	}
}
