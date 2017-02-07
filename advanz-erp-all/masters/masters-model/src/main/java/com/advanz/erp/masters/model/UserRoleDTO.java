package com.advanz.erp.masters.model;

import java.io.Serializable;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class UserRoleDTO extends BaseDTO implements Serializable {
	private Integer sno;
	private Integer roleId;
	private Integer userId;
	private Boolean hotKeyActive=false; 
	public Integer getSno() {
		return sno;
	}
	public void setSno(Integer sno) {
		this.sno = sno;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
  
	public Boolean getHotKeyActive() {
		return hotKeyActive;
	}
	public void setHotKeyActive(Boolean hotKeyActive) {
		this.hotKeyActive = hotKeyActive;
	}
	@Override 
	public String toString() {
	return "USER_ROLE_ENTITY=[sno="+sno+"roleId"+roleId+"userId"+userId+"]";
	}
	
	
}
