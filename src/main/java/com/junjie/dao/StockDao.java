package com.junjie.dao;



import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
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
		String sql="INSERT INTO " + TABLE_NAME + "(stock_code, is_sssz300, allow_sync) VALUES (?, ?, ?)";
		return getJdbcTemplate().update(sql, new Object[]{stock.getStockCode(),stock.getIsSssz300(),stock.getAllowSync()});
	}
	
	public Stock getByStockCode(String stockCode){

		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE stock_code = ?";

		try{
			Stock stock = getJdbcTemplate().queryForObject(sql, new Object[] { stockCode },
				new BeanPropertyRowMapper<Stock>(Stock.class));
			return stock;
		}catch(EmptyResultDataAccessException e){
			return null;
		}
		
	}
	
	public List<Integer> getStockIdByAllowSyncIsSssz300(Boolean allowSync, Boolean isSssz300){
		String sql = "SELECT id FROM " + TABLE_NAME + " WHERE allow_sync = ? AND is_sssz300 = ? ";
		return getJdbcTemplate().queryForList(sql, new Object[]{ allowSync, isSssz300},Integer.class);
	}

}
