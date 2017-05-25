package com.hstock.dao.stock;

import java.util.List;

import com.hstock.dao.GenericDao;
import com.hstock.model.Stock;

public interface StockDao extends GenericDao<Stock>{
	
	public Stock getStockById(Long id);
	
	public List<Stock> getStockByTicketAndOpenDate(String ticket, String date, int number);
	
	public List<Stock> getStockByTicket(String ticket);
	
}
