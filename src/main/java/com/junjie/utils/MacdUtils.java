package com.junjie.utils;

import java.util.Date;

import com.junjie.model.Macd;

public class MacdUtils
{
	public static int EMA_FAST_X = 12;
	public static int EMA_SLOW_X = 26;
	public static int DEM_X = 9;

	public static Macd calculateMacd(String stockCode, Date date, Macd previousMacd, double close)
	{
		Macd macd = new Macd();
		macd.setStockCode(stockCode);
		macd.setDate(date);
		macd.setEmaFast(calculateEmaFast(previousMacd.getEmaFast(), close));
		macd.setEmaSlow(calculateEmaSlow(previousMacd.getEmaSlow(), close));
		macd.setDiff(calculateDiff(macd));
		macd.setDem(calculateDem(previousMacd.getDem(), macd.getDiff()));
		macd.setBar(calculateBar(macd));
		return macd;
	}

	//ema(close,12)
	public static Double calculateEmaFast(Double previous, double close)
	{
		return getEMAPreviousRatio(EMA_FAST_X) * (previous == null ? 0d : previous) + getEMACurrentRatio(EMA_FAST_X) * close;
	}

	//ema(close,26)
	public static Double calculateEmaSlow(Double previous, double close)
	{
		return getEMAPreviousRatio(EMA_SLOW_X) * (previous == null ? 0d : previous) + getEMACurrentRatio(EMA_SLOW_X) * close;
	}

	// DIFF = ema12 - ema26
	public static Double calculateDiff(Macd macd)
	{
		return macd.getEmaFast() - macd.getEmaSlow();
	}

	//ema(diff,9)
	public static Double calculateDem(Double previous, double diff)
	{
		return getEMAPreviousRatio(DEM_X) * (previous == null ? 0d : previous) + getEMACurrentRatio(DEM_X) * diff;
	}

	// BAR = DIFF - DEM
	public static Double calculateBar(Macd macd)
	{
		return macd.getDiff() - macd.getDem();
	}

	// (x - 1) / (x + 1)
	private static double getEMAPreviousRatio(int x)
	{
		return (double) (x - 1) / (double) (x + 1);
	}
	// 2 / (x + 1)
	private static double getEMACurrentRatio(int x)
	{
		return (double) 2 / (double) (x + 1);
	}
}
