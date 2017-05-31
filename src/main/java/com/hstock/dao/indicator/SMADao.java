package com.hstock.dao.indicator;

import java.util.List;

import com.hstock.dao.GenericDao;
import com.hstock.model.IndicatorSma;
import com.hstock.model.Type;

public interface SMADao extends GenericDao<IndicatorSma>{
	
	/**
	 * Get list indicator SMA daily or weekly by name and period value
	 * @param ticketName
	 * @param period
	 * @param type
	 * @return
	 */
	public List<IndicatorSma> getListIndicatorSmaByTicketNameAndPeriod(String ticketName, int period, Type type);
	
	/**
	 * Get indicator SMA  weekly at a time by name and period
	 * @param ticketName
	 * @param period
	 * @param date
	 * @param type
	 * @return
	 */
	public IndicatorSma getIndicatorSmaAtOneDate(String ticketName, int period, String date, Type type);
	
	/**
	 * Get indicator SMA daily at a time by name and period
	 * @param ticket
	 * @param period
	 * @param date
	 * @return
	 */
	public IndicatorSma getSMAAtOneDayByWeekly(String ticket, int period, String date);
	
	/**
	 * 
	 * @param ticket
	 * @param period
	 * @param date
	 * @param type
	 * @return
	 */
	public IndicatorSma getIndicatorSma(String ticket, int period, String date, Type type);
	
}
