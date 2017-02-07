package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.UserMasterRoleDTO;
@SuppressWarnings("serial")
public class UserMasterRoleInputMassage extends AdvanzErpBaseInputMessage {
	private UserMasterRoleDTO userMasterRoleDTO;

	public UserMasterRoleDTO getUserMasterRoleDTO() {
		return userMasterRoleDTO;
	}

	public void setUserMasterRoleDTO(UserMasterRoleDTO userMasterRoleDTO) {
		this.userMasterRoleDTO = userMasterRoleDTO;
	}

}
