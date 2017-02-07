package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.LeaveApplicationDTO;

@SuppressWarnings("serial")
public class LeaveApplicationInputMessage extends AdvanzErpBaseInputMessage{
	private LeaveApplicationDTO leaveApplicationDTO;

	public LeaveApplicationDTO getLeaveApplicationDTO() {
		return leaveApplicationDTO;
	}

	public void setLeaveApplicationDTO(LeaveApplicationDTO leaveApplicationDTO) {
		this.leaveApplicationDTO = leaveApplicationDTO;
	}

	

}
