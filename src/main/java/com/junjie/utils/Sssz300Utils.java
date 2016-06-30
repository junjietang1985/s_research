package com.junjie.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sssz300Utils
{

	/**
	 * returns a list containing all the 300 stocks in the sssz300 to initialize the db
	 */
	public static List<String> getSSSZ300Stocks()
	{
		List<String> res = new ArrayList<>();
		// this page lists all the sssz300 stocks
		String targetUrl = "http://www.goomj.com/zqts/zjzl/zjzl300.htm";
		String regexStock = "\\s(0|6)\\d{5}";
		String regexHTMLTag = "<.*?>";
		Pattern pattern = Pattern.compile(regexStock);
		try
		{
			URL url = new URL(targetUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//			int responseCode = conn.getResponseCode();
			//			System.out.println(responseCode);
			try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())))
			{
				String line;
				//				int count = 0;
				while ((line = br.readLine()) != null)
				{
					line = line.replaceAll(regexHTMLTag, "");
					Matcher matcher = pattern.matcher(line);
					if (matcher.find())
					{
						//						count++;
						String stockCode = line.substring(matcher.start() + 1, matcher.start() + 7);
						//						System.out.println(count + "." + stockCode);
						res.add(stockCode);
					}
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
		return res;
	}

	public static void main(String[] args)
	{
		getSSSZ300Stocks();
	}

}
