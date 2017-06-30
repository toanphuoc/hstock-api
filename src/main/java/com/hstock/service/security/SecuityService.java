package com.hstock.service.security;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.hstock.model.User;

public interface SecuityService extends UserDetailsService{

	/**
	 * Login
	 * @param ip
	 * @param userName
	 * @param password
	 * @return
	 */
	public Map<String, Object> login(String ip, String userName, String password);
	
	/**
	 * Check valid access token
	 * @param accessToken
	 * @return
	 */
	public boolean checkAccessToken(String accessToken);
	
	/**
	 * Get user by user name
	 * @param userName
	 * @return
	 */
	public User getUserByUsername(String userName);
	
	/**
	 * Remove access token
	 * @param accessToken
	 * @return
	 */
	public void removeAccessToken(String accessToken);
	
}
