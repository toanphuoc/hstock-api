package com.hstock.service.indicator;

import java.util.List;

import com.hstock.model.Stock;
import com.hstock.model.Type;

public interface ADXService {

	public Object ADX(String ticket, String date, int period, String type);
	
	public Object ADX(String ticket, int period, Type type);
}
