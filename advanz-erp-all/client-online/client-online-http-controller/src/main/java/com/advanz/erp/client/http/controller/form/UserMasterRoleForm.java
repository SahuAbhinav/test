package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.UserMasterRoleDTO;

public class UserMasterRoleForm {
	private List<UserMasterRoleDTO> userMasterRoleDTOList;
	private UserMasterRoleDTO userMasterRoleDTO;
	private String succ;
	
	public String getSucc() {
		return succ;
	}

	public void setSucc(String succ) {
		this.succ = succ;
	}

	public List<UserMasterRoleDTO> getUserMasterRoleDTOList() {
		return userMasterRoleDTOList;
	}

	public void setUserMasterRoleDTOList(
			List<UserMasterRoleDTO> userMasterRoleDTOList) {
		this.userMasterRoleDTOList = userMasterRoleDTOList;
	}

	public UserMasterRoleDTO getUserMasterRoleDTO() {
		return userMasterRoleDTO;
	}

	public void setUserMasterRoleDTO(UserMasterRoleDTO userMasterRoleDTO) {
		this.userMasterRoleDTO = userMasterRoleDTO;
	}
  
	private Integer userId;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	private String userFullName;

	private String roleName;

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
	
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
