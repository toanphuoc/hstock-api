package com.hstock.dao.indicator;

import java.util.List;

import com.hstock.dao.GenericDao;
import com.hstock.model.IndicatorRsi;
import com.hstock.model.Type;

public interface RSIDao extends GenericDao<IndicatorRsi>{

	public List<IndicatorRsi> getListIndicatorRsiByTicketNameAndPeriod(String ticketName, int period, Type type);
	
	public IndicatorRsi getIndicatorRsiAtOneDate(String ticketName, int period, String date, Type type);
}
