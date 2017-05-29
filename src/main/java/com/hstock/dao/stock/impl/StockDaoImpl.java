package com.hstock.dao.stock.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hstock.dao.AbstractGenericDao;
import com.hstock.dao.stock.StockDao;
import com.hstock.model.Stock;

public class StockDaoImpl extends AbstractGenericDao<Stock> implements StockDao{

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Stock> getAllStock(String ticket, int numberOfDay) {
		String hql = "CALL getAllStockByTicketName(:ticket, :day)";
		return getSession().createSQLQuery(hql).addEntity(Stock.class).setParameter("day", numberOfDay).setParameter("ticket", ticket).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Stock> getAllStockToOneDay(String ticket, String openDate,
			int numberOfDay, int period) {
		String hql = "CALL getStockAllStockByTicketAndDate(:ticket, :openDate, :numberOfDate, :limit)";
		return getSession().createSQLQuery(hql).addEntity(Stock.class)
					.setParameter("ticket", ticket)
					.setParameter("openDate", openDate)
					.setParameter("numberOfDate", numberOfDay)
					.setParameter("limit", period).list();
	}
}
