package com.junjie.model;

public class Stock {
	int id;
	String stockCode;
	boolean isSssz300;
	boolean allowSync;
	
	public boolean getIsSssz300() {
		return isSssz300;
	}
	public void setIsSssz300(boolean isSssz300) {
		this.isSssz300 = isSssz300;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public boolean getAllowSync() {
		return allowSync;
	}
	public void setAllowSync(boolean allowSync) {
		this.allowSync = allowSync;
	}
	
	
}
