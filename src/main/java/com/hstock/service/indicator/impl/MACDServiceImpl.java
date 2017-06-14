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
import com.hstock.model.IndicatorMacd;
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
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Object MACD(String ticket, String date, int periodX,
			int periodY, int periodSignal, String type) {
		Type _type = Type.valueOf(type.toUpperCase());
		int numberOfDay = type.toUpperCase().equals(Type.WEEKLY.toString()) ? NumberOfDay.FRIDAY : -1;
		
		if(date != null){
			IndicatorMacd indicatorMACD = macdDao.getIndicatorMACDAtOneDate(ticket, periodX, periodY, periodSignal, date, _type);
			if(indicatorMACD != null){
				return indicatorMACD;
			}
			List<Stock> stocks = stockDao.getAllStockToDate(ticket, date, numberOfDay);

			List<IndicatorMacd> indicatorMACDs =  (List<IndicatorMacd>) MACD(stocks, ticket, periodX, periodY, periodSignal, _type, false);
			return indicatorMACDs.get(indicatorMACDs.size() - 1);
		}
		
		List<Stock> stocks = stockDao.getAllStock(ticket, numberOfDay);
		
		List<IndicatorMacd> indicatorMACDs = macdDao.getListIndicatorEmaByTicketNameAndPeriod(ticket, periodX, periodY, periodSignal, _type);
		
		if(indicatorMACDs.size() == (stocks.size() - periodY + 1)){
			return indicatorMACDs;
		}
		
		return MACD(stocks, ticket, periodX, periodY, periodSignal, _type, true);
	}
	
	@SuppressWarnings("unchecked")
	public Object MACD(List<Stock> stocks, String ticket, int periodX, int periodY, int periodSignal, Type type, boolean isSave){
		List<IndicatorMacd> indicatorMACDs = new ArrayList<IndicatorMacd>();
		
		Period periodXObj = periodDao.getPeriodByValue(periodX);
		Period periodYObj = periodDao.getPeriodByValue(periodY);
		Period periodSignalObj = periodDao.getPeriodByValue(periodSignal);
		
		/**
		 * Calculator x day EMA of ticket
		 */
		List<IndicatorEma> emaX = (List<IndicatorEma>) emaService.EMA(stocks,ticket, periodX, type);
		
		/**
		 * Calculator y day EMA of ticket
		 */
		List<IndicatorEma> emaY = (List<IndicatorEma>) emaService.EMA(stocks, ticket, periodY, type);
		
		/**
		 * Calculator MACD, Signal and MACD Histogram
		 */
		
		int j = 0;
		double firstSignalValue = 0.00;
		double smoothing = (double) 2 / (periodSignal + 1);
		for (int i = periodY - periodX; i < emaX.size() && j < emaY.size(); i++) {
			IndicatorMacd indicatorMACD = new IndicatorMacd();
			double macd = emaX.get(i).getValue() - emaY.get(j).getValue();
			
			//Calculator signal
			if(j < periodSignal - 1){
				firstSignalValue += macd;
			}else if(j == periodSignal - 1){
				firstSignalValue += macd;
				firstSignalValue /= periodSignal;
				indicatorMACD.setSignal(firstSignalValue);
				indicatorMACD.setMACDHistogram(macd - firstSignalValue);
			}
			if(j > periodSignal - 1){
				double previous = (double) indicatorMACDs.get(j - 1).getSignal();
				double value = smoothing * (macd - previous) + previous;
				indicatorMACD.setSignal(value);
				
				indicatorMACD.setMACDHistogram(macd - value);
			}
			
			
			indicatorMACD.setMACD(macd);
			indicatorMACD.setStock(stocks.get(i + periodX - 1));
			indicatorMACD.setType(type);
			
			if(periodXObj != null && periodYObj != null && periodSignalObj != null) {
				indicatorMACD.setPeriodX(periodXObj);
				indicatorMACD.setPeriodSignal(periodSignalObj);
				indicatorMACD.setPeriodY(periodYObj);
				
				if(isSave){
					int id = (int) macdDao.save(indicatorMACD);
					indicatorMACD.setId(id);
				}
				
			}
			
			indicatorMACDs.add(indicatorMACD);
			j++;
		}
		
		//Calculator Signal and MACD History
		return indicatorMACDs;
	}
}
