package com.hstock.service.stock.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hstock.dao.stock.StockDao;
import com.hstock.service.stock.StockService;

@Service("stockService")
public class StockServiceImpl implements StockService{

	@Autowired
	private StockDao stockDao;

	@Override
	@Transactional
	public Object EMA(String ticket, String date, int period, String type) {
		/*if(type != null && type.toUpperCase().equals(WEEKLY)){
			if(date != null){
				*//**
				 * Get all stock at Friday and to day 'date' of ticket 'ticket'
				 *//*
				List<Stock> stocks = stockDao.getAllStockAtAllWeekend(ticket, NUMBER_OF_FRIDAY, date, period);
				return Indicators.SMA(stocks, WEEKLY);
			}
			
			*//**
			 * Get all Stock at Friday of ticket 'ticket'
			 *//*
			
			List<Stock> stocks = stockDao.getAllStock(ticket, NUMBER_OF_FRIDAY);
			return Indicators.EMA(stocks, period, WEEKLY);
		}
		
		if(date != null){
			
			*//**
			 * Get all stock to day 'date'
			 *//*
			List<Stock> stocks = stockDao.getAllStock(ticket, date, period);
			return Indicators.SMA(stocks, DAILY);
		}
		
		*//**
		 * Get all of stocks
		 *//*
		List<Stock> stocks = stockDao.getAllStock(ticket);
		return Indicators.EMA(stocks, period, DAILY);*/
		return null;
	}
	
	@Override
	@Transactional
	public Object RSI(String ticket, int period) {
		return null;
/*		List<Stock> stocks = stockDao.getAllStock(ticket);
		
		if(period > stocks.size()){
			return null;
		}
		
		List<Map<String, Object>> rsi = new ArrayList<Map<String, Object>>();
		Map<String, Object> firstItem = new HashMap<String, Object>();
		
		firstItem.put("date", stocks.get(period).getOpenDate());
		
		double sumGain = 0;
        double sumLoss = 0;
        double firstValue = 0.00;
		for (int i = 1; i < period + 1; i++) {
			double difference = stocks.get(i).getClosePrice() - stocks.get(i-1).getClosePrice();
			if(difference >=0){
        		sumGain += difference;
        	}else{
        		sumLoss -= difference;
        	}	
		}
		
		if(sumGain == 0){
			firstValue = 0.00;
        }else if(sumLoss == 0){
        	firstValue = 100;
        }else{
        	double avgGain = sumGain/period;
        	double avgLoss = sumLoss/period;
        	
        	firstItem.put("avgGain", avgGain);
        	firstItem.put("avgLoss", avgLoss);
        	
        	double rs  = avgGain/avgLoss;
        	firstItem.put("rs", rs);
        	
        	firstValue = 100 - (100/(1 + rs));
        	firstItem.put("rsi", firstValue);
        }
        rsi.add(firstItem);
		
        int k = 1;
        for (int i = period + 1; i < stocks.size(); i++) {
        	Map<String, Object> map = new HashMap<>();
        	map.put("date", stocks.get(i).getOpenDate());
        	double difference = stocks.get(i).getClosePrice() - stocks.get(i-1).getClosePrice();
        	
        	double currentGain = difference >= 0 ? difference : 0;
        	double avgGain = ((double)rsi.get(k - 1).get("avgGain") * (period - 1) + currentGain) / 14;
        	map.put("avgGain", avgGain);
        	
        	double currentLoss = difference < 0 ? Math.abs(difference) : 0;
        	double avgLoss = ((double) rsi.get(k - 1).get("avgLoss") * (period - 1) + currentLoss) / 14;
        	map.put("avgLoss", avgLoss);
        	
        	double rs = avgGain/avgLoss;
        	map.put("rs", rs);
        	
        	double valueRSI = 100 - (100/(1 + rs));
        	map.put("rsi", valueRSI);
        	
        	rsi.add(map);
        	
        	k++;
		}
        
		return rsi;*/
	}
	

	
	/**
	 * Exponential Moving Average
	 * @param tictet
	 * @param period
	 * @return
	 */
	/*public Object EMA(String ticket, int period){
		
		double smoothing = (double) 2 / (period + 1);
		
		List<Stock> stocks = stockDao.getStockByTicket(ticket);
		
		if(period > stocks.size()){
			return null;
		}
		
		List<Map<String, Object>> lstEMA = new ArrayList<Map<String,Object>>();
		Map<String, Object> firstItem = new HashMap<String, Object>();
		
		double firstEMA = 0.00;
		for (int i = 0; i < period; i++) {
			firstEMA += stocks.get(i).getClosePrice();
		}

		firstEMA /= period;
		
		firstItem.put("ClosePrice", stocks.get(period - 1).getClosePrice());
		firstItem.put("Date", stocks.get(period - 1).getOpenDate());
		firstItem.put("EMA", firstEMA);
		
		lstEMA.add(firstItem);
		
		int k = 1;
		for (int i = period; i < stocks.size(); i++) {
			
			Map<String, Object> map = new HashMap<String, Object>();
			double previous = (double) lstEMA.get(k - 1).get("EMA");
			double ema = smoothing * (stocks.get(i).getClosePrice() - previous) + previous;
			
			map.put("ClosePrice", stocks.get(i).getClosePrice());
			map.put("Date", stocks.get(i).getOpenDate());
			map.put("EMA", ema);
			lstEMA.add(map);
			k++;
		}
		
		return lstEMA;
	}*/
	

}
