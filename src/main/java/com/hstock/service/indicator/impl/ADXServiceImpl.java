package com.hstock.service.indicator.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hstock.dao.indicator.ADXDao;
import com.hstock.dao.period.PeriodDao;
import com.hstock.dao.stock.StockDao;
import com.hstock.model.Period;
import com.hstock.model.Stock;
import com.hstock.model.Type;
import com.hstock.model.indicator.IndicatorADX;
import com.hstock.service.indicator.ADXService;
import com.hstock.service.indicator.NumberOfDay;

@Transactional
public class ADXServiceImpl implements ADXService {

	@Autowired
	private StockDao stockDao;
	
	@Autowired
	private ADXDao adxDao;
	
	@Autowired
	private PeriodDao periodDao;
	
	@Override
	public Object ADX(String ticket, String date, int period, String type) {
		Type _type = Type.valueOf(type.toUpperCase());
		if(date != null){
			return ADX(ticket, period, _type, date);
		}
		
		return ADX(ticket, period, _type);
	}

	private IndicatorADX ADX(String ticket, int period, Type type, String date) {
		IndicatorADX indicatorADX = adxDao.getIndicatorADXAtOneDate(ticket, period, type, date);
		if(indicatorADX != null) return indicatorADX;
		
		int numberOfDay = type.name().toUpperCase().equals(Type.WEEKLY.toString()) ? NumberOfDay.FRIDAY : -1;
		
		List<Stock> stocks = stockDao.getAllStockToDate(ticket, date, numberOfDay);
		
		List<IndicatorADX> indicatorADXs = this.ADX(stocks, ticket, period, type, false);
		return indicatorADXs.get(indicatorADXs.size() - 1);
	}
	
	@Override
	public List<IndicatorADX> ADX(String ticket, int period, Type type) {
		
		List<IndicatorADX> indicatorADXs = adxDao.getListIndicatorADX(ticket, period, type);
		if(!indicatorADXs.isEmpty())
			return indicatorADXs;
		
		indicatorADXs = new ArrayList<>();
		int numberOfDay = type.name().toUpperCase().equals(Type.WEEKLY.toString()) ? NumberOfDay.FRIDAY : -1;
		List<Stock> stocks = stockDao.getAllStock(ticket, numberOfDay);
		
		return this.ADX(stocks, ticket, period, type, true);
	}
	
	private List<IndicatorADX> ADX(List<Stock> stocks, String ticket, int period, Type type, boolean isSave){
		
		List<IndicatorADX> indicatorADXs = new ArrayList<>();
		Period periodObn = periodDao.getPeriodByValue(period);
		
		double preSumTr = 0.0;
		double preSumPlusDm = 0.0;
		double preSumMinusDm = 0.0;
		double sumDx = 0.0;
		double preAdx = 0.0;
		
		for (int i = 1; i < stocks.size(); i++) {
			
			IndicatorADX indicatorADX = new IndicatorADX();
			indicatorADX.setStock(stocks.get(i));
			indicatorADX.setType(type);
			
			
			Stock stock = stocks.get(i);
			Stock preStock = stocks.get(i - 1);
			
			double tr = Arrays.asList(stock.getHighPrice() - stock.getLowPrice(), Math.abs(stock.getHighPrice() - preStock.getClosePrice()), Math.abs(stock.getLowPrice() - preStock.getClosePrice())).stream().max(Double::compare).get();
			
			double plusDm = 0.00;
			if(stock.getHighPrice() - preStock.getHighPrice() > preStock.getLowPrice() - stock.getLowPrice()){
				plusDm = stock.getHighPrice() - preStock.getHighPrice() >= 0 ? stock.getHighPrice() - preStock.getHighPrice() : 0; 
			}
			
			double minusDm = 0.0;
			if(preStock.getLowPrice() - stock.getLowPrice() > stock.getHighPrice() - preStock.getHighPrice()){
				minusDm = preStock.getLowPrice() - stock.getLowPrice() >= 0 ? preStock.getLowPrice() - stock.getLowPrice() : 0;
			}
			
			if(i < period) {
				preSumTr += tr;
				preSumPlusDm += plusDm;
				preSumMinusDm += minusDm;
			}
			
			if(i == period){
				preSumTr += tr;
				preSumPlusDm += plusDm;
				preSumMinusDm += minusDm;
				
				indicatorADX.setPlusDI(100*(preSumPlusDm/preSumTr));
				indicatorADX.setMinusDI(100*(preSumMinusDm/preSumTr));
				
				double diDiff = Math.abs(preSumPlusDm - preSumMinusDm);
				double diSum = preSumPlusDm + preSumMinusDm;
				double dx = diDiff/diSum * 100;
				indicatorADX.setDx(dx);
				
				sumDx += dx;
			}
			
			if(i > period){
				double sumTr = preSumTr - preSumTr/period + tr;
				double sumPlusDm = preSumPlusDm - preSumPlusDm/period + plusDm;
				double sumMinusDm = preSumMinusDm - preSumMinusDm/period + minusDm;
				
				indicatorADX.setPlusDI(100*(sumPlusDm/sumTr));
				indicatorADX.setMinusDI(100*(sumMinusDm/sumTr));
				
				double diDiff = Math.abs(sumPlusDm - sumMinusDm);
				double diSum = sumPlusDm + sumMinusDm;
				
				double dx = diDiff/diSum * 100;
				indicatorADX.setDx(dx);
				sumDx += dx;
				
				preSumTr = sumTr;
				preSumPlusDm = sumPlusDm;
				preSumMinusDm = sumMinusDm;
				
				if(i == period * 2 - 1){
					double adx = sumDx / period;
					indicatorADX.setAdx(adx);
					preAdx = adx;
				}
				
				if(i > period * 2 - 1){
					double adx = ((preAdx * (period - 1)) + dx) / period;
					indicatorADX.setAdx(adx);
					preAdx = adx;
				}
			}
			if(periodObn != null){
				indicatorADX.setPeriod(periodObn);
				
			}
			
			if(isSave){
				int id = (int) adxDao.save(indicatorADX);
				indicatorADX.setId(id);
			}
			
			indicatorADXs.add(indicatorADX);
		}
		return indicatorADXs;
	}



}
