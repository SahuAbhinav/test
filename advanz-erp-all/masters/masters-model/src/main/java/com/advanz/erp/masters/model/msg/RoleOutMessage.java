package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.MenuRoleDTO;
import com.advanz.erp.masters.model.ModuleMenuMasterDTO;
import com.advanz.erp.masters.model.RoleDTO;
import com.advanz.erp.masters.model.RoleMasterDTO;
import com.advanz.erp.masters.model.UserMasterRoleDTO;

@SuppressWarnings("serial")
public class RoleOutMessage  extends AdvanzErpBaseOutputMessage {

	
	private List<RoleMasterDTO> roles;

	private RoleMasterDTO role;
	
	public List<RoleMasterDTO> getRoles() {
		return roles;
	}

	
	public RoleMasterDTO getRole() {
		return role;
	}

	public void setRole(RoleMasterDTO role) {
		this.role = role;
	}

		public void setRoles(List<RoleMasterDTO> dtoList) {
		this.roles = dtoList;
		
	}
	
	
	
}
