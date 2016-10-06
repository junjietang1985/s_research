package com.junjie.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.junjie.model.Macd;

public class MacdDao extends JdbcDaoSupport
{

	private static String TABLE_NAME = "macd";

	public Macd getById(int id)
	{

		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";

		return getJdbcTemplate().queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<Macd>(Macd.class));
	}

}
