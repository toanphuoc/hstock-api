package com.hstock.service.indicator;

public interface MACDService {

	public Object MACD(String ticket, String date, int periodX, int periodY, int periodSignal, String type);
}
