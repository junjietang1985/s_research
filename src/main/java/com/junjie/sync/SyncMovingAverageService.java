package com.junjie.sync;

import java.util.Date;
import java.util.EnumSet;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.junjie.dao.StockMaDao;
import com.junjie.indicator.MovingAverageType;
import com.junjie.model.StockHistory;
import com.junjie.model.StockMa;

public class SyncMovingAverageService
{
	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-context.xml");
	StockMaDao stockMaDao = (StockMaDao) context.getBean("stockMaDao");

	public void syncStockHistory(List<StockHistory> stockHistories, EnumSet<MovingAverageType> movingAverageTypes)
	{
		for (StockHistory stockHistory : stockHistories)
		{
			String stockCode = stockHistory.getStockCode();
			Date date = stockHistory.getDate();

			StockMa stockMa = stockMaDao.getByStockCodeAndDate(stockCode, date);
			// ignore the updating for existing 
			if (stockMa == null)
			{
				stockMa = new StockMa();
				stockMa.setStockCode(stockHistory.getStockCode());
				stockMa.setDate(stockHistory.getDate());
				this.setAllMaValue(stockHistories, stockHistories.indexOf(stockHistory), stockMa, movingAverageTypes);
				//TODO save
			}
			System.out.println(stockMa.toString());
		}
	}

	public void setAllMaValue(List<StockHistory> stockHistories, int indexOfComputation, StockMa stockMa,
			EnumSet<MovingAverageType> movingAverageTypes)
	{
		int count = 0;
		for (MovingAverageType movingAverageType : movingAverageTypes)
		{
			//TODO
			count++;
			if (count > 100) break;

			int days = movingAverageType.getValue();
			Double sum = 0d;
			//TODO will fail at the end of the test
			for (int i = 0; i < days; i++)
			{
				sum += stockHistories.get(indexOfComputation).getClose();
			}
			Double avg = sum / new Double(days);

			try
			{
				StockMa.class.getField(movingAverageType.getDbColName()).set(stockMa, avg);
			}
			catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e)
			{
				e.printStackTrace();
			}

		}
	}
}
