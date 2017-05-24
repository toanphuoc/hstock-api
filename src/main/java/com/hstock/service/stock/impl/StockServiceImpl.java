package com.hstock.service.stock.impl;

import java.util.List;

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
	public Stock getStockById(long id) {
		return stockDao.findById(id);
	}

}
