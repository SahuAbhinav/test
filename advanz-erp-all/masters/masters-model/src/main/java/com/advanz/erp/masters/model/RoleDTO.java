package com.advanz.erp.masters.model;

import java.util.ArrayList;
import java.util.List;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class RoleDTO extends BaseDTO{

	private Integer roleId;
	
	private String roleName;
	
	private List<UserDTO> users;

	private ModuleMenuMasterDTO menuRoles;
	
	
	public ModuleMenuMasterDTO getMenuRoles() {
		return menuRoles;
	}

	public void setMenuRoles(ModuleMenuMasterDTO menuRoles) {
		this.menuRoles = menuRoles;
	}

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

	public List<UserDTO> getUsers() {
		return users;
	}

	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}
	
	
}
