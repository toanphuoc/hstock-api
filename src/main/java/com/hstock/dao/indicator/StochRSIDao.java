package com.hstock.dao.indicator;

import java.util.List;

import com.hstock.dao.GenericDao;
import com.hstock.model.Type;
import com.hstock.model.indicator.IndicatorStochRSI;

public interface StochRSIDao extends GenericDao<IndicatorStochRSI>{

	/**
	 * Get list indicator StochRSI
	 * @param ticket
	 * @param periodRsi
	 * @param periodStochRsi
	 * @param type
	 * @return
	 */
	public List<IndicatorStochRSI> getListIndicatorStochRSIs(String ticket, int periodRsi, int periodStochRsi, int periodSk, int periodSd, Type type);
	
	/**
	 * Get indicator Stoch RSI
	 * @param ticket
	 * @param periodRsi
	 * @param periodStochRsi
	 * @param periodSk
	 * @param periodSd
	 * @param type
	 * @param date
	 * @return
	 */
	public IndicatorStochRSI getIndicatorStochRSIAtOneDay(String ticket, int periodRsi, int periodStochRsi, int periodSk, int periodSd, Type type, String date);
}
