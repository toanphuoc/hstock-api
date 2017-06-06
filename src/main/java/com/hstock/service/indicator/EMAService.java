package com.hstock.service.indicator;

import java.util.List;

import com.hstock.model.Stock;
import com.hstock.model.Type;

public interface EMAService {

	public Object EMA(String ticket, String date, int period, String type);
	
	public Object EMA(List<Stock> stocks, String ticket, int period, Type type);
}
