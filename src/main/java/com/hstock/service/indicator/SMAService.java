package com.hstock.service.indicator;

public interface SMAService {

	int NUMBER_OF_FRIDAY = 4;
	
	public Object SMA(String ticket, String date, int period, String type);
}
