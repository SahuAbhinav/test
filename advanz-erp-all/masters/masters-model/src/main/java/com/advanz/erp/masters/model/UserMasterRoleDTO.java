package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class UserMasterRoleDTO extends BaseDTO {
	private Integer userId;
	private String userLoginId;
	private String userPassword;
	private String userFullName;
    private Integer activeFlag=1;
    
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

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	private RoleMasterDTO roleMasterDTO;
		
	
	
	public RoleMasterDTO getRoleMasterDTO() {
		return roleMasterDTO;
	}

	public void setRoleMasterDTO(RoleMasterDTO roleMasterDTO) {
		this.roleMasterDTO = roleMasterDTO;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	@Override
	   public String toString()
	   {
		  return "UserMasterRoleDTO[userId="+userId+"userLoginId="+userLoginId+"userPassword="+userPassword+"userFullName="+userFullName+"activeFlag="+activeFlag+"+roleMasterDTO="+roleMasterDTO+"]"; 
	   }
	
}
