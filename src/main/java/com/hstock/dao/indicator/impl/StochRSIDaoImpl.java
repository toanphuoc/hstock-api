package com.hstock.dao.indicator.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hstock.dao.AbstractGenericDao;
import com.hstock.dao.indicator.StochRSIDao;
import com.hstock.model.Type;
import com.hstock.model.indicator.IndicatorStochRSI;

public class StochRSIDaoImpl extends AbstractGenericDao<IndicatorStochRSI> implements StochRSIDao{

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<IndicatorStochRSI> getListIndicatorStochRSIs(String ticket,
			int periodRsi, int periodStochRsi, int periodSk, int periodSd, Type type) {
		String sql = "CALL getAllStochRSIOfStock(:ticket, :periodRsi, :periodStochRsi, :periodSk, :periodSd, :type)";
		return getSession().createSQLQuery(sql).addEntity(IndicatorStochRSI.class)
				.setParameter("ticket", ticket)
				.setParameter("periodRsi", periodRsi)
				.setParameter("periodStochRsi", periodStochRsi)
				.setParameter("periodSk", periodSk)
				.setParameter("periodSd", periodSd)
				.setParameter("type", type.name().toString()).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public IndicatorStochRSI getIndicatorStochRSIAtOneDay(String ticket,
			int periodRsi, int periodStochRsi, int periodSk, int periodSd,
			Type type, String date) {
		String sql = "CALL getStochRSIAtOneDay(:ticket, :periodRsi, :periodStochRsi, :periodSk, :periodSd, :type, :date)";
		List<IndicatorStochRSI> list = getSession().createSQLQuery(sql).addEntity(IndicatorStochRSI.class)
				.setParameter("ticket", ticket)
				.setParameter("periodRsi", periodRsi)
				.setParameter("periodStochRsi", periodStochRsi)
				.setParameter("periodSk", periodSk)
				.setParameter("periodSd", periodSd)
				.setParameter("type", type.name().toString())
				.setParameter("date", date).list();
		if(list != null && list.size() >= 1)
			return (IndicatorStochRSI) list.get(0);
		return null;
	}
}
