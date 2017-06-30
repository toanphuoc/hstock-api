package com.hstock.service.stock;

import java.util.List;
import com.hstock.model.Stock;

public interface StockService {
	
	/**
	 * Get list of favorite stock by access token
	 * @param accessToken
	 * @return
	 */
	public List<String> getFavoriteStock(String accessToken);
}
