package com.hstock.dao.stock;

import java.util.List;

import com.hstock.dao.GenericDao;
import com.hstock.model.Stock;

public interface StockDao extends GenericDao<Stock>{
	
//	public List<Stock> getAllStock(String ticket, St)
	
	/**
	 * Get all stocks each Friday of ticket 'ticket'
	 * @param ticket
	 * @param numberOfDay
	 * @return
	 */
	public List<Stock> getAllStock(String ticket, int numberOfDay);
	
	/**
	 * Get all stock
	 * @param ticket
	 * @param openDate
	 * @param numberOfDay
	 * @param period
	 * @return
	 */
	public List<Stock> getAllStockToOneDay(String ticket, String openDate, int numberOfDay, int period);
}
