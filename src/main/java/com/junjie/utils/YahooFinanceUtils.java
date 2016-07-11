package com.junjie.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class YahooFinanceUtils
{
	public static final String DOWNLOAD_TO_SPREADSHEET_URL_PREFIX = "http://real-chart.finance.yahoo.com/table.csv";

	public static final String FULL_HISTORICAL_PRICES_URL_PREFIX = "http://finance.yahoo.com/q/hp?s=";

	public static final String FULL_HISTORICAL_PRICES_URL_SUFFIX = "+Historical+Prices";

	// m d y
	//http://chart.finance.yahoo.com/table.csv?s=000858.SZ&a=0&b=1&c=1980&d=0&e=1&f=2016&g=d&ignore=.csv

	//	public static final String PARTIAL_HISTORICAL_PRICES_URL_PREFIX = "http://chart.finance.yahoo.com/table.csv?s=";
	//
	//	public static final String PARTIAL_HISTORICAL_PRICES_URL_SUFFIX = "&g=d&ignore=.csv";

	/**
	 * e.g.<br>
	 * http://finance.yahoo.com/q/hp?s=000858.SZ+Historical+Prices
	 */

	public static String getFullHistoricalPricesURL(String stockCode)
	{
		return FULL_HISTORICAL_PRICES_URL_PREFIX + stockCode + FULL_HISTORICAL_PRICES_URL_SUFFIX;
	}

	/**
	 * returns the spread sheet url for downloading the full historical data of the stock <br>
	 * e.g.<br>
	 * http://real-chart.finance.yahoo.com/table.csv?s=601600.SS&amp;d=6&amp;e=11&amp;f=2016&amp;g=d&amp;a=0&amp;b=4&amp;c=2000&amp;ignore=.csv
	 */
	public static String getDownloadToSpreadsheetURLForFullHistory(String stockCode)
	{
		String targetUrl = getFullHistoricalPricesURL(stockCode);
		try
		{
			URL url = new URL(targetUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			int responseCode = conn.getResponseCode();
			System.out.println(responseCode);
			try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())))
			{
				String line;
				while ((line = br.readLine()) != null)
				{
					if (!line.contains("Download to Spreadsheet"))
					{
						continue;
					}
					String prefix = "[\\s\\S]*<a href=\"http:\\/\\/real-chart.finance.yahoo.com\\/table.csv";
					String suffix = "\"+[\\s\\S]*";
					String downloadToSpreadsheetURL = DOWNLOAD_TO_SPREADSHEET_URL_PREFIX + line.replaceAll(prefix, "").replaceAll(suffix, "");
					return downloadToSpreadsheetURL;
				}
			}

		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String args[])
	{
		System.out.println(getDownloadToSpreadsheetURLForFullHistory("000858.SZ"));
	}
}
