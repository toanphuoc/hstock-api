package com.hstock.service.indicator;

import java.util.List;

import com.hstock.model.Stock;
import com.hstock.model.Type;

public interface RSIService {

	public Object RSI(String ticket, String date, int period, String type);
	
	public Object RSI(List<Stock> stocks, String ticket, int period, Type type);
}
