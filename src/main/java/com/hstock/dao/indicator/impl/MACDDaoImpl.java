package com.hstock.dao.indicator.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hstock.dao.AbstractGenericDao;
import com.hstock.dao.indicator.MACDDao;
import com.hstock.model.IndicatorMACD;
import com.hstock.model.Type;

public class MACDDaoImpl extends AbstractGenericDao<IndicatorMACD> implements MACDDao{

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<IndicatorMACD> getListIndicatorEmaByTicketNameAndPeriod(
			String ticket, int periodX, int periodY, int periodSignal, Type type) {
		String sql = "CALL getAllMACDOfStock(:ticket, :periodX, :periodY, :periodSignal, :type)";
		
		return getSession().createSQLQuery(sql).addEntity(IndicatorMACD.class)
				.setParameter("ticket", ticket)
				.setParameter("periodX", periodX)
				.setParameter("periodY", periodY)
				.setParameter("periodSignal", periodSignal)
				.setParameter("type", type.name().toString()).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public IndicatorMACD getIndicatorMACDAtOneDate(String ticket, int periodX,
			int periodY, int periodSignal, String date, Type type) {
		String sql = "CALL getMACDAtOneDay(:ticket, :periodX, :periodY, :periodSignal, :date, :type)";
		List<IndicatorMACD> list = getSession().createSQLQuery(sql).addEntity(IndicatorMACD.class)
				.setParameter("ticket", ticket)
				.setParameter("periodX", periodX)
				.setParameter("periodY", periodY)
				.setParameter("periodSignal", periodSignal)
				.setParameter("date", date)
				.setParameter("type", type.name().toString()).list();
		if(list.size() == 1){
			return (IndicatorMACD) list.get(0);
		}
		return null;
	}

}
