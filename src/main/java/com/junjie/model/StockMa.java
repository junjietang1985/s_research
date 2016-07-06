package com.junjie.model;

import java.util.Date;

public class StockMa
{
	private Long id;
	private String stockCode;
	private Date date;
	private Float ma5;
	private Float ma10;
	private Float ma20;
	private Float ma30;
	private Float ma60;

	public StockMa()
	{
		
	}

	public String getStockCode()
	{
		return stockCode;
	}

	public void setStockCode(String stockCode)
	{
		this.stockCode = stockCode;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public Float getMa5()
	{
		return ma5;
	}

	public void setMa5(Float ma5)
	{
		this.ma5 = ma5;
	}

	public Float getMa10()
	{
		return ma10;
	}

	public void setMa10(Float ma10)
	{
		this.ma10 = ma10;
	}

	public Float getMa20()
	{
		return ma20;
	}

	public void setMa20(Float ma20)
	{
		this.ma20 = ma20;
	}

	public Float getMa30()
	{
		return ma30;
	}

	public void setMa30(Float ma30)
	{
		this.ma30 = ma30;
	}

	public Float getMa60()
	{
		return ma60;
	}

	public void setMa60(Float ma60)
	{
		this.ma60 = ma60;
	}

}
