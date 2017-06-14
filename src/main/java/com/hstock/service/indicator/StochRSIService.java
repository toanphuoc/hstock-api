package com.hstock.service.indicator;

import java.util.List;

import com.hstock.model.Stock;
import com.hstock.model.Type;

public interface StochRSIService {

	public Object StochRSI(String ticket, String date, int periodRsi, int periodStochRsi, int periodSk, int periodSd, String type);
	
	public Object StochRSI(List<Stock> stocks, String ticket, int periodRsi, int periodStochRsi, int periodSk, int periodSd, Type type);
}
