package com.hstock.service.stock;

public interface StockService {
	
	String WEEKLY = "WEEKLY";
	String DAILY = "DAILY";
	int NUMBER_OF_FRIDAY = 4;
	
	public Object EMA(String ticket, String date, int period, String type);
	
	public Object SMA(String ticket, String date, int period, String type);
	
	public Object RSI(String ticket, int period);
}
