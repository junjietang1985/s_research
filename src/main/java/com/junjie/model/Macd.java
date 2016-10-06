package com.junjie.model;

import java.util.Date;

public class Macd
{
	private Long id;
	private String stockCode;
	private Date date;
	private Double emaFast;
	private Double emaSlow;
	private Double diff;
	private Double dem;
	private Double bar;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getStockCode()
	{
		return stockCode;
	}

	public void setStockCode(String stockCode)
	{
		this.stockCode = stockCode;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public Double getEmaFast()
	{
		return emaFast;
	}

	public void setEmaFast(Double emaFast)
	{
		this.emaFast = emaFast;
	}

	public Double getEmaSlow()
	{
		return emaSlow;
	}

	public void setEmaSlow(Double emaSlow)
	{
		this.emaSlow = emaSlow;
	}

	public Double getDiff()
	{
		return diff;
	}

	public void setDiff(Double diff)
	{
		this.diff = diff;
	}

	public Double getDem()
	{
		return dem;
	}

	public void setDem(Double dem)
	{
		this.dem = dem;
	}

	public Double getBar()
	{
		return bar;
	}

	public void setBar(Double bar)
	{
		this.bar = bar;
	}

}
