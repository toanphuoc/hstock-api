package com.hstock.dao.indicator;

import java.util.List;

import com.hstock.dao.GenericDao;
import com.hstock.model.Type;
import com.hstock.model.indicator.IndicatorBB;

public interface BBDao extends GenericDao<IndicatorBB>{
	
	/**
	 * Get list indicator BB
	 * @param ticket
	 * @param period
	 * @param sd
	 * @param type
	 * @return
	 */
	public List<IndicatorBB> getListIndicatorBB(String ticket, int period, int sd, Type type);
	
	/**
	 * Get indicator BB at one date
	 * @param ticket
	 * @param period
	 * @param sd
	 * @param type
	 * @param date
	 * @return
	 */
	public IndicatorBB getIndicatorBBAtOneDate(String ticket, int period, int sd, Type type, String date);
}
