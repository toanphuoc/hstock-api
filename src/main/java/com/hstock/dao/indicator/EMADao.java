package com.hstock.dao.indicator;

import java.util.List;

import com.hstock.dao.GenericDao;
import com.hstock.model.IndicatorEma;
import com.hstock.model.Type;

public interface EMADao extends GenericDao<IndicatorEma>{

	/**
	 * Get list indicator EMA daily or weekly by name and period value
	 * @param ticketName
	 * @param period
	 * @param type
	 * @return
	 */
	public List<IndicatorEma> getListIndicatorEmaByTicketNameAndPeriod(String ticketName, int period, Type type);
	
	/**
	 * Get indicator EMA  weekly at a time by name and period
	 * @param ticketName
	 * @param period
	 * @param date
	 * @param type
	 * @return
	 */
	public IndicatorEma getIndicatorEmaAtOneDate(String ticketName, int period, String date, Type type);
}
