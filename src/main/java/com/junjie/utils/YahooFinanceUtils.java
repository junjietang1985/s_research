package com.junjie.utils;

import java.time.LocalDate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class helps to generate URLs for downloading stock data from yahoo
 */
public class YahooFinanceUtils
{

	// 2000/Jan/4-2016/Aug/12
	//http://chart.finance.yahoo.com/table.csv?s=601600.SS&a=0&b=4&c=2000&d=7&e=12&f=2016&g=d&ignore=.csv
	// remember the month uses 0-11
	public static final String PARTIAL_HISTORICAL_PRICES_URL_FORMAT = "http://chart.finance.yahoo.com/table.csv?s=%s&a=%s&b=%s&c=%s&d=%s&e=%s&f=%s&g=d&ignore=.csv";

	private static final Log LOGGER = LogFactory.getLog(YahooFinanceUtils.class);

	/**
	 * returns a URL for downloading a csv in designated date range (including from & to)
	 */
	public static String getDownloadPartialHistoricalPricesUrl(String stockCode, LocalDate from, LocalDate to)
	{
		return String.format(PARTIAL_HISTORICAL_PRICES_URL_FORMAT, stockCode, from.getMonthValue() - 1, from.getDayOfMonth(), from.getYear(),
			to.getMonthValue() - 1, to.getDayOfMonth(), to.getYear());
	}

	/**
	 * returns a URL for downloading a csv from 2005.01.01 to today
	 */
	public static String getDownloadFullHistoricalPricesUrl(String stockCode)
	{
		return getDownloadPartialHistoricalPricesUrl(stockCode, LocalDate.of(2005, 1, 1), LocalDate.now());
	}

	public static void main(String args[])
	{
		System.out.println(getDownloadFullHistoricalPricesUrl("000858.SZ"));
	}
}
