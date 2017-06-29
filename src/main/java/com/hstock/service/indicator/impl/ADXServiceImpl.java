package com.hstock.service.indicator.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hstock.dao.period.PeriodDao;
import com.hstock.dao.stock.StockDao;
import com.hstock.model.Stock;
import com.hstock.model.Type;
import com.hstock.service.indicator.ADXService;
import com.hstock.service.indicator.NumberOfDay;

@Transactional
public class ADXServiceImpl implements ADXService {

	@Autowired
	private StockDao stockDao;
	
	@Autowired
	private PeriodDao periodDao;
	
	@Override
	public Object ADX(String ticket, String date, int period, String type) {
		Type _type = Type.valueOf(type.toUpperCase());
		int numberOfDay = _type.name().toUpperCase().equals(Type.WEEKLY.toString()) ? NumberOfDay.FRIDAY : -1;
		List<Stock> stocks = stockDao.getAllStock(ticket, numberOfDay);
		
		if(date != null){
			
		}
		return ADX(stocks, ticket, period, _type);
	}

	@Override
	public Object ADX(List<Stock> stocks, String ticket, int period, Type type) {
		// TODO Auto-generated method stub
		return null;
	}

}
