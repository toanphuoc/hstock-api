package com.hstock.dao.user;

import com.hstock.dao.GenericDao;
import com.hstock.model.User;

public interface UserDao extends GenericDao<User>{
	
	/**
	 * Get user by user name
	 * @param userName
	 * @return
	 */
	public User getUserByUserName(String userName);
	
	/**
	 * Update last login
	 * @param id
	 */
	public void updateLastLogin(int id);
}
