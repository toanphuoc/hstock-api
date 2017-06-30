package com.hstock.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Access_Token")
public class AccessToken {

	@Id
	@Column(name = "ACCESS_TOKEN", unique = true, nullable = false)
	private String accessToken;
	
	@Column(name = "ACTIVE_TIME")
	private Date activeTime = new Date();
	
	@OneToOne
	@JoinColumn(name="USER_ID", foreignKey=@ForeignKey(name="TOKEN_USER_FK"))
	private User user;

	public AccessToken(){
		
	}
	
	public AccessToken(String accessToken, User user){
		this.accessToken = accessToken;
		this.user = user;
	}
	
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Date getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
