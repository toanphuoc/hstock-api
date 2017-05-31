package com.hstock.dao.indicator.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hstock.dao.AbstractGenericDao;
import com.hstock.dao.indicator.EMADao;
import com.hstock.model.IndicatorEma;
import com.hstock.model.Type;

public class EMADaoImpl extends AbstractGenericDao<IndicatorEma> implements EMADao{

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<IndicatorEma> getListIndicatorEmaByTicketNameAndPeriod(
			String ticketName, int period, Type type) {
		String hql = "CALL getAllEMAOfStock(:ticket, :period, :type)";
		return getSession().createSQLQuery(hql).addEntity(IndicatorEma.class)
				.setParameter("ticket", ticketName)
				.setParameter("period", period)
				.setParameter("type", type.name().toString()).list();
	}

	@Override
	@Transactional
	public IndicatorEma getIndicatorEmaAtOneDate(String ticketName, int period,
			String date, Type type) {
		String sql = "CALL getEMAAtOneDay(:ticket, :type, :period, :date)";
		
		List list = getSession().createSQLQuery(sql).addEntity(IndicatorEma.class)
				.setParameter("ticket", ticketName)
				.setParameter("period", period)
				.setParameter("type", type.name().toString())
				.setParameter("date", date).list();
		if(list.size() == 1){
			return (IndicatorEma) list.get(0);
		}
		return null;
	}

}
