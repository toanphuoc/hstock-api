package com.hstock.dao.period.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hstock.dao.AbstractGenericDao;
import com.hstock.dao.period.PeriodDao;
import com.hstock.model.Period;

public class PeriodDaoImpl extends AbstractGenericDao<Period> implements PeriodDao{

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Period getPeriodByValue(int value) {
		String hql = "FROM Period where value = :value";
		List<Period> periods = getSession().createQuery(hql).setParameter("value", value).list();
		if(periods.size() == 0){
			Period p = new Period(value);
			int id = (int) getSession().save(p);
			p.setId(id);
			return p;
		}
		
		return periods.get(0);
	}

}
