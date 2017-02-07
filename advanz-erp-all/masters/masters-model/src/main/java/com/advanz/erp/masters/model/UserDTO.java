package com.advanz.erp.masters.model;

import java.util.List;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class UserDTO  extends BaseDTO {

	private Integer userId ; 
	
	private String userLoginId;
	
	private String userPassword;
	
	private List<UserRoleAndRightsDTO> rolesAndRights;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public List<UserRoleAndRightsDTO> getRolesAndRights() {
		return rolesAndRights;
	}

	public void setRolesAndRights(List<UserRoleAndRightsDTO> rolesAndRights) {
		this.rolesAndRights = rolesAndRights;
	}
	
	
	
}
