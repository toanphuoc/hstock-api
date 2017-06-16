package com.hstock.service.indicator.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hstock.dao.indicator.StochRSIDao;
import com.hstock.dao.period.PeriodDao;
import com.hstock.dao.stock.StockDao;
import com.hstock.model.Period;
import com.hstock.model.Stock;
import com.hstock.model.Type;
import com.hstock.model.indicator.IndicatorRSI;
import com.hstock.model.indicator.IndicatorStochRSI;
import com.hstock.service.indicator.NumberOfDay;
import com.hstock.service.indicator.RSIService;
import com.hstock.service.indicator.StochRSIService;

public class StochRSIServiceImpl implements StochRSIService{

	@Autowired
	private StockDao stockDao;
	
	@Autowired
	private PeriodDao periodDao;
	
	@Autowired
	private RSIService rsiService;
	
	@Autowired
	private StochRSIDao stochRSIDao;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Object StochRSI(String ticket, String date, int periodRsi, int periodStochRsi, int periodSk, int periodSd, String type) {
		Type _type = Type.valueOf(type.toUpperCase());
		int numberOfDay = type.toUpperCase().equals(Type.WEEKLY.toString()) ? NumberOfDay.FRIDAY : -1;
		if(date != null){
			IndicatorStochRSI indicatorStochRSI = stochRSIDao.getIndicatorStochRSIAtOneDay(ticket, periodRsi, periodStochRsi, periodSk, periodSd, _type, date);
			if(indicatorStochRSI != null){
				return indicatorStochRSI;
			}
			
			List<Stock> stocks = stockDao.getAllStockToDate(ticket, date, numberOfDay);
			List<IndicatorStochRSI> indicatorStochRSIs = (List<IndicatorStochRSI>) StochRSI(stocks, ticket, periodRsi, periodStochRsi, periodSk, periodSd, _type);
			return indicatorStochRSIs.get(indicatorStochRSIs.size() - 1);
		}
		
		List<Stock> stocks = stockDao.getAllStock(ticket, numberOfDay);
		return StochRSI(stocks, ticket, periodRsi, periodStochRsi, periodSk, periodSd, _type);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Object StochRSI(List<Stock> stocks, String ticket, int periodRsi, int periodStochRsi, int periodSk, int periodSd, Type type) {
		List<IndicatorStochRSI> indicatorStochRSIs = stochRSIDao.getListIndicatorStochRSIs(ticket, periodRsi, periodStochRsi, periodSk, periodSd, type);
		
		if(indicatorStochRSIs != null && (indicatorStochRSIs.size() == stocks.size() - periodRsi - periodStochRsi + 1)){
			return indicatorStochRSIs;
		}
		
		
		indicatorStochRSIs = new ArrayList<IndicatorStochRSI>();
		List<IndicatorRSI> indicatorRsis = (List<IndicatorRSI>) rsiService.RSI(stocks, ticket, periodRsi, type);
		Queue<Double> queue = new LinkedList<>();
		
		for (int i = 0; i < periodStochRsi - 1; i++) {
			double temp = indicatorRsis.get(i).getRsi();
			queue.add(temp);
		}
		
		Period periodRsiObj = periodDao.getPeriodByValue(periodRsi);
		Period periodSPeriodObj = periodDao.getPeriodByValue(periodStochRsi);
		Period periodSkObj = periodDao.getPeriodByValue(periodSk);
		Period periodSdObj = periodDao.getPeriodByValue(periodSd);
		
		int j = 0;
		for (int i = periodStochRsi - 1; i < indicatorRsis.size(); i++) {
			IndicatorStochRSI indicatorStochRSI = new IndicatorStochRSI();
			
			double temp = indicatorRsis.get(i).getRsi();
			
			queue.add(temp);
			
			double max = queue.element();
			double min = queue.element();
			
			for (double item : queue) {
				if(item > max)
					max = item;
				if(item < min)
					min = item;
			}
			indicatorStochRSI.setRsi(temp);
			indicatorStochRSI.setStock(indicatorRsis.get(i).getStock());
			
			indicatorStochRSI.setStochRsi((double)(temp - min)/(max - min));
			indicatorStochRSI.setType(type);
			
			if(periodRsiObj != null) indicatorStochRSI.setPeriodRsi(periodRsiObj);

			if(periodSPeriodObj != null) indicatorStochRSI.setPeriodStochRsi(periodSPeriodObj);
			
			if(periodSkObj != null) indicatorStochRSI.setPeriodSK(periodSkObj);
			
			if(periodSdObj != null) indicatorStochRSI.setPeriodSD(periodSdObj);
			
			if(j < periodSk - 1 && periodRsiObj != null && periodSPeriodObj != null && periodSkObj != null && periodSdObj != null){
				int id = (int) stochRSIDao.save(indicatorStochRSI);
				indicatorStochRSI.setId(id);
			}
			
			indicatorStochRSIs.add(indicatorStochRSI);
			
			queue.poll();
			j++;
		}
		
		if(periodSk != 0){
			for (int i = periodSk - 1; i < indicatorStochRSIs.size(); i++) {
				double x = 0.00;
				for(int k = 0; k < periodSk; k++){
					x += indicatorStochRSIs.get(i - k).getStochRsi();
				}
				x /= periodSk;
				
				indicatorStochRSIs.get(i).setSk(x);
				
				if(periodSd != 0 && i - periodSk + 2 >= periodSd){
					double sd = 0.00;
					for (int z = 0; z < periodSd; z++) {
						sd += indicatorStochRSIs.get(i - z).getSk();
					}
					sd /= periodSd;
					indicatorStochRSIs.get(i).setSd(sd);
				}
				if(periodRsiObj != null && periodSPeriodObj != null && periodSkObj != null && periodSdObj != null){
					int id = (int) stochRSIDao.save(indicatorStochRSIs.get(i));
					indicatorStochRSIs.get(i).setId(id);
				}
				
			}
		}
		return indicatorStochRSIs;
	}
}
