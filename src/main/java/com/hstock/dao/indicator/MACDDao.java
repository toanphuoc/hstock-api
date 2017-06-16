package com.hstock.dao.indicator;

import java.util.List;

import com.hstock.dao.GenericDao;
import com.hstock.model.Type;
import com.hstock.model.indicator.IndicatorMACD;

public interface MACDDao extends GenericDao<IndicatorMACD>{

	/**
	 * Get list indicator MACD daily or weekly
	 * @param ticket
	 * @param periodX
	 * @param periodY
	 * @param periodSignal
	 * @param type
	 * @return
	 */
	public List<IndicatorMACD> getListIndicatorMACD(String ticket, int periodX, int periodY, int periodSignal, Type type);
	
	/**
	 * Get Indicator MACD at one day
	 * @param ticket
	 * @param periodX
	 * @param periodY
	 * @param periodSignal
	 * @param date
	 * @param type
	 * @return
	 */
	public IndicatorMACD getIndicatorMACDAtOneDate(String ticket, int periodX, int periodY, int periodSignal, String date, Type type);
}
