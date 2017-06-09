package com.hstock.dao.indicator;

import java.util.List;

import com.hstock.dao.GenericDao;
import com.hstock.model.IndicatorMACD;
import com.hstock.model.Type;

public interface MACDDao extends GenericDao<IndicatorMACD>{

	public List<IndicatorMACD> getListIndicatorEmaByTicketNameAndPeriod(String ticket, int periodX, int periodY, int periodSignal, Type type);
	
	public IndicatorMACD getIndicatorMACDAtOneDate(String ticket, int periodX, int periodY, int periodSignal, String date, Type type);
}
