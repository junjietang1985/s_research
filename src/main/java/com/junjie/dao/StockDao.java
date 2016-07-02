package com.junjie.dao;



import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.junjie.model.Stock;

public class StockDao extends JdbcDaoSupport {

	private static String TABLE_NAME = "stock";

	public Stock getById(int id) {

		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";

		return getJdbcTemplate().queryForObject(sql, new Object[] { id },
				new BeanPropertyRowMapper<Stock>(Stock.class));
	}
	
	public int save(Stock stock){
		String sql="INSERT INTO " + TABLE_NAME + "(stock_code, is_sssz300) VALUES (?, ?)";
		return getJdbcTemplate().update(sql, new Object[]{stock.getStockCode(),stock.getIsSssz300()});
	}

}
