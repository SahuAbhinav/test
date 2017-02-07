package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.UserRoleAndRightsDTO;
@SuppressWarnings("serial")
public class UserRoleAndRightsInputMessage  extends AdvanzErpBaseInputMessage{
	
	private UserRoleAndRightsDTO  userRoleAndRightsDTO;

	public UserRoleAndRightsDTO getUserRoleAndRightsDTO() {
		return userRoleAndRightsDTO;
	}

	public void setUserRoleAndRightsDTO(UserRoleAndRightsDTO userRoleAndRightsDTO) {
		this.userRoleAndRightsDTO = userRoleAndRightsDTO;
	}

	
	
}
