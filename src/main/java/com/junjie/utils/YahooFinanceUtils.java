package com.junjie.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class YahooFinanceUtils
{
	public static final String FULL_HISTORICAL_PRICES_URL_PREFIX = "http://finance.yahoo.com/quote/";

	public static final String FULL_HISTORICAL_PRICES_URL_SUFFIX = "/history?p=";

	// 2000/Jan/4-2016/Aug/12
	//http://chart.finance.yahoo.com/table.csv?s=601600.SS&a=0&b=4&c=2000&d=7&e=12&f=2016&g=d&ignore=.csv

	public static final String PARTIAL_HISTORICAL_PRICES_URL_FORMAT = "http://chart.finance.yahoo.com/table.csv?s=%s&a=%s&b=%s&c=%s&d=%s&e=%s&f=%s&g=d&ignore=.csv";

	private static final Log LOGGER = LogFactory.getLog(YahooFinanceUtils.class);

	//TODO
	public static String getDownloadPartialHistoricalPricesUrl(String stockCode)
	{
		return null;
	}
	
	//TODO
	public static String getDownloadAllHistoricalPricesUrl(String stockCode){
		return null;
	}

	/**
	 * valid url to get the full historical price url <br>
	 * not used in the code e.g.<br>
	 * http://finance.yahoo.com/quote/601600.SS/history?p=601600.SS
	 */

	//	public static String getFullHistoricalPricesURL(String stockCode)
	//	{
	//		String res = FULL_HISTORICAL_PRICES_URL_PREFIX + stockCode + FULL_HISTORICAL_PRICES_URL_SUFFIX + stockCode;
	//		LOGGER.info("Full Historical Prices URL : " + res);
	//		return res;
	//	}

	/**
	 * returns the spread sheet url for downloading the full historical data of the stock <br>
	 * e.g.<br>
	 * http://real-chart.finance.yahoo.com/table.csv?s=601600.SS&amp;d=6&amp;e=11&amp;f=2016&amp;g=d&amp;a=0&amp;b=4&amp;c=2000&amp;ignore=.csv
	 */
	//	public static String getDownloadToSpreadsheetURLForFullHistory(String stockCode)
	//	{
	//		String targetUrl = getFullHistoricalPricesURL(stockCode);
	//		try
	//		{
	//			URL url = new URL(targetUrl);
	//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	//			int responseCode = conn.getResponseCode();
	//			System.out.println(responseCode);
	//			try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())))
	//			{
	//				String line;
	//				while ((line = br.readLine()) != null)
	//				{
	//					if (!line.contains("Download to Spreadsheet"))
	//					{
	//						continue;
	//					}
	//					String prefix = "[\\s\\S]*<a href=\"http:\\/\\/real-chart.finance.yahoo.com\\/table.csv";
	//					String suffix = "\"+[\\s\\S]*";
	//					String res = DOWNLOAD_TO_SPREADSHEET_URL_PREFIX + line.replaceAll(prefix, "").replaceAll(suffix, "");
	//					LOGGER.info("Download to Spread Sheet URL : " + res);
	//					return res;
	//				}
	//			}
	//
	//		}
	//		catch (MalformedURLException e)
	//		{
	//			e.printStackTrace();
	//		}
	//		catch (IOException e)
	//		{
	//			e.printStackTrace();
	//		}
	//		return null;
	//	}

	public static void main(String args[])
	{
		System.out.println(getDownloadAllHistoricalPricesUrl("000858.SZ"));
	}
}
