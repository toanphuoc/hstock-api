package com.hstock.dao.favoriteStock.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hstock.dao.AbstractGenericDao;
import com.hstock.dao.favoriteStock.FavoriteStockDao;
import com.hstock.model.StockFavorite;

@Transactional
public class FavoriteStockDaoImpl extends AbstractGenericDao<StockFavorite> implements FavoriteStockDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<StockFavorite> getListFavoriteStock(int userId) {
		String hql = "FROM StockFavorite where USER_ID = :userID GROUP BY TICKET";
		return getSession().createQuery(hql).setParameter("userID", userId).list();
	}

}
