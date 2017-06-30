package com.hstock.service.stock.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hstock.dao.favoriteStock.FavoriteStockDao;
import com.hstock.dao.security.AccessTokenDao;
import com.hstock.dao.stock.StockDao;
import com.hstock.model.Stock;
import com.hstock.model.StockFavorite;
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
	
	@Autowired
	private AccessTokenDao accessTokenDao;
	
	@Autowired
	private FavoriteStockDao favoriteStockDao;

	@Override
	public List<String> getFavoriteStock(String accessToken) {
		int userId = accessTokenDao.getUserIdByAccessToken(accessToken);
		List<StockFavorite> lst = favoriteStockDao.getListFavoriteStock(userId);
		return lst.stream().map(i -> i.getTicket()).collect(Collectors.toList());
	}

}
