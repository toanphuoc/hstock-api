package com.hstock.dao.indicator.impl;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.hstock.dao.AbstractGenericDao;
import com.hstock.dao.indicator.EMADao;
import com.hstock.model.Type;
import com.hstock.model.indicator.IndicatorEMA;

public class EMADaoImpl extends AbstractGenericDao<IndicatorEMA> implements EMADao{

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<IndicatorEMA> getListIndicatorEMA (String ticketName, int period, Type type) {
		String hql = "CALL getAllEMAOfStock(:ticket, :period, :type)";
		return getSession().createSQLQuery(hql).addEntity(IndicatorEMA.class)
				.setParameter("ticket", ticketName)
				.setParameter("period", period)
				.setParameter("type", type.name().toString()).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public IndicatorEMA getIndicatorEMAAtOneDate(String ticketName, int period,
			String date, Type type) {
		String sql = "CALL getEMAAtOneDay(:ticket, :type, :period, :date)";
		
		List<IndicatorEMA> list = getSession().createSQLQuery(sql).addEntity(IndicatorEMA.class)
				.setParameter("ticket", ticketName)
				.setParameter("period", period)
				.setParameter("type", type.name().toString())
				.setParameter("date", date).list();
		if(list.size() == 1){
			return (IndicatorEMA) list.get(0);
		}
		return null;
	}

}
