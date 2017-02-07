package com.advanz.erp.masters.model;

import java.sql.Timestamp;

import com.advanz.erp.common.model.BaseDTO;
@SuppressWarnings("serial")
public class LoggerDTO extends BaseDTO {
	private Integer id;
	private Timestamp loginTime;
	private Timestamp logoutTime;
	private Integer userId;
	private String remoteAddress;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Timestamp getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}
	public Timestamp getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(Timestamp logoutTime) {
		this.logoutTime = logoutTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getRemoteAddress() {
		return remoteAddress;
	}
	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}
	
		
	
}