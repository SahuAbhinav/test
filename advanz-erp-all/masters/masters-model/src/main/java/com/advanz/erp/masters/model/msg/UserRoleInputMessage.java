package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.UserRoleDTO;

@SuppressWarnings("serial")
public class UserRoleInputMessage extends AdvanzErpBaseInputMessage {
	private UserRoleDTO userRoleDTO;

	public UserRoleDTO getUserRoleDTO() {
		return userRoleDTO;
	}

	public void setUserRoleDTO(UserRoleDTO userRoleDTO) {
		this.userRoleDTO = userRoleDTO;
	}

}
