package com.hstock.service.security.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
		
		UserDetails userDetails = loadUserByUsername(userName);
		
		
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
			
			//Add access  token
			AccessToken token = new AccessToken(accessToken, user);
			accessTokenDao.addAccessToken(token);
			
			//Update last login
			userDao.updateLastLogin(user.getUserId());
			
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
		System.out.println(hash);
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

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.getUserByUserName(username);
		System.out.println("User name: " + username);
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), 
                true, true, true, true, getGrantedAuthorities(user));
	}

	private Collection<? extends GrantedAuthority> getGrantedAuthorities(
			User user) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(user.getUserRole().toString()));
		return grantedAuthorities;
	}

}
