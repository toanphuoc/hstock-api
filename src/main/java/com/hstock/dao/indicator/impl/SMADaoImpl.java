package com.hstock.dao.indicator.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hstock.dao.AbstractGenericDao;
import com.hstock.dao.indicator.SMADao;
import com.hstock.model.Type;
import com.hstock.model.indicator.IndicatorSMA;

public class SMADaoImpl extends AbstractGenericDao<IndicatorSMA> implements SMADao{

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<IndicatorSMA> getListIndicatorSMA(
			String ticketName, int period, Type type) {
		String hql = "call getAllSMAOfStock(:ticket, :period, :type)";
		return getSession().createSQLQuery(hql).addEntity(IndicatorSMA.class)
						.setParameter("ticket", ticketName)
						.setParameter("period", period)
						.setParameter("type", type.name().toString()).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public IndicatorSMA getIndicatorSMAAtOneDate(String ticketName, int period,
			String date, Type type) {
		String hql = "CALL getSMAAtOneDay(:ticket, :period, :type, :openDate)";			
		List<IndicatorSMA> indicatorSmas = getSession().createSQLQuery(hql).addEntity(IndicatorSMA.class)
				.setParameter("ticket", ticketName)
				.setParameter("period", period)
				.setParameter("type", type.name().toString())
				.setParameter("openDate", date).list();
		
		if(indicatorSmas != null && indicatorSmas.size() >= 1)
			return indicatorSmas.get(0);
		return null;
	}
}
