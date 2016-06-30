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

	public static final String HISTORICAL_PRICES_URL_PREFIX = "http://finance.yahoo.com/q/hp?s=";

	public static final String HISTORICAL_PRICES_URL_SUFFIX = "+Historical+Prices";

	/**
	 * returns the spread sheet url for downloading the historical data of the stock
	 */
	public static String getDownloadToSpreadsheetURL(String stockCode)
	{
		String targetUrl = getHistoricalPricesURL(stockCode);
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

	//http://finance.yahoo.com/q/hp?s=000858.SZ+Historical+Prices
	public static String getHistoricalPricesURL(String stockCode)
	{
		return HISTORICAL_PRICES_URL_PREFIX + stockCode + HISTORICAL_PRICES_URL_SUFFIX;
	}

	public static void main(String args[])
	{
		System.out.println(getDownloadToSpreadsheetURL("000858.SZ"));
	}
}
