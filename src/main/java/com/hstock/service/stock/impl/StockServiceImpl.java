package com.hstock.service.stock.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hstock.dao.stock.StockDao;
import com.hstock.model.Stock;
import com.hstock.model.User;
import com.hstock.service.security.SecuityService;
import com.hstock.service.stock.StockService;

@Service("stockService")
@Transactional
public class StockServiceImpl implements StockService{

	@Autowired
	private StockDao stockDao;
	
	@Autowired
	private SecuityService securityService;

	@Override
	public List<Stock> getFavoriteStock(String accessToken) {
		
		return null;
	}

}
