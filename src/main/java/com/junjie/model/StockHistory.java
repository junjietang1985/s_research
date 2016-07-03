package com.junjie.model;

import java.util.Date;

public class StockHistory
{
	private Long id;
	private String stockCode;
	private Date date;
	private Float open;
	private Float high;
	private Float low;
	private Float close;
	private Long volume;
	private Float adjClose;

	public StockHistory(Long id,String stockCode, Date date, Float open, Float high, Float low, Float close, Long volume, Float adjClose)
	{
		super();
		this.id = id;
		this.stockCode = stockCode;
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
		this.adjClose = adjClose;
	}
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public Float getOpen()
	{
		return open;
	}
	public void setOpen(Float open)
	{
		this.open = open;
	}
	public Float getHigh()
	{
		return high;
	}
	public void setHigh(Float high)
	{
		this.high = high;
	}
	public Float getLow()
	{
		return low;
	}
	public void setLow(Float low)
	{
		this.low = low;
	}
	public Float getClose()
	{
		return close;
	}
	public void setClose(Float close)
	{
		this.close = close;
	}
	public Float getAdjClose()
	{
		return adjClose;
	}
	public void setAdjClose(Float adjClose)
	{
		this.adjClose = adjClose;
	}
	public Date getDate()
	{
		return date;
	}
	public void setDate(Date date)
	{
		this.date = date;
	}
	public Long getVolume()
	{
		return volume;
	}
	public void setVolume(Long volume)
	{
		this.volume = volume;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	@Override
	public String toString()
	{
		return String.format("StockHistory [id: %d, stockCode: %s, date: %s, open: %f, high: %f, low: %f, close: %f, volume: %d, adjClose: %f]", id, stockCode, date, open,
			high, low, close, volume, adjClose);
	}
	public static Builder builder()
	{
		return new Builder();
	}

	public static class Builder
	{
		private Long id;
		private String stockCode;
		private Date date;
		private Float open;
		private Float high;
		private Float low;
		private Float close;
		private Long volume;
		private Float adjClose;

		public Builder id(Long id)
		{
			this.id = id;
			return this;
		}
		public Builder stockCode(String stockCode){
			this.stockCode = stockCode;
			return this;
		}
		public Builder date(Date date)
		{
			this.date = date;
			return this;
		}
		public Builder open(Float open)
		{
			this.open = open;
			return this;
		}
		public Builder high(Float high)
		{
			this.high = high;
			return this;
		}
		public Builder low(Float low)
		{
			this.low = low;
			return this;
		}
		public Builder close(Float close)
		{
			this.close = close;
			return this;
		}
		public Builder volume(Long volume)
		{
			this.volume = volume;
			return this;
		}
		public Builder adjClose(Float adjClose)
		{
			this.adjClose = adjClose;
			return this;
		}
		public StockHistory build()
		{
			return new StockHistory(id, stockCode, date, open, high, low, close, volume, adjClose);
		}
	}

}
