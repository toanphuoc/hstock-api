package com.hstock.service.stock;

import java.util.List;

import com.hstock.model.Stock;

public interface StockService {

	public List<Stock> getAllStocks();
	
	public Stock getStockById(long id);
}
