package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.RoleMasterDTO;
@SuppressWarnings("serial")
public class RoleMasterInputMessage extends AdvanzErpBaseInputMessage {
	RoleMasterDTO roleMasterDTO;

	public RoleMasterDTO getRoleMasterDTO() {
		return roleMasterDTO;
	}

	public void setRoleMasterDTO(RoleMasterDTO roleMasterDTO) {
		this.roleMasterDTO = roleMasterDTO;
	}
}
