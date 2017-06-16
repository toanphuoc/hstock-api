package com.hstock.service.indicator.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hstock.dao.indicator.SMADao;
import com.hstock.dao.period.PeriodDao;
import com.hstock.dao.stock.StockDao;
import com.hstock.model.Period;
import com.hstock.model.Stock;
import com.hstock.model.Type;
import com.hstock.model.indicator.IndicatorSMA;
import com.hstock.service.indicator.NumberOfDay;
import com.hstock.service.indicator.SMAService;

@Service("smaService")
public class SMAServiceImpl implements SMAService{

	@Autowired
	private StockDao stockDao;
	
	@Autowired
	private SMADao smaDao;
	
	@Autowired
	private PeriodDao periodDao;
	
	@Override
	@Transactional
	public Object SMA(String ticket, String date, int period, String type) {
		Type _type = Type.valueOf(type.toUpperCase());
		if(date != null){
			return SMA(ticket, date, period, _type);
		}
		
		int numberOfDay = _type.name().toUpperCase().equals(Type.WEEKLY.toString()) ? NumberOfDay.FRIDAY : -1;
		
		List<Stock> stocks = stockDao.getAllStock(ticket, numberOfDay);
		return SMA(stocks, ticket, period, _type);
	}
	
	/**
	 * Calculator Simple Moving Average at a time in list of stocks
	 * @param stocks
	 * @return
	 */
	public Object SMA(String ticket, String date, int period, Type type){
		
		//Get data from database
		IndicatorSMA indicatorSma = smaDao.getIndicatorSMAAtOneDate(ticket, period, date, type);
		
		
		if(indicatorSma != null)
			return indicatorSma;
		
		indicatorSma = new IndicatorSMA();
		
		int numberOfDay = type.name().toUpperCase().equals(Type.WEEKLY.toString()) ? NumberOfDay.FRIDAY : -1;
		List<Stock> stocks = stockDao.getAllStockToOneDay(ticket, date, numberOfDay, period);
		
		if(stocks.size() == 0){
			return null;
		}
		
		double sum = 0 ;
		for (Stock stock : stocks) {
			sum += stock.getClosePrice();
		}
		
		indicatorSma.setStock(stocks.get(0));
		indicatorSma.setType(type);
		indicatorSma.setValue(sum / period);
		indicatorSma.setPeriod(periodDao.getPeriodByValue(period));
		
		//int id = (int) smaDao.save(indicatorSma);
		//indicatorSma.setId(id);
		
		return indicatorSma;
	}
	
	/**
	 * Calculator Simple Moving Average all of stock
	 * @param ticket
	 * @param period
	 * @return
	 */
	@Override
	@Transactional
	public Object SMA(List<Stock> stocks, String ticket, int period, Type type){
		/**
		 * Load all of data Simple Moving Average from database 
		 */
		List<IndicatorSMA> indicatorSmas = smaDao.getListIndicatorSMA(ticket, period, type);
		
		if(indicatorSmas != null && indicatorSmas.size() == (stocks.size() - period + 1)){
			return indicatorSmas;
		}
		
		/**
		 * If data is loaded form database is empty, calculation all data and store those in database 
		 */
		
		if(period > stocks.size()){
			return null;
		}
		
		indicatorSmas = new ArrayList<IndicatorSMA>();
		
		Period periodObj = periodDao.getPeriodByValue(period);
		
		for (int i = period - 1; i < stocks.size(); i++) {
			
			IndicatorSMA indicatorSma = new IndicatorSMA();
			
			double x = 0.00;
			for(int j = 0; j < period; j++){
				x += stocks.get(i - j).getClosePrice();
			}
			x /= period;
			
			indicatorSma.setType(type);
			indicatorSma.setStock(stocks.get(i));
			indicatorSma.setValue(x);
			
			if(periodObj != null){
				indicatorSma.setPeriod(periodObj);
				int id = (int) smaDao.save(indicatorSma);
				indicatorSma.setId(id);
			}
			indicatorSmas.add(indicatorSma);
		}
		
		return indicatorSmas;
	}

}
