package com.hstock.dao.indicator;

import java.util.List;

import com.hstock.dao.GenericDao;
import com.hstock.model.Type;
import com.hstock.model.indicator.IndicatorSMA;

public interface SMADao extends GenericDao<IndicatorSMA>{
	
	/**
	 * Get list indicator SMA
	 * @param ticketName
	 * @param period
	 * @param type
	 * @return
	 */
	public List<IndicatorSMA> getListIndicatorSMA(String ticketName, int period, Type type);
	
	/**
	 * Get indicator SMA at one day
	 * @param ticketName
	 * @param period
	 * @param date
	 * @param type
	 * @return
	 */
	public IndicatorSMA getIndicatorSMAAtOneDate(String ticketName, int period, String date, Type type);
	
}
