package com.hstock.service.stock.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hstock.dao.stock.StockDao;
import com.hstock.model.Stock;
import com.hstock.service.stock.StockService;

@Service("stockService")
public class StockServiceImpl implements StockService{

	@Autowired
	private StockDao stockDao;
	
	@Override
	@Transactional
	public List<Stock> getAllStocks() {
		return stockDao.findAll();
	}

	@Override
	@Transactional
	public Stock getStockById(Long id) {
		return stockDao.getStockById(id);
	}

	@Override
	@Transactional
	public Object EMA(String ticket, String date, int period) {
		if(date != null)
			return SMAOfDate(ticket, period, date);
		
		return EMA(ticket, period);
	}

	@Override
	@Transactional
	public Object SMA(String ticket, String date, int period) {
		if(date != null){
			return SMAOfDate(ticket, period, date);
		}
		
		return SMA(ticket, period);
	}
	
	@Override
	@Transactional
	public Object RSI(String ticket, int period) {
		List<Stock> stocks = stockDao.getStockByTicket(ticket);
		
		
		List<Double> rsi = new ArrayList<Double>();
		for (int i = 1; i < stocks.size(); i++) {
			double sumGain = 0;
	        double sumLoss = 0;
	        double index = 0.00;
	        for (int j = i; j < period + i && period + i < stocks.size(); j++) {
	        	double difference = stocks.get(j).getClosePrice() - stocks.get(j-1).getClosePrice();
	        	System.out.println("Difference: " + difference);
	        	if(difference >=0){
	        		sumGain += difference;
	        	}else{
	        		sumLoss -= difference;
	        	}	
			}
	        if(sumGain == 0){
	        	index = 0.00;
	        }else if(sumLoss == 0){
	        	index = 100;
	        }else{
	        	double avgGain = sumGain/period;
	        	double avgLoss = sumLoss/period;
	        	
	        	System.out.println("Avg Gain: " + avgGain);
	        	System.out.println("Avg Loss: " + avgLoss);
	        	
	        	double rs  = avgGain/avgLoss;
	        	System.out.println(rs);
	        	index = 100 - (100/(1 + rs));
	        }
	        rsi.add(index);
		}
		return rsi;
	}
	
	/**
	 * Simple Moving Average at day
	 * @param ticket
	 * @param period
	 * @param date
	 * @return
	 */
	public Object SMAOfDate(String ticket, int period, String date){
		List<Stock> stocks = stockDao.getStockByTicketAndOpenDate(ticket, date, period);
		Map<String, Object> map = new HashMap<String, Object>();
		if(stocks.size() == 0){
			map.put("Error", "No found data");
			return map;
		}
		
		double sum = 0 ;
		for (Stock stock : stocks) {
			sum += stock.getClosePrice();
		}
		
		map.put("ticket", ticket);
		map.put("date", date);
		map.put("period", period);
		map.put("result", sum / period);
		return map;
	}
	
	/**
	 * Exponential Moving Average
	 * @param tictet
	 * @param period
	 * @return
	 */
	public Object EMA(String ticket, int period){
		
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
	}
	
	/**
	 * Simple Moving Average function
	 * @param ticket
	 * @param number
	 * @return
	 */
	public Object SMA(String ticket, int number){
		List<Stock> stocks = stockDao.getStockByTicket(ticket);
		if(number > stocks.size()){
			return null;
		}
		
		List<Map<String, Object>> lstMA = new ArrayList<Map<String,Object>>();
		
		for (int i = number - 1; i < stocks.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("date", stocks.get(i).getOpenDate());
			double x = 0.00;
			for(int j = 0; j < number; j++){
				x += stocks.get(i - j).getClosePrice();
			}
			x /= number;
			map.put("SMA", x);
			
			lstMA.add(map);
		}
		return lstMA;
	}
}
