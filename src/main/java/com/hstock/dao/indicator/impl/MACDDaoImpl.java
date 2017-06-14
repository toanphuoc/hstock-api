package com.hstock.dao.indicator.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hstock.dao.AbstractGenericDao;
import com.hstock.dao.indicator.MACDDao;
import com.hstock.model.IndicatorMacd;
import com.hstock.model.Type;

public class MACDDaoImpl extends AbstractGenericDao<IndicatorMacd> implements MACDDao{

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<IndicatorMacd> getListIndicatorEmaByTicketNameAndPeriod(
			String ticket, int periodX, int periodY, int periodSignal, Type type) {
		String sql = "CALL getAllMACDOfStock(:ticket, :periodX, :periodY, :periodSignal, :type)";
		
		return getSession().createSQLQuery(sql).addEntity(IndicatorMacd.class)
				.setParameter("ticket", ticket)
				.setParameter("periodX", periodX)
				.setParameter("periodY", periodY)
				.setParameter("periodSignal", periodSignal)
				.setParameter("type", type.name().toString()).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public IndicatorMacd getIndicatorMACDAtOneDate(String ticket, int periodX,
			int periodY, int periodSignal, String date, Type type) {
		String sql = "CALL getMACDAtOneDay(:ticket, :periodX, :periodY, :periodSignal, :date, :type)";
		List<IndicatorMacd> list = getSession().createSQLQuery(sql).addEntity(IndicatorMacd.class)
				.setParameter("ticket", ticket)
				.setParameter("periodX", periodX)
				.setParameter("periodY", periodY)
				.setParameter("periodSignal", periodSignal)
				.setParameter("date", date)
				.setParameter("type", type.name().toString()).list();
		if(list.size() == 1){
			return (IndicatorMacd) list.get(0);
		}
		return null;
	}

}
