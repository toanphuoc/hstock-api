package com.hstock.dao.user;

public enum UserRole {

	USER (1), ADMIN  (2);
	
	private int value;
	
	UserRole(int value){
		this.value = value;
	}
	
	public int getRoleValue(){
		return value;
	}
}
