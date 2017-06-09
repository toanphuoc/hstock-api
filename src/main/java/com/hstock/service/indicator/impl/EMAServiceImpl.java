package com.hstock.service.indicator.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hstock.dao.indicator.EMADao;
import com.hstock.dao.period.PeriodDao;
import com.hstock.dao.stock.StockDao;
import com.hstock.model.IndicatorEma;
import com.hstock.model.Period;
import com.hstock.model.Stock;
import com.hstock.model.Type;
import com.hstock.service.indicator.EMAService;
import com.hstock.service.indicator.NumberOfDay;

@Service("amaService")
public class EMAServiceImpl implements EMAService{

	@Autowired
	private StockDao stockDao;
	
	@Autowired
	private EMADao emaDao;
	
	@Autowired
	private PeriodDao periodDao;
	
	@Override
	@Transactional
	public Object EMA(String ticket, String date, int period, String type) {
		Type _type = Type.valueOf(type.toUpperCase());
		if(date != null){
			return EMA(ticket, date, period, _type);
		}
		int numberOfDay = _type.name().toUpperCase().equals(Type.WEEKLY.toString()) ? NumberOfDay.FRIDAY : -1;
		
		List<Stock> stocks = stockDao.getAllStock(ticket, numberOfDay);
		return EMA(stocks, ticket, period, _type);
	}

	@Override
	@Transactional
	public Object EMA(List<Stock> stocks, String ticket, int period, Type type) {
		
		List<IndicatorEma> indicatorEmas = emaDao.getListIndicatorEmaByTicketNameAndPeriod(ticket, period, type);
		
		//Load from database
		if(indicatorEmas != null && indicatorEmas.size() == (stocks.size() - period + 1)){
			return indicatorEmas;
		}
		
		if(period > stocks.size()){
			return null;
		}
		
		Period periodObj = periodDao.getPeriodByValue(period);
		
		indicatorEmas = new ArrayList<IndicatorEma>();
		
		//Calculator of first item
		IndicatorEma firstItem = new IndicatorEma();
		double ema = 0.00;
		for (int i = 0; i < period; i++) {
			ema += stocks.get(i).getClosePrice();
		}
		
		ema /= period;
		
		firstItem.setStock(stocks.get(period - 1));
		firstItem.setType(type);
		firstItem.setValue(ema);
		if(periodObj != null){
			firstItem.setPeriod(periodObj);
			int firstItemId = (int) emaDao.save(firstItem);
			firstItem.setId(firstItemId);
		}
		
		
		indicatorEmas.add(firstItem);
		
		int k = 1;
		double smoothing = (double) 2 / (period + 1);
		for (int i = period; i < stocks.size(); i++) {
			IndicatorEma indicatorEma = new IndicatorEma();
			double previous = (double) indicatorEmas.get(k - 1).getValue();
			double value = smoothing * (stocks.get(i).getClosePrice() - previous) + previous;
			
			indicatorEma.setStock(stocks.get(i));
			indicatorEma.setType(type);
			indicatorEma.setValue(value);
			
			if(periodObj != null){
				indicatorEma.setPeriod(periodObj);
				int id = (int) emaDao.save(indicatorEma); 
				indicatorEma.setId(id);
			}
			
			indicatorEmas.add(indicatorEma);
			
			k++;
		}
		
		return indicatorEmas;
	}

	public Object EMA(String ticket, String date, int period, Type type) {
		
		IndicatorEma indicatorEma = emaDao.getIndicatorEmaAtOneDate(ticket, period, date, type);
		
		if(indicatorEma != null)
			return indicatorEma;
		
		Period periodObj = periodDao.getPeriodByValue(period);
		
		int numberOfDay = type.name().toUpperCase().equals(Type.WEEKLY.toString()) ? NumberOfDay.FRIDAY : -1;
		
		List<Stock> stocks = stockDao.getAllStockToDate(ticket, date, numberOfDay);
		
		List<IndicatorEma> indicatorEmas = new ArrayList<IndicatorEma>();
		
		//Calculator of first item
		IndicatorEma firstItem = new IndicatorEma();
		double ema = 0.00;
		for (int i = 0; i < period; i++) {
			ema += stocks.get(i).getClosePrice();
		}
		
		ema /= period;
		
		firstItem.setType(type);
		firstItem.setValue(ema);
		firstItem.setPeriod(periodObj);
		firstItem.setStock(stocks.get(period - 1));
		
		//int firstItemId = (int) emaDao.save(firstItem);
		//firstItem.setId(firstItemId);
		
		indicatorEmas.add(firstItem);
		
		int k = 1;
		double smoothing = (double) 2 / (period + 1);
		for (int i = period; i < stocks.size(); i++) {
			IndicatorEma item = new IndicatorEma();
			double previous = (double) indicatorEmas.get(k - 1).getValue();
			double value = smoothing * (stocks.get(i).getClosePrice() - previous) + previous;
			
			item.setPeriod(periodObj);
			item.setStock(stocks.get(i));
			item.setType(type);
			item.setValue(value);
			
			//int id = (int) emaDao.save(item); 
			//item.setId(id);
			
			indicatorEmas.add(item);
			
			k++;
		}
		
		return indicatorEmas.get(indicatorEmas.size() - 1);
	}
}
