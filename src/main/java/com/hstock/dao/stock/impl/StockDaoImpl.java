package com.hstock.dao.stock.impl;

import java.util.List;

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

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Stock> getStockByTicketAndOpenDate(String ticket, String date,
			int number) {
		
		String hql = "FROM Stock WHERE ticket = upper(:ticket) and str_to_date(open_date, '%m/%d/%Y') <= str_to_date(:date, '%m/%d/%Y') order by str_to_date(open_date, '%m/%d/%Y') desc"; 
		List<Stock> result = getSession().createQuery(hql)
				.setParameter("ticket", ticket)
				.setParameter("date", date)
				.setMaxResults(number)
				.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Stock> getStockByTicket(String ticket) {
		String hql = "FROM Stock WHERE ticket = upper(:ticket) order by str_to_date(open_date, '%m/%d/%Y')";
		return getSession().createQuery(hql).setParameter("ticket", ticket).list();
	}

}
