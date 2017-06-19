package com.hstock.service.indicator.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hstock.dao.indicator.BBDao;
import com.hstock.dao.period.PeriodDao;
import com.hstock.dao.stock.StockDao;
import com.hstock.model.Period;
import com.hstock.model.Stock;
import com.hstock.model.Type;
import com.hstock.model.indicator.IndicatorBB;
import com.hstock.service.indicator.BBService;
import com.hstock.service.indicator.NumberOfDay;

public class BBServiceImpl implements BBService{

	@Autowired
	private StockDao stockDao;
	
	@Autowired
	private PeriodDao periodDao;
	
	@Autowired
	private BBDao bbDao;
	
	@Override
	@Transactional
	public Object BB(String ticket, String date, int period, int standardInviation, String type) {
		Type _type = Type.valueOf(type.toUpperCase());
		int numberOfDay = _type.name().toUpperCase().equals(Type.WEEKLY.toString()) ? NumberOfDay.FRIDAY : -1;
		if(date != null){
			
		}
		List<Stock> stocks = stockDao.getAllStock(ticket, numberOfDay);
		return BB(stocks, period, standardInviation, _type);
	}
	
	public Object BB(List<Stock> stocks, int period, int standartInviation, Type type){
		List<IndicatorBB> indicatorBBs = new ArrayList<IndicatorBB>();
		
		Period periodObj = periodDao.getPeriodByValue(period);
		
		for (int i = period - 1; i < stocks.size(); i++) {
			IndicatorBB indicatorBB = new IndicatorBB();
			double sma = 0.0;
			double sd = 0.00;
			for (int j = 0; j < period; j++) {
				sma += stocks.get(i - j).getClosePrice();
				
			}
			sma /= period;
			
			for (int j = 0; j < period; j++) {
				sd +=  Math.pow(stocks.get(i - j).getClosePrice() - sma, 2)/period;
			}
			sd = Math.sqrt(sd);
			
			indicatorBB.setSma(sma);
			indicatorBB.setStock(stocks.get(i));
			indicatorBB.setType(type);
			indicatorBB.setStandardDeviation(standartInviation);
			
			double upperBand = sma + sd * standartInviation;
			double lowerBand = sma - sd * standartInviation;
			
			indicatorBB.setUpperBand(upperBand);
			indicatorBB.setLowerBand(lowerBand);
			indicatorBB.setBb(upperBand - lowerBand);
			
			if(periodObj != null){
				indicatorBB.setPeriod(periodObj);
				int id = (int) bbDao.save(indicatorBB);
				indicatorBB.setId(id);
			}
			
			indicatorBBs.add(indicatorBB);
		}
		return indicatorBBs;
	}

}
