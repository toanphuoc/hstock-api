package com.hstock.dao.indicator.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hstock.dao.AbstractGenericDao;
import com.hstock.dao.indicator.BBDao;
import com.hstock.model.Type;
import com.hstock.model.indicator.IndicatorBB;

public class BBDaoImpl extends AbstractGenericDao<IndicatorBB> implements BBDao{

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<IndicatorBB> getListIndicatorBB(String ticket, int period,
			int sd, Type type) {
		String sql = "CALL getAllBBOfStock(:ticket, :period, :sd, :type)";
		return getSession().createSQLQuery(sql).addEntity(IndicatorBB.class)
				.setParameter("ticket", ticket)
				.setParameter("period", period)
				.setParameter("sd", sd)
				.setParameter("type", type.name().toString()).list();
	}

	@Override
	@Transactional
	public IndicatorBB getIndicatorBBAtOneDate(String ticket, int period,
			int sd, Type type, String date) {
		String sql = "CALL getBBAtOneDay(:ticket, :period, :type, :date, :sd)";
		List lst = getSession().createSQLQuery(sql).addEntity(IndicatorBB.class)
				.setParameter("ticket", ticket)
				.setParameter("period", period)
				.setParameter("sd", sd)
				.setParameter("date", date)
				.setParameter("type", type.name().toString()).list();
		if(!lst.isEmpty())
			return (IndicatorBB) lst.get(0);
		return null;
	}

}
