package com.junjie.sync;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.junjie.model.StockHistory;
import com.junjie.utils.YahooFinanceUtils;

public class SyncStockFromYahooFinance
{
	private static boolean SKIP_HEAD_LINE = true;
	private static String SEPARATOR = ",";
	private static int NUM_OF_COLUMN = 7;
	private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	public void a()
	{
		String targetUrl = YahooFinanceUtils.getDownloadToSpreadsheetURL("000858.SZ");
		try
		{
			URL url = new URL(targetUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())))
			{
				String line;
				if (SKIP_HEAD_LINE)
				{
					br.readLine();
				}
				while ((line = br.readLine()) != null)
				{
					String[] cells = line.split(SEPARATOR);
					if (cells.length != NUM_OF_COLUMN)
					{
						throw new IllegalArgumentException("number of columns is not " + NUM_OF_COLUMN);
					}

					StockHistory stockHistory = toStockHistory(cells);
					System.out.println(stockHistory);
				}

			}
		}
		catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private StockHistory toStockHistory(String[] cells)
	{
		try
		{
			return StockHistory.builder().date(DATE_FORMAT.parse(cells[0])).open(Float.parseFloat(cells[1])).high(Float.parseFloat(cells[2]))
				.low(Float.parseFloat(cells[3])).close(Float.parseFloat(cells[4])).volume(Long.parseLong(cells[5]))
				.adjColse(Float.parseFloat(cells[6])).build();
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args)
	{
		new SyncStockFromYahooFinance().a();

	}

}
