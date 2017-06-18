package com.hstock.service.security;

import java.util.Map;

public interface SecuityService {

	public Map<String, Object> login(String ip, String userName, String password);
	
	public boolean checkAccessToken(String accessToken);
	
}
