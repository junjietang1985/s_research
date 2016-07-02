package com.junjie.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.junjie.dao.StockDao;
import com.junjie.model.Stock;




public class Test {

	public static void main(String[] args) {

    	ApplicationContext context = 
    		new ClassPathXmlApplicationContext("Spring-context.xml");
    	 
    	StockDao dao = (StockDao) context.getBean("stockDAO");
           	
        Stock stock = dao.getById(1);
        System.out.println(stock.getStockCode()+stock.getIsSssz300());
        stock.setStockCode(stock.getStockCode()+1);
        
        dao.save(stock);

	}

}
