package com.hstock.dao.stock;

import java.util.List;

import com.hstock.dao.GenericDao;
import com.hstock.model.Stock;

public interface StockDao extends GenericDao<Stock>{
	
	/**
	 * Get all stocks each Friday of ticket 'ticket'
	 * @param ticket
	 * @param numberOfDay
	 * @return
	 */
	public List<Stock> getAllStockAtAllWeekend(String ticket, int numberOfDay);
	
	/**
	 * Get all stock each Friday + to day 'date' + limit 'period' of ticket 'ticket'
	 * @param ticket
	 * @param numberOfDay
	 * @param date
	 * @param period
	 * @return
	 */
	public List<Stock> getAllStockAtAllWeekend(String ticket, int numberOfDay, String date, int period);
	
	/**
	 * Get all Stock of ticket 'ticket'
	 * @param ticket
	 * @return
	 */
	public List<Stock> getAllStock(String ticket);
	
	/**
	 * Get all Stock tp day 'date' + limitation 'period' of ticket 'ticket'
	 * @param ticket
	 * @param date
	 * @param period
	 * @return
	 */
	public List<Stock> getAllStock(String ticket, String date, int period);
}
