package com.hstock.dao.favoriteStock;

import java.util.List;

import com.hstock.dao.GenericDao;
import com.hstock.model.StockFavorite;

public interface FavoriteStockDao extends GenericDao<StockFavorite>{

	/**
	 * Get list favorite stock by user id
	 * @param userId
	 * @return
	 */
	public List<StockFavorite> getListFavoriteStock(int userId);
}
