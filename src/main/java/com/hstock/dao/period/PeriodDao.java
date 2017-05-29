package com.hstock.dao.period;

import com.hstock.dao.GenericDao;
import com.hstock.model.Period;

public interface PeriodDao extends GenericDao<Period>{

	public Period getPeriodByValue(int value);
}
