package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.UserDTO;

@SuppressWarnings("serial")
public class UserOutMessage  extends AdvanzErpBaseOutputMessage{
	
	private List<UserDTO> userDTOList;

	private UserDTO userDTO;

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public List<UserDTO> getUserDTOList() {
		return userDTOList;
	}

	public void setUserDTOList(List<UserDTO> userDTOList) {
		this.userDTOList = userDTOList;
	}
	
}
