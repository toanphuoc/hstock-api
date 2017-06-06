package com.hstock.service.indicator.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hstock.dao.indicator.MACDDao;
import com.hstock.dao.period.PeriodDao;
import com.hstock.dao.stock.StockDao;
import com.hstock.model.IndicatorEma;
import com.hstock.model.IndicatorMACD;
import com.hstock.model.Period;
import com.hstock.model.Stock;
import com.hstock.model.Type;
import com.hstock.service.indicator.EMAService;
import com.hstock.service.indicator.MACDService;
import com.hstock.service.indicator.NumberOfDay;

@Service("macdService")
public class MACDServiceImpl implements MACDService{

	@Autowired
	private StockDao stockDao;
	
	@Autowired
	private PeriodDao periodDao;
	
	@Autowired
	private MACDDao macdDao;
	
	@Autowired
	private EMAService emaService;
	
	@Override
	@Transactional
	public Object MACD(String ticket, String date, int periodX,
			int periodY, int periodSignal, String type) {
		Type _type = Type.valueOf(type.toUpperCase());
		if(date != null){
			
		}
		return MACD(ticket, periodX, periodY, periodSignal, _type);
	}
	
	@SuppressWarnings("unchecked")
	public Object MACD(String ticket, int periodX, int periodY, int periodSignal, Type type){
		int numberOfDay = type.name().toUpperCase().equals(Type.WEEKLY.toString()) ? NumberOfDay.FRIDAY : -1;
		List<Stock> stocks = stockDao.getAllStock(ticket, numberOfDay);
		
		
		List<IndicatorMACD> indicatorMACDs = new ArrayList<IndicatorMACD>();
		
		Period periodXObj = periodDao.getPeriodByValue(periodX);
		Period periodYObj = periodDao.getPeriodByValue(periodY);
		
		/**
		 * Calculator x day EMA of ticket
		 */
		List<IndicatorEma> emaX = (List<IndicatorEma>) emaService.EMA(stocks,ticket, periodX, type);
		
		/**
		 * Calculator y day EMA of ticket
		 */
		List<IndicatorEma> emaY = (List<IndicatorEma>) emaService.EMA(stocks, ticket, periodY, type);
		
		/**
		 * Calculator MACD
		 */
		
		int j = 0;
		for (int i = periodY - periodX; i < emaX.size() && j < emaY.size(); i++) {
			IndicatorMACD indicatorMACD = new IndicatorMACD();
			double macd = emaX.get(i).getValue() - emaY.get(j).getValue();
			
			indicatorMACD.setMACD(macd);
			indicatorMACD.setStock(stocks.get(i + periodX - 1));
			indicatorMACD.setType(type);
			if(periodXObj != null && periodYObj != null) {
				indicatorMACD.setPeriodX(periodXObj);
				indicatorMACD.setPeriodY(periodYObj);
				int id = (int) macdDao.save(indicatorMACD);
				indicatorMACD.setId(id);
			}
			
			indicatorMACDs.add(indicatorMACD);
			j++;
		}
		
		//Calculator Signal and MACD History
		return indicatorMACDs;
	}
}
