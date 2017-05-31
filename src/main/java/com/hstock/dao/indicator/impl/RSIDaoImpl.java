package com.hstock.dao.indicator.impl;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.hstock.dao.AbstractGenericDao;
import com.hstock.dao.indicator.RSIDao;
import com.hstock.model.IndicatorRsi;
import com.hstock.model.Type;

public class RSIDaoImpl extends AbstractGenericDao<IndicatorRsi> implements RSIDao{

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<IndicatorRsi> getListIndicatorRsiByTicketNameAndPeriod(
			String ticketName, int period, Type type) {
		String hql = "CALL getAllRSIOfStock(:ticket, :period, :type)";
		return getSession().createSQLQuery(hql).addEntity(IndicatorRsi.class)
				.setParameter("ticket", ticketName)
				.setParameter("period", period)
				.setParameter("type", type.name().toString()).list();
	}

	@Override
	@Transactional
	public IndicatorRsi getIndicatorRsiAtOneDate(String ticketName, int period,
			String date, Type type) {
		String sql = "CALL getRSIAtOneDay(:ticket, :type, :period, :date)";
		
		List list = getSession().createSQLQuery(sql).addEntity(IndicatorRsi.class)
				.setParameter("ticket", ticketName)
				.setParameter("period", period)
				.setParameter("type", type.name().toString())
				.setParameter("date", date).list();
		if(list.size() == 1){
			return (IndicatorRsi) list.get(0);
		}
		return null;
	}

}
