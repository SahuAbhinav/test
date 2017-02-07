package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;

import com.advanz.erp.masters.model.LeaveTypeMastDTO;
import com.advanz.erp.masters.model.criteria.LeaveTypeMastSearchCriteriaDTO;

public class LeaveTypeMastInputMessage extends AdvanzErpBaseInputMessage{

	private LeaveTypeMastDTO leaveTypeMastDTO;

	private LeaveTypeMastSearchCriteriaDTO searchCriteria;
	
	public LeaveTypeMastDTO getLeaveTypeMastDTO() {
		return leaveTypeMastDTO;
	}

	public void setLeaveTypeMastDTO(LeaveTypeMastDTO leaveTypeMastDTO) {
		this.leaveTypeMastDTO = leaveTypeMastDTO;
	}

	public LeaveTypeMastSearchCriteriaDTO getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(LeaveTypeMastSearchCriteriaDTO searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	
	
	
}
