package com.hstock.dao.indicator;

import java.util.List;

import com.hstock.dao.GenericDao;
import com.hstock.model.IndicatorSma;
import com.hstock.model.Type;

public interface SMADao extends GenericDao<IndicatorSma>{
	
	public List<IndicatorSma> getListIndicatorSmaByTicketNameAndPeriod(String ticketName, int period, Type type);
	
	public IndicatorSma getIndicatorSmaAtOneDate(String ticketName, int period, String date, Type type);
	
	public IndicatorSma getSMAAtOneDayByWeekly(String ticket, int period, String date);
	
}
