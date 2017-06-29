package com.hstock.dao.indicator;

import java.util.List;
import com.hstock.dao.GenericDao;
import com.hstock.model.Type;
import com.hstock.model.indicator.IndicatorADX;

public interface ADXDao extends GenericDao<IndicatorADX>{

	/**
	 * Get list indicator ADX
	 * @param ticket
	 * @param period
	 * @param sd
	 * @param type
	 * @return
	 */
	public List<IndicatorADX> getListIndicatorADX(String ticket, int period, Type type);
	
	/**
	 * Get indicator ADX at one date
	 * @param ticket
	 * @param period
	 * @param sd
	 * @param type
	 * @param date
	 * @return
	 */
	public IndicatorADX getIndicatorADXAtOneDate(String ticket, int period, Type type, String date);
}
