package com.hstock.dao.indicator;

import java.util.List;

import com.hstock.dao.GenericDao;
import com.hstock.model.Type;
import com.hstock.model.indicator.IndicatorRSI;

public interface RSIDao extends GenericDao<IndicatorRSI>{

	/**
	 * Get list indicator RSI
	 * @param ticketName
	 * @param period
	 * @param type
	 * @return
	 */
	public List<IndicatorRSI> getListIndicatorRSI(String ticketName, int period, Type type);
	
	/**
	 * Get indicator RSI at one day
	 * @param ticketName
	 * @param period
	 * @param date
	 * @param type
	 * @return
	 */
	public IndicatorRSI getIndicatorRSIAtOneDate(String ticketName, int period, String date, Type type);
}
