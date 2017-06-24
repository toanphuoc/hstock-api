package com.hstock.service.security;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.hstock.model.User;

public interface SecuityService extends UserDetailsService{

	public Map<String, Object> login(String ip, String userName, String password);
	
	public boolean checkAccessToken(String accessToken);
	
	public User getUserByUsername(String userName);
	
}
