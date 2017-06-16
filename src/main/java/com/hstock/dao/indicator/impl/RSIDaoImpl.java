package com.hstock.dao.indicator.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hstock.dao.AbstractGenericDao;
import com.hstock.dao.indicator.RSIDao;
import com.hstock.model.Type;
import com.hstock.model.indicator.IndicatorRSI;

public class RSIDaoImpl extends AbstractGenericDao<IndicatorRSI> implements RSIDao{

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<IndicatorRSI> getListIndicatorRSI(String ticketName, int period, Type type) {
		String hql = "CALL getAllRSIOfStock(:ticket, :period, :type)";
		return getSession().createSQLQuery(hql).addEntity(IndicatorRSI.class)
				.setParameter("ticket", ticketName)
				.setParameter("period", period)
				.setParameter("type", type.name().toString()).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public IndicatorRSI getIndicatorRSIAtOneDate(String ticketName, int period, String date, Type type) {
		String sql = "CALL getRSIAtOneDay(:ticket, :type, :period, :date)";
		
		List<IndicatorRSI> list = getSession().createSQLQuery(sql).addEntity(IndicatorRSI.class)
				.setParameter("ticket", ticketName)
				.setParameter("period", period)
				.setParameter("type", type.name().toString())
				.setParameter("date", date).list();
		if(list.size() == 1){
			return (IndicatorRSI) list.get(0);
		}
		return null;
	}

}
