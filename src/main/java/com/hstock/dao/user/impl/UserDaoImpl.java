package com.hstock.dao.user.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hstock.dao.AbstractGenericDao;
import com.hstock.dao.user.UserDao;
import com.hstock.model.User;

public class UserDaoImpl extends AbstractGenericDao<User> implements UserDao{

	@Override
	@Transactional
	public User getUserByUserName(String userName) {
		String hql = "FROM User where USER_NAME = :username";
		List<?> users =  getSession().createQuery(hql).setParameter("username", userName).list();
		if(users.size() == 1)
			return (User) users.get(0);
		return null;
	}

	@Override
	@Transactional
	public void updateLastLogin(int id) {
		String hql = "UPDATE User set LAST_LOGIN = now() where UserID = :id";
		getSession().createQuery(hql).setParameter("id", id).executeUpdate();
	}

}
