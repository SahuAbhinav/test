package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.UserMasterRoleDTO;

@SuppressWarnings("serial")
public class UserMasterRoleOutputMessage extends AdvanzErpBaseOutputMessage {
	private List<UserMasterRoleDTO> userMasterRoleDTOList;
	private UserMasterRoleDTO userMasterRoleDTO;

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

}
