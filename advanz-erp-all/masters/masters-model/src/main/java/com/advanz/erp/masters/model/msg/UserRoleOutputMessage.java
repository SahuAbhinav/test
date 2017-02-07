package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.UserRoleDTO;

@SuppressWarnings("serial")
public class UserRoleOutputMessage extends AdvanzErpBaseOutputMessage {
	private UserRoleDTO userRoleDTO;
	private List<UserRoleDTO> userRoleDTOList;

	public UserRoleDTO getUserRoleDTO() {
		return userRoleDTO;
	}

	public void setUserRoleDTO(UserRoleDTO userRoleDTO) {
		this.userRoleDTO = userRoleDTO;
	}

	public List<UserRoleDTO> getUserRoleDTOList() {
		return userRoleDTOList;
	}

	public void setUserRoleDTOList(List<UserRoleDTO> userRoleDTOList) {
		this.userRoleDTOList = userRoleDTOList;
	}

}
