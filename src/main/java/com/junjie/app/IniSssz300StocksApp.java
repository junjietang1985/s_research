package com.junjie.app;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.junjie.dao.StockDao;
import com.junjie.model.Stock;
import com.junjie.utils.Sssz300Utils;

public class IniSssz300StocksApp {

	Logger log = Logger.getLogger(this.getClass());

	private static Stock createSssz300Stock(String stockCode) {
		Stock stock = new Stock();
		stock.setStockCode(stockCode);
		stock.setIsSssz300(true);
		return stock;
	}

	private void work() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Spring-context.xml");
		StockDao dao = (StockDao) context.getBean("stockDao");

		List<String> list = Sssz300Utils.getSSSZ300Stocks();
		for (String stockCode : list) {
			if (dao.getByStockCode(stockCode) != null) {
				log.info(String.format("the stock %s has been found in the db",
						stockCode));
			} else {
				dao.save(createSssz300Stock(stockCode));
			}
		}

	}

	public static void main(String[] args) {
		new IniSssz300StocksApp().work();
	}

}
