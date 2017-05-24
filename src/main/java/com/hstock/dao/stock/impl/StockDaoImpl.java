package com.hstock.dao.stock.impl;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import com.hstock.dao.AbstractGenericDao;
import com.hstock.dao.stock.StockDao;
import com.hstock.model.Stock;

public class StockDaoImpl extends AbstractGenericDao<Stock> implements StockDao{

	@Override
	@Transactional
	public Stock getStockById(Long id) {
		Session session  = getSession();
		
		return (Stock) session.get(Stock.class, id);
	}

}
