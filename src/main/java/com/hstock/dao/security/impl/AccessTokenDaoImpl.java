package com.hstock.dao.security.impl;

import org.springframework.transaction.annotation.Transactional;

import com.hstock.dao.AbstractGenericDao;
import com.hstock.dao.security.AccessTokenDao;
import com.hstock.model.AccessToken;

@Transactional
public class AccessTokenDaoImpl extends AbstractGenericDao<AccessToken> implements AccessTokenDao{

	@Override
	public void addAccessToken(AccessToken accessToken) {
		
		if(findById(accessToken.getAccessToken()) != null){
			delete(accessToken.getAccessToken());
		}
		
		save(accessToken);
	}

	@Override
	public boolean updateActiveTime(String accessToken) {
		String hql = "UPDATE access_token set ACTIVE_TIME = now() where ACCESS_TOKEN = :accessToken";
		return getSession().createSQLQuery(hql).addEntity(AccessToken.class).setParameter("accessToken", accessToken).executeUpdate() == 1;
	}

	@Override
	public int getUserIdByAccessToken(String accessToken) {
		return findById(accessToken).getUser().getUserId();
	}
}
