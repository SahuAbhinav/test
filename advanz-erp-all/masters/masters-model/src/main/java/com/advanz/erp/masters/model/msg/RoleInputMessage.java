package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.MenuRoleDTO;
import com.advanz.erp.masters.model.ModuleMenuMasterDTO;
import com.advanz.erp.masters.model.RoleDTO;
import com.advanz.erp.masters.model.RoleMasterDTO;

@SuppressWarnings("serial")
public class RoleInputMessage  extends AdvanzErpBaseInputMessage {

	
	private RoleMasterDTO role;
	
	
	public RoleMasterDTO getRole() {
		return role;
	}

	public void setRole(RoleMasterDTO role) {
		this.role = role;
	}
	
}
