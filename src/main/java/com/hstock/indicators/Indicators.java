package com.hstock.indicators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hstock.model.Stock;

public class Indicators {

	/**
	 * Calculator Simple Moving Average at a time in list of stocks
	 * @param stocks
	 * @return
	 */
	public static Object SMA(List<Stock> stocks, String type){
		Map<String, Object> map = new HashMap<String, Object>();
		if(stocks.size() == 0){
			map.put("Error", "No found data");
			return map;
		}
		
		double sum = 0 ;
		for (Stock stock : stocks) {
			sum += stock.getClosePrice();
		}
		
		int period = stocks.size();
		
		map.put("stock", stocks.get(period - 1));
		map.put("type", type.toLowerCase());
		map.put("period", period);
		map.put("sma", sum / period);
		return map;
	}
	
	/**
	 * Calculator Simple Moving Average all of stock
	 * @param stocks
	 * @param period
	 * @return
	 */
	public static Object SMA(List<Stock> stocks, int period, String type){
		if(period > stocks.size()){
			return null;
		}
		Map<String, Object>  rs = new HashMap<>();
		rs.put("period", period);
		rs.put("type", type);
		
		List<Map<String, Object>> lstMA = new ArrayList<Map<String,Object>>();
		
		for (int i = period - 1; i < stocks.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("stock_id", stocks.get(i).getId());
			double x = 0.00;
			for(int j = 0; j < period; j++){
				x += stocks.get(i - j).getClosePrice();
			}
			x /= period;
			map.put("sma", x);
			
			lstMA.add(map);
		}
		
		rs.put("sma", lstMA);
		return rs;
	}

	
	/**
	 * Calculator Exponential Moving Average all of stock
	 * @param stocks
	 * @param period
	 * @return
	 */
	public static Object EMA(List<Stock> stocks, int period, String type) {
		
		double smoothing = (double) 2 / (period + 1);
		
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
		
		firstItem.put("close_price", stocks.get(period - 1).getClosePrice());
		firstItem.put("date", stocks.get(period - 1).getOpenDate());
		firstItem.put("ema", firstEMA);
		
		lstEMA.add(firstItem);
		
		int k = 1;
		for (int i = period; i < stocks.size(); i++) {
			
			Map<String, Object> map = new HashMap<String, Object>();
			double previous = (double) lstEMA.get(k - 1).get("ema");
			double ema = smoothing * (stocks.get(i).getClosePrice() - previous) + previous;
			
			map.put("close_price", stocks.get(i).getClosePrice());
			map.put("date", stocks.get(i).getOpenDate());
			map.put("ema", ema);
			lstEMA.add(map);
			k++;
		}
		
		return lstEMA;
	}

}
