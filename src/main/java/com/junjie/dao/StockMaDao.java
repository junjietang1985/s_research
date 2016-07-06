package com.junjie.dao;

import java.util.Date;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.junjie.model.StockMa;

public class StockMaDao extends JdbcDaoSupport
{
	private static String TABLE_NAME = "stock_ma";

	public int save(StockMa stockMa)
	{

		String sql = "INSERT INTO " + TABLE_NAME + "(stock_code, date, ma5, ma10, ma20, ma30, ma60) VALUES (?, ?, ?, ?, ?, ?, ?)";
		return getJdbcTemplate().update(
			sql,
			new Object[] { stockMa.getStockCode(), stockMa.getDate(), stockMa.getMa5(), stockMa.getMa10(), stockMa.getMa20(), stockMa.getMa30(),
							stockMa.getMa60() });
	}

	public StockMa getByStockCodeAndDate(String stockCode, Date date)
	{

		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE stock_code = ? AND date = ?";

		try
		{
			StockMa stockMa = getJdbcTemplate().queryForObject(sql, new Object[] { stockCode, date },
				new BeanPropertyRowMapper<StockMa>(StockMa.class));
			return stockMa;
		}
		catch (EmptyResultDataAccessException e)
		{
			return null;
		}

	}
}
