package com.hstock.dao.indicator.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hstock.dao.AbstractGenericDao;
import com.hstock.dao.indicator.SMADao;
import com.hstock.model.IndicatorSma;
import com.hstock.model.Type;

public class SMADaoImpl extends AbstractGenericDao<IndicatorSma> implements SMADao{

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<IndicatorSma> getListIndicatorSmaByTicketNameAndPeriod(
			String ticketName, int period, Type type) {
		String hql = "call getAllSMAOfStock(:ticket, :period, :type)";
		return getSession().createSQLQuery(hql).addEntity(IndicatorSma.class)
						.setParameter("ticket", ticketName)
						.setParameter("period", period)
						.setParameter("type", type.name().toString()).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public IndicatorSma getIndicatorSmaAtOneDate(String ticketName, int period,
			String date, Type type) {
		String hql = "CALL getSMAAtOneDay(:ticket, :period, :type, :openDate)";			
		List<IndicatorSma> indicatorSmas = getSession().createSQLQuery(hql).addEntity(IndicatorSma.class)
				.setParameter("ticket", ticketName)
				.setParameter("period", period)
				.setParameter("type", type.name().toString())
				.setParameter("openDate", date).list();
		
		if(indicatorSmas != null && indicatorSmas.size() >= 1)
			return indicatorSmas.get(0);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public IndicatorSma getSMAAtOneDayByWeekly(String ticket, int period,
			String date) {
		String hql = "CALL getSMAAtOneDayByWeekly(:ticket, :openDate, :period)";			
		List<IndicatorSma> indicatorSmas = getSession().createSQLQuery(hql).addEntity(IndicatorSma.class)
				.setParameter("ticket", ticket)
				.setParameter("period", period)
				.setParameter("openDate", date).list();
		
		if(indicatorSmas != null && indicatorSmas.size() >= 1)
			return indicatorSmas.get(0);
		return null;
	}
}
