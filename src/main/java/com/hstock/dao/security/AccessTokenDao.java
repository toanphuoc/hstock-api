package com.hstock.dao.security;

import com.hstock.dao.GenericDao;
import com.hstock.model.AccessToken;

public interface AccessTokenDao extends GenericDao<AccessToken> {

	/**
	 * Add access token
	 * @param accessToken
	 */
	public void addAccessToken(AccessToken accessToken);
	
	/**
	 * Update time expired access token
	 * @param accessToken
	 * @return
	 */
	public boolean updateActiveTime(String accessToken);
	
	/**
	 * Get user id by access token
	 * @param accessToken
	 * @return
	 */
	public int getUserIdByAccessToken(String accessToken);
	
}
