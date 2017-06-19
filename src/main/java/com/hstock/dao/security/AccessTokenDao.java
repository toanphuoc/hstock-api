package com.hstock.dao.security;

import com.hstock.dao.GenericDao;
import com.hstock.model.AccessToken;

public interface AccessTokenDao extends GenericDao<AccessToken> {

	public void addAccessToken(AccessToken accessToken);
	
	public boolean updateActiveTime(String accessToken);
	
}
