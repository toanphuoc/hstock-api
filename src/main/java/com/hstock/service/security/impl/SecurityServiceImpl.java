package com.hstock.service.security.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import com.hstock.dao.security.AccessTokenDao;
import com.hstock.dao.security.UserDao;
import com.hstock.dao.security.UserRole;
import com.hstock.model.AccessToken;
import com.hstock.model.User;
import com.hstock.service.security.SecuityService;

public class SecurityServiceImpl implements SecuityService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AccessTokenDao accessTokenDao;
	
	@Override
	@Transactional
	public Map<String, Object> login(String ip, String userName, String password) {

		String accessToken = null;
		User user = userDao.getUserByUserName(userName);
		
		//Check user name
		if(user == null){
			return login(false, accessToken, "User is not exist", user);
		}
		
		//Check user role
		int[] roles = {UserRole.USER.getRoleValue(), UserRole.ADMIN.getRoleValue()};
		if(!ArrayUtils.contains(roles, user.getUserRole().getRoleId())){
			return login(false, accessToken, null, user);
		}
		
		if(isMatchPassword(password, user.getPassword(), user.getSalt())){
			ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
			accessToken = encoder.encodePassword(user.getUserName(), RandomStringUtils.random(8));
			
			AccessToken token = new AccessToken(accessToken, user);
			accessTokenDao.addAccessToken(token);
			
			return login(true, accessToken, "Login success", user);
		}
		
		
		return login(false, accessToken, null, user);
	}
	
	/**
	 * Check matching password
	 * @param password
	 * @param passwordEncrypt
	 * @param salt
	 * @return
	 */
	public boolean isMatchPassword(String password, String passwordEncrypt, String salt){
		
		ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
		String hash = encoder.encodePassword(password, salt);
		
		//return encoder.isPasswordValid(passwordEncrypt, password, salt);
		return hash.equals(passwordEncrypt);
	}
	
	public Map<String, Object> login(boolean result, String token, String message, User user){
		
		Map<String, Object> map = new HashMap<>();
		map.put("success", result);
		map.put("access_token", token);
		map.put("message", message);
		
		if(result && user != null){
			
		}
		return map;
	}

	@Override
	@Transactional
	public boolean checkAccessToken(String token) {
		AccessToken accessToken = accessTokenDao.findById(token);
		if(accessToken != null){
			Calendar clNow = Calendar.getInstance();
			
			Calendar clActive = Calendar.getInstance();
			clActive.setTime(accessToken.getActiveTime());
			clActive.add(Calendar.MINUTE, 30);
			
			if(clNow.before(clActive)){
				//Update time active
				return accessTokenDao.updateActiveTime(token);
			}
		}
		return false;
	}

}
