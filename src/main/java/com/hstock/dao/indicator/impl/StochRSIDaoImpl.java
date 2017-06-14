package com.hstock.dao.indicator.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hstock.dao.AbstractGenericDao;
import com.hstock.dao.indicator.StochRSIDao;
import com.hstock.model.IndicatorStochRSI;
import com.hstock.model.Type;

public class StochRSIDaoImpl extends AbstractGenericDao<IndicatorStochRSI> implements StochRSIDao{

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<IndicatorStochRSI> getListIndicatorStochRSIs(String ticket,
			int periodRsi, int periodStochRsi, Type type) {
		String sql = "CALL getAllStochRSIOfStock(:ticket, :periodRsi, :periodStochRsi, :type)";
		return getSession().createSQLQuery(sql).addEntity(IndicatorStochRSI.class)
				.setParameter("ticket", ticket)
				.setParameter("periodRsi", periodRsi)
				.setParameter("periodStochRsi", periodStochRsi)
				.setParameter("type", type.name().toString()).list();
	}
}
