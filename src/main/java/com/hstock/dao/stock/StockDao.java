package com.hstock.dao.stock;

import java.util.List;

import com.hstock.dao.GenericDao;
import com.hstock.model.Stock;

public interface StockDao extends GenericDao<Stock>{
	
	/**
	 * Get Stock by ID
	 * @param id
	 * @return
	 */
	public Stock getStockById(Long id);
	
	/**
	 * Get Stock by ticket, open_date and limit
	 * @param ticket
	 * @param date
	 * @param number
	 * @return
	 */
	public List<Stock> getStockByTicketAndOpenDate(String ticket, String date, int number);
	
	/**
	 * Get Stock by ticket
	 * @param ticket
	 * @return
	 */
	public List<Stock> getStockByTicket(String ticket);
	
}
