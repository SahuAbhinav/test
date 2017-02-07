package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.LeaveApplicationDTO;

public class LeaveApplicationOutputMessage extends AdvanzErpBaseOutputMessage {

//	private BlanketProductionMasterDTO BlanketProductionMasterDTO;
	
	private List<LeaveApplicationDTO> leaveApplicationDTOList;

	public List<LeaveApplicationDTO> getLeaveApplicationDTOList() {
		return leaveApplicationDTOList;
	}

	public void setLeaveApplicationDTOList(
			List<LeaveApplicationDTO> leaveApplicationDTOList) {
		this.leaveApplicationDTOList = leaveApplicationDTOList;
	}

	


}
