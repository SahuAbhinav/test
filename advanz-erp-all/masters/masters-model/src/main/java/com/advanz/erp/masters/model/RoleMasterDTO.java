package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class RoleMasterDTO extends BaseDTO {
	private Integer roleId;
	private String roleName;
	private Boolean hotKeyActive=false; 
	

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	public Boolean getHotKeyActive() {
		return hotKeyActive;
	}

	public void setHotKeyActive(Boolean hotKeyActive) {
		this.hotKeyActive = hotKeyActive;
	}

	@Override
   public String toString()
   {
	  return "RoleMasterDTO[roleId="+roleId+"roleName="+roleName+"]"; 
   }
}
