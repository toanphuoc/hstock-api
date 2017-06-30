package com.hstock.dao.stock.impl;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.hstock.dao.AbstractGenericDao;
import com.hstock.dao.stock.StockFavoriteDao;
import com.hstock.model.StockFavorite;

@Transactional
public class StockFavoriteDaoImpl extends AbstractGenericDao<StockFavorite> implements StockFavoriteDao{

	@Override
	public List<StockFavorite> getStockFavoriteOfUser(String accessToken) {
		// TODO Auto-generated method stub
		return null;
	}

}
