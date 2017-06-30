package com.hstock.dao.stock;

import java.util.List;

import com.hstock.dao.GenericDao;
import com.hstock.model.StockFavorite;

public interface StockFavoriteDao extends GenericDao<StockFavorite>{

	/**
	 * Get list stock favorite of user
	 * @param accessToken
	 * @return
	 */
	public List<StockFavorite> getStockFavoriteOfUser(String accessToken);
}
