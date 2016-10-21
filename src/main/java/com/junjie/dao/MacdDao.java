package com.junjie.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.junjie.model.Macd;
import com.junjie.utils.DaoUtils;
import com.junjie.utils.enumeration.Orderby;

public class MacdDao extends JdbcDaoSupport
{

	private static String TABLE_NAME = "macd";

	public Macd getById(int id)
	{

		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";

		return getJdbcTemplate().queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<Macd>(Macd.class));
	}

	public List<Macd> getMacdByStockCode(String stockCode, Orderby dateOrder, Integer limit)
	{
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE stock_code = " + DaoUtils.formatQuatation(stockCode);
		sql += dateOrder == null ? "" : " ORDER BY date " + dateOrder;
		sql += limit == null ? "" : " LIMIT " + limit;
		return getJdbcTemplate().queryForList(sql, Macd.class);
	}

}
