package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.RoleMasterDTO;
@SuppressWarnings("serial")
public class RoleMasterOutputMessage extends AdvanzErpBaseOutputMessage {
	List<RoleMasterDTO> roleMasterDTOList;
	RoleMasterDTO roleMasterDTO;

	public List<RoleMasterDTO> getRoleMasterDTOList() {
		return roleMasterDTOList;
	}

	public void setRoleMasterDTOList(List<RoleMasterDTO> roleMasterDTOList) {
		this.roleMasterDTOList = roleMasterDTOList;
	}

	public RoleMasterDTO getRoleMasterDTO() {
		return roleMasterDTO;
	}

	public void setRoleMasterDTO(RoleMasterDTO roleMasterDTO) {
		this.roleMasterDTO = roleMasterDTO;
	}

}
