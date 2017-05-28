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
	public List<Stock> getAllStockAtAllWeekend(String ticket, int numberOfDay) {
		String hql = "From Stock where weekday(str_to_date(open_date, '%m/%d/%Y')) = :day and ticket = upper(:ticket) order by str_to_date(open_date, '%m/%d/%Y')";
		return getSession().createQuery(hql).setParameter("day", numberOfDay).setParameter("ticket", ticket).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Stock> getAllStockAtAllWeekend(String ticket, int numberOfDay, String date, int period) {
		String hql = "FROM Stock WHERE ticket = upper(:ticket) " 
					+ "and str_to_date(open_date, '%m/%d/%Y') <= str_to_date(:date, '%m/%d/%Y') "
					+ "and weekday(str_to_date(open_date, '%m/%d/%Y')) = :day "
					+ "order by str_to_date(open_date, '%m/%d/%Y') desc"; 
		
		List<Stock> result = getSession().createQuery(hql)
				.setParameter("ticket", ticket)
				.setParameter("date", date)
				.setParameter("day", numberOfDay)
				.setMaxResults(period)
				.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Stock> getAllStock(String ticket) {
		String hql = "FROM Stock WHERE ticket = upper(:ticket) order by str_to_date(open_date, '%m/%d/%Y')";
		return getSession().createQuery(hql).setParameter("ticket", ticket).list();
	}

	@Override
	@Transactional
	public List<Stock> getAllStock(String ticket, String date, int period) {
		String hql = "FROM Stock WHERE ticket = upper(:ticket) and str_to_date(open_date, '%m/%d/%Y') <= str_to_date(:date, '%m/%d/%Y') order by str_to_date(open_date, '%m/%d/%Y') desc"; 
		@SuppressWarnings("unchecked")
		List<Stock> result = getSession().createQuery(hql)
				.setParameter("ticket", ticket)
				.setParameter("date", date)
				.setMaxResults(period)
				.list();
		return result;
	}

	@SuppressWarnings("unchecked")
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
}
