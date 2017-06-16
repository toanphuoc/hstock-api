package com.hstock.dao.indicator;

import java.util.List;

import com.hstock.dao.GenericDao;
import com.hstock.model.Type;
import com.hstock.model.indicator.IndicatorEMA;

public interface EMADao extends GenericDao<IndicatorEMA>{

	/**
	 * Get list indicator EMA daily or weekly
	 * @param ticketName
	 * @param period
	 * @param type
	 * @return
	 */
	public List<IndicatorEMA> getListIndicatorEMA(String ticketName, int period, Type type);
	
	/**
	 * Get indicator EMA  weekly or daily type at a day
	 * @param ticketName
	 * @param period
	 * @param date
	 * @param type
	 * @return
	 */
	public IndicatorEMA getIndicatorEMAAtOneDate(String ticketName, int period, String date, Type type);
}
