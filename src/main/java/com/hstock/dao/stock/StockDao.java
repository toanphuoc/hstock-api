package com.hstock.dao.stock;

import com.hstock.dao.GenericDao;
import com.hstock.model.Stock;

public interface StockDao extends GenericDao<Stock>{
	
	public Stock getStockById(Long id);
}
