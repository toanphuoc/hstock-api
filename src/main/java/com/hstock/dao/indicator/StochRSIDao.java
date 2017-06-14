package com.hstock.dao.indicator;

import java.util.List;

import com.hstock.dao.GenericDao;
import com.hstock.model.IndicatorStochRSI;
import com.hstock.model.Type;

public interface StochRSIDao extends GenericDao<IndicatorStochRSI>{

	public List<IndicatorStochRSI> getListIndicatorStochRSIs(String ticket, int periodRsi, int periodStochRsi, Type type);
}
