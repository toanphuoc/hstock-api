package com.hstock.service.stock;

import java.util.List;

import com.hstock.model.Stock;

public interface StockService {

	public List<Stock> getAllStocks();
	
	public Stock getStockById(Long id);
	
	public Object EMA(String ticket, String date, int period);
	
	public Object SMA(String ticket, String date, int period);
	
	public Object RSI(String ticket, int period);
}
