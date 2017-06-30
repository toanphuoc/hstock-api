package com.hstock.dao.indicator.impl;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.hstock.dao.AbstractGenericDao;
import com.hstock.dao.indicator.ADXDao;
import com.hstock.model.Type;
import com.hstock.model.indicator.IndicatorADX;

@Transactional
public class ADXDaoImpl extends AbstractGenericDao<IndicatorADX> implements ADXDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<IndicatorADX> getListIndicatorADX(String ticket, int period, Type type) {
		String sql = "CALL getAllADXOfStock(:ticket, :period, :type)";
		
		return getSession().createSQLQuery(sql).addEntity(IndicatorADX.class)
				.setParameter("ticket", ticket)
				.setParameter("period", period)
				.setParameter("type", type.name().toString())
				.list();
	}

	@Override
	public IndicatorADX getIndicatorADXAtOneDate(String ticket, int period, Type type, String date) {
		String sql = "CALL getADXAtOneDay(:ticket, :type, :period, :date)";
		List lst = getSession().createSQLQuery(sql).addEntity(IndicatorADX.class)
				.setParameter("ticket", ticket)
				.setParameter("period", period)
				.setParameter("date", date)
				.setParameter("type", type.name().toString()).list();
		if(lst.size() == 1){
			return (IndicatorADX) lst.get(0);
		}
		return null;
	}
	

}
