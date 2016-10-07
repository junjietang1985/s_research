package com.junjie.service.sync;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.junjie.model.StockHistory;
import com.junjie.net.HttpURLConnectionUtils;

public class SyncStockFromYahooFinanceService
{
	private static boolean SKIP_HEAD_LINE = true;
	private static String SEPARATOR = ",";
	private static int NUM_OF_COLUMN = 7;
	private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	// 000858.SZ
	public List<StockHistory> getStockHistory(String stockCode, String targetUrl)
	{
		List<StockHistory> res = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(HttpURLConnectionUtils.getInputStream(targetUrl))))
		{
			String line;
			if (SKIP_HEAD_LINE)
			{
				br.readLine();
			}
			while ((line = br.readLine()) != null)
			{
				String[] columns = line.split(SEPARATOR);
				if (columns.length != NUM_OF_COLUMN)
				{
					throw new IllegalArgumentException("number of columns is not " + NUM_OF_COLUMN);
				}

				StockHistory stockHistory = toStockHistory(stockCode, columns);
				res.add(stockHistory);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return res;

	}

	private StockHistory toStockHistory(String stockCode, String[] columns)
	{
		try
		{
			return StockHistory.builder().stockCode(stockCode).date(DATE_FORMAT.parse(columns[0])).open(Float.parseFloat(columns[1]))
				.high(Float.parseFloat(columns[2])).low(Float.parseFloat(columns[3])).close(Float.parseFloat(columns[4]))
				.volume(Long.parseLong(columns[5])).adjClose(Float.parseFloat(columns[6])).build();
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
//		System.out.println(new SyncStockFromYahooFinanceService().getStockHistory("000858.SZ").size());
	}

}
