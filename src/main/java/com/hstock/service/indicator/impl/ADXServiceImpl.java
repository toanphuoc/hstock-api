package com.hstock.service.indicator.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hstock.dao.period.PeriodDao;
import com.hstock.dao.stock.StockDao;
import com.hstock.model.Stock;
import com.hstock.model.Type;
import com.hstock.service.indicator.ADXService;

@Transactional
public class ADXServiceImpl implements ADXService {

	@Autowired
	private StockDao stockDao;
	
	@Autowired
	private PeriodDao periodDao;
	
	@Override
	public Object ADX(String ticket, String date, int period, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object ADX(List<Stock> stocks, String ticket, int period, Type type) {
		// TODO Auto-generated method stub
		return null;
	}

}
