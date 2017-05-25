package com.hstock.service.stock.impl;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hstock.dao.stock.StockDao;
import com.hstock.model.Stock;
import com.hstock.service.stock.StockService;

@Service("stockService")
public class StockServiceImpl implements StockService{

	@Autowired
	private StockDao stockDao;
	
	@Override
	@Transactional
	public List<Stock> getAllStocks() {
		return stockDao.findAll();
	}

	@Override
	@Transactional
	public Stock getStockById(Long id) {
		return stockDao.getStockById(id);
	}

	@Override
	@Transactional
	public Object EMA(String ticket, String date, int number) {
		List<Stock> stocks = stockDao.getStockByTicketAndOpenDate(ticket, date, number);
		double sum = 0 ;
		for (Stock stock : stocks) {
			sum += stock.getClosePrice();
		}
		
		DecimalFormat format = new DecimalFormat("#.##");
		String value = format.format(sum / number);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ticket", ticket);
		map.put("date", date);
		map.put("period", number);
		map.put("result", Double.parseDouble(value));
		return map;
	}

	@Override
	@Transactional
	public Object SMA(String ticket, String date, int number) {
		if(date != null){
			return EMA(ticket, date, number);
		}
		return SMA(ticket, number);
	}
	
	public Object SMA(String ticket, int number){
		List<Stock> stocks = stockDao.getStockByTicket(ticket);
		return stocks;
	}
}
